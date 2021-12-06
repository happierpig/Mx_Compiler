package IR.Infrastructure;

import AST.*;
import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRModule;
import IR.Instruction.*;
import IR.Operand.*;
import IR.TypeSystem.*;
import Utils.GlobalScope;
import java.util.HashMap;
import java.util.LinkedList;

public class IRBuilder implements ASTVisitor {
    public IRModule targetModule;
    public GlobalScope gScope;
    public IRScope cScope;
    public HashMap<String, IRType> typeTable;
    public HashMap<String, IRFunction> funcTable;
    public LinkedList<VarDefNode> globalInit;
    public IRBasicBlock curBlock;
    public IRFunction curFunction;
    public enum Operator{add, sub, mul, sdiv, srem, shl, ashr, and, or, xor, logic_and, logic_or, eq, ne, sgt, sge, slt, sle, assign}

    public IRBuilder(IRModule _module, GlobalScope _gScope){
        this.targetModule = _module;
        this.gScope = _gScope;
        this.cScope = new IRScope(null);
        this.typeTable = new HashMap<>();
        this.funcTable = new HashMap<>();
        this.globalInit = new LinkedList<>();
        this.curBlock = null;
        this.curFunction = null;
        gScope.Class_Table.forEach((className,classScope)->{
            switch (className) {
                case "int" -> typeTable.put("int", new IntegerType(32));
                case "bool" -> typeTable.put("bool", new IntegerType(8));
                case "string" -> typeTable.put("string", new PointerType(new IntegerType(8)));
                //todo add class-type:
//                default -> typeTable.put(className,)
            }
        });
        typeTable.put("void",new VoidType());
        gScope.Functions_Table.forEach((funcName,funcNode)->{
            FunctionType funcType = new FunctionType(typeTable.get(funcNode.funcType.typeId));
            if(funcNode.parameterList != null) funcNode.parameterList.forEach(tmp->{
                IRType argType = typeTable.get(tmp.varType.typeId);
                funcType.addParameters(argType);
            });
            typeTable.put(funcName,funcType);
            IRFunction _func = new IRFunction("_f_"+funcName,funcType);
            if(funcNode.isBuiltin) _func.setBuiltin();
            funcTable.put(funcName,_func);
            targetModule.addFunction(_func);
        });

        //todo : collect Class information
    }

