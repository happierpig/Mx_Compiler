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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

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

    private Stack<IRBasicBlock> loopContinue;
    private Stack<IRBasicBlock> loopBreak;

    public IRBuilder(IRModule _module, GlobalScope _gScope){
        this.targetModule = _module;
        this.gScope = _gScope;
        this.cScope = new IRScope(null, IRScope.scopeType.Global);
        this.typeTable = new HashMap<>();
        this.funcTable = new HashMap<>();
        this.globalInit = new LinkedList<>();
        this.curBlock = null;
        this.curFunction = null;
        loopContinue = new Stack<>(); loopBreak = new Stack<>();

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
        if(!cScope.isValid()) return;
        node.IRoperand = new IntConstant(node.value);
    }

    @Override
    public void visit(BoolConstantExprNode node) {
        if(!cScope.isValid()) return;
        node.IRoperand = new BoolConstant(node.value);
    }

    @Override
    public void visit(StringConstantExprNode node) {
        //todo : check repeated constant declaration
        if(!cScope.isValid()) return;
        StringConstant stringLiteral = new StringConstant(processRaw(node.value));
        targetModule.addString(stringLiteral);
        node.IRoperand = stringLiteral;
    }

    @Override
    public void visit(NullConstantExprNode node) {
        if(!cScope.isValid()) return;
        node.IRoperand = new NullConstant();
    }

    @Override
    public void visit(VarDefNode node) {
        if(!cScope.isValid()) return;
        IRType valueTy = typeTable.get(node.varType.typeId);
        if(node.varType instanceof ArrayTypeNode) valueTy = new PointerType(valueTy,((ArrayTypeNode) node.varType).dimSize);
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
                Value initValue = node.initValue.IRoperand;
                if(initValue instanceof NullConstant) ((NullConstant) initValue).setType(valueTy);
                if (initValue instanceof StringConstant) {
                    initValue = new Gep(new PointerType(new IntegerType(8)), initValue, curBlock);
                    ((Gep) initValue).addIndex(new IntConstant(0)).addIndex(new IntConstant(0));
                }
                this.memoryStore(initValue,value);
            }else globalInit.add(node);
        }else{
            if(node.varType instanceof ArrayTypeNode) {
                if (cScope.parent != null) this.memoryStore(new NullConstant((PointerType) valueTy), value);
                else globalInit.add(node);
            }
        }
    }

    @Override
    public void visit(VarDefStmtNode node) {
        if(!cScope.isValid()) return;
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(IdentifierExprNode node) {
        // visit id Node means load it in; So function call should not travel in this node :)
        if(!cScope.isValid()) return;
        node.IRoperand = memoryLoad(node.identifier,cScope.fetchValue(node.identifier),node.exprType.typeId.equals("bool"));
    }

    @Override
    public void visit(FuncCallExprNode node) {
        if(!cScope.isValid()) return;
        IRFunction func;
        if(node.Func instanceof IdentifierExprNode){
            func = funcTable.get(((IdentifierExprNode)node.Func).identifier);
        }else{
            // todo : class function call
            func = null;
        }
        if(node.AryList != null) node.AryList.forEach(tmp-> tmp.accept(this));
        assert func != null;
        Call newOperand = new Call(func,curBlock);
        if(node.AryList != null) node.AryList.forEach(tmp->newOperand.addArg(tmp.IRoperand));
        if(func.isBuiltin) func.setUsed();
        node.IRoperand = newOperand;
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if(!cScope.isValid()) return;
        if(node.returnVal != null){
            Value returnValue;
            node.returnVal.accept(this);
            returnValue = node.returnVal.IRoperand;
            this.memoryStore(returnValue,curFunction.returnAddress);
        }
        new Branch(curBlock,curFunction.exitBlock());
        cScope.setInvalid();
    }

    @Override
    public void visit(FuncDefNode node) {
        curFunction = funcTable.get(node.identifier);
        cScope = new IRScope(cScope, IRScope.scopeType.Func);
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
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(ExprStmtNode node) {
        if(!cScope.isValid()) return;
        node.expr.accept(this);
    }

    @Override
    public void visit(BinaryExprNode node) {
        if(!cScope.isValid()) return;
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
                Value assignValue = tmpRs2;
                assert _address != null;
                if (tmpRs2 instanceof NullConstant) ((NullConstant) tmpRs2).setType(_address.type.dePointed());
                if (tmpRs2 instanceof StringConstant){
                    assignValue = new Gep(new PointerType(new IntegerType(8)),tmpRs2, curBlock);
                    ((Gep) assignValue).addIndex(new IntConstant(0)).addIndex(new IntConstant(0));
                }
                this.memoryStore(assignValue, _address);
                node.IRoperand = assignValue;
            }
        }
    }

    @Override
    public void visit(MonoExprNode node) {
        // todo :Class Node
        if(!cScope.isValid()) return;
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
        if(!cScope.isValid()) return;
        cScope = new IRScope(cScope, IRScope.scopeType.Common);
        if(node.stmtList != null) node.stmtList.forEach(tmp->tmp.accept(this));
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(IfStmtNode node) {
        if(!cScope.isValid()) return;
        node.IRoperand = null;
        cScope = new IRScope(cScope, IRScope.scopeType.Flow);
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
        new Branch(curBlock,termBlock);
        curBlock = termBlock;
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(WhileStmtNode node) {
        // todo : add break / continue
        if(!cScope.isValid()) return;
        node.IRoperand = null;
        cScope = new IRScope(cScope, IRScope.scopeType.Flow);
        IRBasicBlock condition = new IRBasicBlock("while_condition",curFunction);
        IRBasicBlock loopBody = new IRBasicBlock("while_body",curFunction);
        IRBasicBlock termBlock = new IRBasicBlock(curFunction.name,curFunction);
        this.pushStack(condition,termBlock);
        new Branch(curBlock,condition);
        curBlock = condition;
        node.condition.accept(this);
        new Branch(curBlock,node.condition.IRoperand,loopBody,termBlock);
        curBlock = loopBody;
        node.loopBody.accept(this);
        new Branch(curBlock,condition);
        curBlock = termBlock;
        this.popStack();
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(ForStmtNode node) {
        // todo : none-condition situation
        if(!cScope.isValid()) return;
        node.IRoperand = null;
        cScope = new IRScope(cScope, IRScope.scopeType.Flow);
        if(node.init != null) node.init.accept(this);
        IRBasicBlock condition = new IRBasicBlock("for_condition",curFunction);
        IRBasicBlock iter = new IRBasicBlock("for_iter",curFunction);
        IRBasicBlock loopBody = new IRBasicBlock("for_body",curFunction);
        IRBasicBlock termBody = new IRBasicBlock(curFunction.name,curFunction);
        this.pushStack(iter,termBody);
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
        this.popStack();
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(BreakStmtNode Node) {
        if(!cScope.isValid()) return;
        new Branch(curBlock,loopBreak.peek());
        cScope.setInvalid();
    }

    @Override
    public void visit(ContinueStmtNode node) {
        if(!cScope.isValid()) return;
        new Branch(curBlock,loopContinue.peek());
        cScope.setInvalid();
    }

    @Override
    public void visit(NewExprNode node) {
        if(!cScope.isValid()) return;
        Value newOperand = null;
        if(node.isArray()){
            LinkedList<ExprNode> initList = new LinkedList<>(node.SizeList);
            newOperand = recursiveNew(initList,new PointerType(typeTable.get(node.newType.typeId),node.DimSize));
        }else{
            //todo : new class object
        }
        node.IRoperand = newOperand;
    }

    @Override
    public void visit(ArrayAccessExprNode node) {

    }

    @Override
    public void visit(ClassDefNode Node) {

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

    private Value heapAlloc(IRType targetType, Value byteSize){
        IRFunction malloc = funcTable.get("_malloc");
        Value returnValue = new Call(malloc,curBlock);
        ((Call)returnValue).addArg(byteSize);
        malloc.setUsed();
        if(!targetType.isEqual(returnValue.type)) returnValue = new Bitcast(returnValue,targetType,curBlock);
        return returnValue;
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
        Value tmpAddress = this.stackAlloc(op.toString(),new IntegerType(8));
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
        return this.memoryLoad("circuit",tmpAddress,true);
    }

    public void processGlobalInit(){
        if(this.globalInit.size() == 0) return;
        FunctionType tempType = new FunctionType(new VoidType());
        IRFunction entryFunction = new IRFunction("_GLOBAL_",tempType);
        IRBasicBlock mainBody = new IRBasicBlock(entryFunction.name,entryFunction);
        this.globalInit.forEach(node->{
            IRType valueTy = typeTable.get(node.varType.typeId);
            if(node.varType instanceof ArrayTypeNode) valueTy = new PointerType(valueTy,((ArrayTypeNode) node.varType).dimSize);
            IRFunction nowFunction = new IRFunction("_global_var_init",tempType);
            Value address = cScope.fetchValue(node.identifier);
            this.curFunction = nowFunction;
            this.curBlock = new IRBasicBlock(node.identifier,curFunction); // entry-Block
            IRBasicBlock tmpExit = new IRBasicBlock(node.identifier,curFunction); // exit-Block
            new Ret(new Value("Anonymous",new VoidType()),tmpExit);
            Value initValue;
            if(node.initValue == null) initValue = new NullConstant();
            else{
                node.initValue.accept(this);
                initValue = node.initValue.IRoperand;
            }
            if(initValue instanceof NullConstant) ((NullConstant) initValue).setType(valueTy);
            if (initValue instanceof StringConstant) {
                initValue = new Gep(new PointerType(new IntegerType(8)), initValue, curBlock);
                ((Gep) initValue).addIndex(new IntConstant(0)).addIndex(new IntConstant(0));
            }
            this.memoryStore(initValue,address);
            new Branch(curBlock,tmpExit);
            this.targetModule.addGlobalInit(curFunction);
            new Call(nowFunction,mainBody);
        });
        new Ret(new Value("Anonymous",new VoidType()),mainBody);
        this.targetModule.addGlobalInit(entryFunction);
    }

    private void pushStack(IRBasicBlock cBlock, IRBasicBlock bBlock){
        loopContinue.push(cBlock); loopBreak.push(bBlock);
    }

    private void popStack(){
        loopContinue.pop(); loopBreak.pop();
    }

    private Value recursiveNew(LinkedList<ExprNode> initList, IRType targetType){
        IRType elementType = targetType.dePointed();
        // get element number
        initList.getFirst().accept(this);
        Value elementNumber = initList.getFirst().IRoperand;
        initList.removeFirst();
        // calculate total byte size (including array size)
        Value elementByteSize = new Binary(Operator.mul,elementNumber,new IntConstant(elementType.byteSize()),curBlock);
        Value totalByteSize = new Binary(Operator.add,elementByteSize,new IntConstant(4),curBlock);
        // malloc
        Value i32Pointer = heapAlloc(new PointerType(new IntegerType(32)),totalByteSize);
        // store elementNumber
        this.memoryStore(elementNumber,i32Pointer);
        Gep biasPointer = new Gep(new PointerType(new IntegerType(32)),i32Pointer,curBlock);
        biasPointer.addIndex(new IntConstant(1));
        Value realPointer = new Bitcast(biasPointer,targetType,curBlock);
        if(initList.size() == 0) return realPointer;
        // enrolling for into IR; while(nowPtr != elementNumber){ /  enrolling  / + nowPtr++}
        Value ptrAddress = this.stackAlloc("array_ptr",new IntegerType(32));
        this.memoryStore(new IntConstant(0),ptrAddress);
        IRBasicBlock condition = new IRBasicBlock("array_new_condition",curFunction);
        IRBasicBlock loopBody = new IRBasicBlock("array_new_body",curFunction);
        IRBasicBlock termBlock = new IRBasicBlock(curFunction.name,curFunction);
        new Branch(curBlock,condition);
        curBlock = condition;
        Value ptr = this.memoryLoad("array_ptr",ptrAddress,false);
        Value flag = new Icmp(Operator.ne,ptr,elementNumber,curBlock);
        new Branch(curBlock,flag,loopBody,termBlock);
        curBlock = loopBody;
        Gep elementPtr = new Gep(targetType,realPointer,curBlock);
        elementPtr.addIndex(ptr);
        Value element = recursiveNew(initList,elementType);
        this.memoryStore(element,elementPtr);
        this.memoryStore(new Binary(Operator.add,ptr,new IntConstant(1),curBlock),ptrAddress);
        new Branch(curBlock,condition);
        curBlock = termBlock;
        return realPointer;
    }
}