    @Override
    public void visit(RootNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(IntConstantExprNode node) {
        node.IRoperand = new IntConstant(node.value);
    }

    @Override
    public void visit(BoolConstantExprNode node) {
        node.IRoperand = new BoolConstant(node.value);
    }

    @Override
    public void visit(StringConstantExprNode node) {
        //todo : check repeated constant declaration
        StringConstant stringLiteral = new StringConstant(processRaw(node.value));
        targetModule.addString(stringLiteral);
        node.IRoperand = stringLiteral;
    }

    @Override
    public void visit(NullConstantExprNode node) {
        node.IRoperand = new NullConstant();
    }

    @Override
    public void visit(VarDefNode node) {
        IRType valueTy = typeTable.get(node.varType.typeId);
        Value value;
        if(cScope.parent == null){ // Global definition
            value = new GlobalDef(node.identifier,valueTy);
            targetModule.addGlobalDef((GlobalDef) value);
        }else value = stackAlloc(node.identifier,valueTy);
        cScope.setVariable(node.identifier,value);
        node.IRoperand = value;
        if(node.initValue != null){ // init
            if(cScope.parent != null){ // local variable
                node.initValue.accept(this);
                Value initValue;
                if(node.initValue.IRoperand instanceof NullConstant) ((NullConstant) node.initValue.IRoperand).setType(valueTy);
                if (node.initValue.IRoperand instanceof StringConstant) {
                    initValue = new Gep(node.identifier, node.initValue.IRoperand, 0, curBlock);
                }else initValue = node.initValue.IRoperand;
                this.memoryStore(initValue,value);
            }else globalInit.add(node);
        }
    }

    @Override
    public void visit(VarDefStmtNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(IdentifierExprNode node) {
        // visit id Node means load it in; So function call should not travel in this node :)
        node.IRoperand = memoryLoad(node.identifier,cScope.fetchValue(node.identifier),node.exprType.typeId.equals("bool"));
    }

    @Override
    public void visit(FuncCallExprNode node) {
        IRFunction func;
        if(node.Func instanceof IdentifierExprNode){
            func = funcTable.get(((IdentifierExprNode)node.Func).identifier);
        }else{
            // todo : class function call
            func = null;
        }
        if(node.AryList != null) node.AryList.forEach(tmp-> tmp.accept(this));
        Call newOperand = new Call(func,curBlock);
        if(node.AryList != null) node.AryList.forEach(tmp->newOperand.addArg(tmp.IRoperand));
        if(func.isBuiltin) func.setUsed();
        node.IRoperand = newOperand;
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if(node.returnVal != null){
            Value returnValue;
            node.returnVal.accept(this);
            returnValue = node.returnVal.IRoperand;
            this.memoryStore(returnValue,curFunction.returnAddress);
        }
        new Branch(curBlock,curFunction.exitBlock());
        // todo : add something to complete Return
        curBlock = null;
    }

    @Override
    public void visit(FuncDefNode node) {
        curFunction = funcTable.get(node.identifier);
        cScope = new IRScope(cScope);
        Value.refresh();
        IRBasicBlock tmpEntry = new IRBasicBlock(curFunction.name,curFunction); // entry-Block
        IRBasicBlock tmpExit = new IRBasicBlock(curFunction.name,curFunction); // exit-Block
        Value tmpReturnValue;
        if(!curFunction.type.toString().equals("void")){
            curFunction.returnAddress = new Alloc("_return",((FunctionType)curFunction.type).returnType,tmpEntry);
            tmpReturnValue = new Load("_return",curFunction.returnAddress,tmpExit);
        }else tmpReturnValue = new Value("Anonymous",new VoidType());
        new Ret(tmpReturnValue,tmpExit);
        curBlock = curFunction.entryBlock();
        if(node.parameterList != null) node.parameterList.forEach(tmp->{
            Value tmpArg = new Value("_arg",typeTable.get(tmp.varType.typeId));
            curFunction.addParameter(tmpArg);
            Alloc realArg = this.stackAlloc(tmp.identifier,tmpArg.type);
            this.memoryStore(tmpArg,realArg);
            cScope.setVariable(tmp.identifier,realArg);
        });
        if(node.funcBody.stmtList != null) node.funcBody.stmtList.forEach(stmt->{
            if(curBlock == null) curBlock = new IRBasicBlock(curFunction.name,curFunction);
            stmt.accept(this);
        });

        curBlock = null;
        cScope = cScope.parent;
    }

    @Override
    public void visit(ExprStmtNode node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(BinaryExprNode node) {
        IRBuilder.Operator op = translateOp(node.operator);
        if(op == Operator.logic_and || op == Operator.logic_or){
            Value newOperand = null;
            node.LOperand.accept(this); Value tmpRs1 = node.LOperand.IRoperand;
            switch (op){
                case logic_and -> {
                    if(tmpRs1 instanceof BoolConstant){
                        if(!((BoolConstant) tmpRs1).value) newOperand = tmpRs1;
                        else{
                            node.ROperand.accept(this);
                            newOperand = node.ROperand.IRoperand;
                        }
                    }else newOperand = shortCircuit(op,node,tmpRs1);
                }
                case logic_or -> {
                    if(tmpRs1 instanceof BoolConstant){
                        if(((BoolConstant) tmpRs1).value) newOperand = tmpRs1;
                        else{
                            node.ROperand.accept(this);
                            newOperand = node.ROperand.IRoperand;
                        }
                    }else newOperand = shortCircuit(op,node,tmpRs1);
                }
            }
            node.IRoperand = newOperand;
        }else {
            node.ROperand.accept(this);
            Value tmpRs2 = node.ROperand.IRoperand;
            if (op != Operator.assign) {
                Value newOperand = null;
                node.LOperand.accept(this);
                Value tmpRs1 = node.LOperand.IRoperand; // lvalue do not need load in.
                if (tmpRs1 instanceof IRConstant && tmpRs2 instanceof IRConstant) {
                    newOperand = calculateConstant(op, (IRConstant) tmpRs1, (IRConstant) tmpRs2);
                } else {
                    switch (op) {
                        case add, sub, mul, sdiv, srem, shl, ashr, and, or, xor -> newOperand = new Binary(op, tmpRs1, tmpRs2, curBlock);
                        case eq, ne, sgt, sge, slt, sle -> {
                            if (tmpRs2 instanceof NullConstant) ((NullConstant) tmpRs2).setType(tmpRs1.type);
                            newOperand = new Icmp(op, tmpRs1, tmpRs2, curBlock);
                        }
                        default -> throw new RuntimeException("[Debug] Unknown Op again. :(");
                    }
                }
                node.IRoperand = newOperand;
            } else {
                Value _address = getAddress(node.LOperand);
                assert _address != null;
                if (tmpRs2 instanceof NullConstant) ((NullConstant) tmpRs2).setType(_address.type.dePointed());
                this.memoryStore(tmpRs2, _address);
                node.IRoperand = tmpRs2;
            }
        }
    }

    @Override
    public void visit(MonoExprNode node) {
        // Class Node todo
        node.operand.accept(this);
        Value originValue = node.operand.IRoperand;
        Value newOperand = originValue;
        switch(node.operator){
            case LNOT,BITNOT,POS,NEG -> {
                if(originValue instanceof IRConstant){
                    switch (node.operator){
                        case LNOT -> newOperand = new BoolConstant(!((BoolConstant)originValue).value);
                        case BITNOT -> newOperand = new IntConstant(~((IntConstant)originValue).value);
                        case POS -> {}
                        case NEG -> newOperand = new IntConstant(-((IntConstant)originValue).value);
                    }
                }else{
                    switch (node.operator){
                        case LNOT -> newOperand = new Binary(Operator.xor,originValue,new BoolConstant(true),curBlock);
                        case BITNOT -> newOperand = new Binary(Operator.xor,originValue,new IntConstant(-1),curBlock);
                        case POS -> {}
                        case NEG -> newOperand = new Binary(Operator.sub,new IntConstant(0),originValue,curBlock);
                    }
                }
            }
            case PREINC, PREDEC, AFTINC, AFTDEC -> {
                Value address = getAddress(node.operand);
                Value newValue = null;
                switch(node.operator) {
                    case PREINC -> newValue = newOperand = new Binary(Operator.add,originValue,new IntConstant(1),curBlock);
                    case PREDEC -> newValue = newOperand = new Binary(Operator.add,originValue,new IntConstant(-1),curBlock);
                    case AFTINC -> newValue = new Binary(Operator.add,originValue,new IntConstant(1),curBlock);
                    case AFTDEC -> newValue = new Binary(Operator.add,originValue,new IntConstant(-1),curBlock);
                }
                assert newValue != null;
                this.memoryStore(newValue,address);
            }
        }
        node.IRoperand = newOperand;
    }

    @Override
    public void visit(BlockStmtNode node) {
        cScope = new IRScope(cScope);
        if(node.stmtList != null) node.stmtList.forEach(tmp->tmp.accept(this));
        cScope = cScope.parent;
    }

    @Override
    public void visit(IfStmtNode node) {
        node.IRoperand = null;
        cScope = new IRScope(cScope);
        IRBasicBlock thenBlock = new IRBasicBlock("if_then",curFunction);
        IRBasicBlock termBlock = new IRBasicBlock(curFunction.name,curFunction);
        node.condition.accept(this);
        if(node.elseCode != null){
            IRBasicBlock elseBlock = new IRBasicBlock("if_else",curFunction);
            new Branch(curBlock,node.condition.IRoperand,thenBlock,elseBlock);
            curBlock = elseBlock;
            node.elseCode.accept(this);
            new Branch(curBlock,termBlock);
        }else new Branch(curBlock,node.condition.IRoperand,thenBlock,termBlock);
        curBlock = thenBlock;
        node.thenCode.accept(this);
        // todo :debug
        new Branch(curBlock,termBlock);
        curBlock = termBlock;
        cScope = cScope.parent;
    }

    @Override
    public void visit(WhileStmtNode node) {
        // todo : add break / continue
        node.IRoperand = null;
        cScope = new IRScope(cScope);
        IRBasicBlock condition = new IRBasicBlock("while_condition",curFunction);
        IRBasicBlock loopBody = new IRBasicBlock("while_body",curFunction);
        IRBasicBlock termBlock = new IRBasicBlock(curFunction.name,curFunction);
        new Branch(curBlock,condition);
        curBlock = condition;
        node.condition.accept(this);
        new Branch(curBlock,node.condition.IRoperand,loopBody,termBlock);
        curBlock = loopBody;
        node.loopBody.accept(this);
        new Branch(curBlock,condition);
        curBlock = termBlock;
        cScope = cScope.parent;
    }

    @Override
    public void visit(ForStmtNode node) {
        // todo : none-condition situation && add break / continue
        node.IRoperand = null;
        cScope = new IRScope(cScope);
        if(node.init != null) node.init.accept(this);
        IRBasicBlock condition = new IRBasicBlock("for_condition",curFunction);
        IRBasicBlock iter = new IRBasicBlock("for_iter",curFunction);
        IRBasicBlock loopBody = new IRBasicBlock("for_body",curFunction);
        IRBasicBlock termBody = new IRBasicBlock(curFunction.name,curFunction);
        new Branch(curBlock,condition);
        curBlock = condition;
        if(node.condition != null){
            node.condition.accept(this);
            new Branch(curBlock,node.condition.IRoperand,loopBody,termBody);
        }else new Branch(curBlock,loopBody);
        curBlock = loopBody;
        node.loopBody.accept(this);
        new Branch(curBlock,iter);
        curBlock = iter;
        if(node.iteration != null) node.iteration.accept(this);
        new Branch(curBlock,condition);
        curBlock = termBody;
        cScope = cScope.parent;
    }

    @Override
    public void visit(ArrayAccessExprNode node) {

    }

    @Override
    public void visit(NewExprNode node) {

    }

    @Override
    public void visit(BreakStmtNode Node) {

    }

    @Override
    public void visit(ClassDefNode Node) {

    }

    @Override
    public void visit(ContinueStmtNode node) {

    }

    @Override
    public void visit(ObjectMemberExprNode node) {
        // node.IROperand means load Value
        // address need
    }

    @Override
    public void visit(ThisExprNode node) {

    }

    @Override
    public void visit(LambdaExprNode node) {

    }

    @Override
    public void visit(VoidTypeNode node) {

    }

    @Override
    public void visit(ClassTypeNode node) {

    }

    @Override
    public void visit(ArrayTypeNode node) {

    }

    private String processRaw(String raw){
        return raw
                .substring(1,raw.length()-1)
                .replace("\\", "\\5C")
                .replace("\n", "\\0A")
                .replace("\"", "\\22")
                .replace("\t", "\\09");
    }

    private Alloc stackAlloc(String identifier, IRType _ty){
        return new Alloc(identifier,_ty,curBlock);
    }

    private Value memoryLoad(String identifier, Value address, boolean mode){ //mode true for bool-load
        Value tmp = new Load(identifier,address,curBlock);
        return mode ? new Trunc(identifier,tmp,new IntegerType(1),curBlock) : tmp;
    }

    private void memoryStore(Value value, Value address){
        Value target = value;
        if(!(value instanceof BoolConstant) && value.type.isEqual(new IntegerType(1)) && address.type.isEqual(new PointerType(new IntegerType(8))))
            target = new Zext(value,new IntegerType(8),curBlock);
        new Store(target,address,curBlock);
    }

    private Value getAddress(ASTNode node){
        if(node instanceof IdentifierExprNode){
            return cScope.fetchValue(((IdentifierExprNode) node).identifier);
        }else if(node instanceof ObjectMemberExprNode){
            // todo :
            return null;
        }else throw new RuntimeException("[Debug] Address get fault. ");
    }

    private IRBuilder.Operator translateOp(BinaryExprNode.Op origin){
        switch (origin){
            case ADD -> {return Operator.add;}
            case SUB -> {return Operator.sub;}
            case MUL -> {return Operator.mul;}
            case DIV -> {return Operator.sdiv;}
            case MOD -> {return Operator.srem;}
            case SHL -> {return Operator.shl;}
            case SHR -> {return Operator.ashr;}
            case AND -> {return Operator.and;}
            case XOR -> {return Operator.xor;}
            case OR -> {return Operator.or;}
            case LAND -> {return Operator.logic_and;}
            case LOR -> {return Operator.logic_or;}
            case GT -> {return Operator.sgt;}
            case LT-> {return Operator.slt;}
            case GE -> {return Operator.sge;}
            case LE -> {return Operator.sle;}
            case EQ -> {return Operator.eq;}
            case NE -> {return Operator.ne;}
            case ASSIGN -> {return Operator.assign;}
            default -> throw new RuntimeException("[Debug] Unknown operator.");
        }
    }

    private IRConstant calculateConstant(IRBuilder.Operator op, IRConstant rs1, IRConstant rs2){
        assert rs1.type.isEqual(rs2.type);
        IRConstant returnValue = null;
        switch (op) {
            case add, sub, mul, sdiv, srem, shl, ashr, and, or, xor, logic_and, logic_or -> {
                if (rs1 instanceof IntConstant) {
                    int value1 = ((IntConstant) rs1).value;
                    int value2 = ((IntConstant) rs2).value;
                    int result;
                    switch (op) {
                        case add -> result = (value1 + value2);
                        case sub -> result = (value1 - value2);
                        case mul -> result = (value1 * value2);
                        case sdiv -> result = (value1 / value2);
                        case srem -> result = (value1 % value2);
                        case and -> result = (value1 & value2);
                        case or -> result = (value1 | value2);
                        case xor -> result = (value1 ^ value2);
                        case shl -> result = (value1 << value2);
                        case ashr -> result = (value1 >> value2);
                        default -> throw new RuntimeException("[Debug] Unknown Op.");
                    }
                    returnValue = new IntConstant(result);
                } else {
                    boolean value1 = ((BoolConstant) rs1).value;
                    boolean value2 = ((BoolConstant) rs2).value;
                    boolean result;
                    switch (op) {
                        case logic_and -> result = (value1 && value2);
                        case logic_or -> result = (value1 || value2);
                        default -> throw new RuntimeException("[Debug] Unknown Op.");
                    }
                    returnValue = new BoolConstant(result);
                }
            }
            case eq, ne, sgt, sge, slt, sle -> {
                boolean result;
                if(rs1 instanceof IntConstant){
                    int value1 = ((IntConstant) rs1).value;
                    int value2 = ((IntConstant) rs2).value;
                    switch (op) {
                        case eq -> result = (value1 == value2);
                        case ne -> result = (value1 != value2);
                        case sge -> result = (value1 >= value2);
                        case sgt -> result = (value1 > value2);
                        case sle -> result = (value1 <= value2);
                        case slt -> result = (value1 < value2);
                        default -> throw new RuntimeException("[Debug] Unknown Op.");
                    }
                }else{
                    boolean value1 = ((BoolConstant) rs1).value;
                    boolean value2 = ((BoolConstant) rs2).value;
                    switch (op) {
                        case eq -> result = (value1 == value2);
                        case ne -> result = (value1 != value2);
                        default -> throw new RuntimeException("[Debug] Unknown Op.");
                    }
                }
                returnValue = new BoolConstant(result);
            }
            default -> throw new RuntimeException("[Debug] Unknown op .");
        }
        return returnValue;
    }

    private Value shortCircuit(Operator op, BinaryExprNode node, Value tmpRs1){
        Value tmpAddress = this.stackAlloc(op.toString(),new IntegerType(1));
        IRBasicBlock dBlock = new IRBasicBlock("_dBlock",curFunction);  // direct
        IRBasicBlock sBlock = new IRBasicBlock("_sBlock",curFunction);  // second
        IRBasicBlock tBlock = new IRBasicBlock("_tBlock",curFunction);  // terminal
        switch(op){
            case logic_and -> new Branch(curBlock,tmpRs1,sBlock,dBlock);
            case logic_or -> new Branch(curBlock,tmpRs1,dBlock,sBlock);
        }
        this.curBlock = dBlock;
        this.memoryStore(tmpRs1,tmpAddress); new Branch(curBlock,tBlock);
        this.curBlock = sBlock;
        node.ROperand.accept(this); Value tmpRs2 = node.ROperand.IRoperand;
        this.memoryStore(tmpRs2,tmpAddress); new Branch(curBlock,tBlock);
        this.curBlock = tBlock;
        return this.memoryLoad("circuit",tmpAddress,false);
    }

    public void processGlobalInit(){
        if(this.globalInit.size() == 0) return;
        FunctionType tempType = new FunctionType(new VoidType());
        IRFunction entryFunction = new IRFunction("_GLOBAL_",tempType);
        IRBasicBlock mainBody = new IRBasicBlock(entryFunction.name,entryFunction);
        this.globalInit.forEach(node->{
            IRFunction nowFunction = new IRFunction("_global_var_init",tempType);
            Value address = cScope.fetchValue(node.identifier);
            this.curFunction = nowFunction;
            this.curBlock = new IRBasicBlock(node.identifier,curFunction); // entry-Block
            IRBasicBlock tmpExit = new IRBasicBlock(node.identifier,curFunction); // exit-Block
            new Ret(new Value("Anonymous",new VoidType()),tmpExit);
            Value initValue;
            node.initValue.accept(this);
            if(node.initValue.IRoperand instanceof NullConstant) ((NullConstant) node.initValue.IRoperand).setType(typeTable.get(node.varType.typeId));
            if (node.initValue.IRoperand instanceof StringConstant) {
                initValue = new Gep(node.identifier, node.initValue.IRoperand, 0, curBlock);
            }else initValue = node.initValue.IRoperand;
            this.memoryStore(initValue,address);
            new Branch(curBlock,tmpExit);
            this.targetModule.addGlobalInit(curFunction);
            new Call(nowFunction,mainBody);
        });
        new Ret(new Value("Anonymous",new VoidType()),mainBody);
        this.targetModule.addGlobalInit(entryFunction);
    }
}