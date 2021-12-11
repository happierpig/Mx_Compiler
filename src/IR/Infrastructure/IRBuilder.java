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
import java.util.Stack;

public class IRBuilder implements ASTVisitor {
    public IRModule targetModule;
    public GlobalScope gScope;
    public IRScope cScope;  // symbol table storing pointer
    public HashMap<String, IRType> typeTable;
    public HashMap<String, IRFunction> funcTable;
    public HashMap<String,StringConstant> stringTable; // avoid repeatedly global str
    public LinkedList<VarDefNode> globalInit;
    public IRBasicBlock curBlock;
    public IRFunction curFunction;
    public StructType curClass;
    public enum Operator{add, sub, mul, sdiv, srem, shl, ashr, and, or, xor, logic_and, logic_or, eq, ne, sgt, sge, slt, sle, assign}

    private Stack<IRBasicBlock> loopContinue;
    private Stack<IRBasicBlock> loopBreak;

    public IRBuilder(IRModule _module, GlobalScope _gScope){
        this.targetModule = _module;
        this.gScope = _gScope;
        this.cScope = new IRScope(null, IRScope.scopeType.Global);
        this.typeTable = new HashMap<>();
        this.funcTable = new HashMap<>();
        this.stringTable = new HashMap<>();
        this.globalInit = new LinkedList<>();
        this.curBlock = null;
        this.curFunction = null;
        this.curClass = null;
        loopContinue = new Stack<>(); loopBreak = new Stack<>();
        // todo: deal with array type pointer
        gScope.Class_Table.forEach((className,classScope)->{
            switch (className) {
                case "int" -> typeTable.put("int", new IntegerType(32));
                case "bool" -> typeTable.put("bool", new BoolType());
                case "string" -> typeTable.put("string", new PointerType(new IntegerType(8)));
                default -> {
                    StructType newClass = new StructType(className);
                    typeTable.put(className,new PointerType(newClass));
                    targetModule.addClassType(newClass);
                }
            }
        });
        typeTable.put("void",new VoidType());
        gScope.Functions_Table.forEach((funcName,funcNode)->{
            FunctionType funcType = new FunctionType(typeTable.get(funcNode.funcType.typeId));
            if(funcNode.parameterList != null) funcNode.parameterList.forEach(tmp->{
                IRType argType = typeTable.get(tmp.varType.typeId);
                funcType.addParameters(argType,tmp.identifier);
            });
            IRFunction _func = new IRFunction("_f_"+funcName,funcType);
            if(funcNode.isBuiltin) _func.setBuiltin();
            funcTable.put(funcName,_func);
            targetModule.addFunction(_func);
        });
        gScope.Class_Table.forEach((className,classScope)->{
            switch (className) {
                case "int","bool"-> {}
                default ->{
                    IRType pendingType = typeTable.get(className).dePointed();
                    assert classScope.Class_Table.size() == 0;
                    if(!className.equals("string")) classScope.Variable_Table.forEach((identifier,tmpTy)-> ((StructType)pendingType).addMember(identifier,typeTable.get(tmpTy.typeId)));
                    classScope.Functions_Table.forEach((funcName,funcNode)->{
                        IRType returnTy = (funcNode.funcType == null) ? new VoidType() : typeTable.get(funcNode.funcType.typeId);
                        FunctionType funcType = new FunctionType(returnTy);
                        IRType argType = new PointerType(pendingType);
                        funcType.addParameters(argType,"_this");
                        if(funcNode.parameterList != null) {
                            for (VarDefNode tmp : funcNode.parameterList) {
                                argType = typeTable.get(tmp.varType.typeId);
                                funcType.addParameters(argType,"_this");
                            }
                        }
                        IRFunction _func = new IRFunction("_class_" + className + "_" + funcName,funcType);
                        if(funcNode.isBuiltin) _func.setBuiltin();
                        funcTable.put(_func.name,_func);
                        targetModule.addFunction(_func);
                    });
                    if(!className.equals("string") && funcTable.get("_class_" + className + "_" + className) == null){
                        FunctionType funcType = new FunctionType(new VoidType());
                        IRType argType = new PointerType(pendingType);
                        funcType.addParameters(argType,"_this");
                        IRFunction _func = new IRFunction("_class_" + className + "_" + className,funcType);
                        _func.addParameter(new Value("_arg",argType)); // this pointer
                        IRBasicBlock onlyBlock = new IRBasicBlock(_func.name,_func);
                        new Ret(new Value("Anonymous",new VoidType()),onlyBlock);
                        funcTable.put(_func.name, _func);
                        targetModule.addFunction(_func);
                    }
                }
            }
        });
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
        if(!cScope.isValid()) return;
        StringConstant stringLiteral = stringTable.get(node.value);
        if(stringLiteral == null){
            stringLiteral = new StringConstant(node.value);
            targetModule.addString(stringLiteral);
            stringTable.put(node.value,stringLiteral);
        }
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
                if (initValue instanceof StringConstant) initValue = getStringPtr(initValue);
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
        node.IRoperand = memoryLoad(node.identifier,getAddress(node),curBlock);
    }

    @Override
    public void visit(FuncCallExprNode node) {
        if(!cScope.isValid()) return;
        IRFunction func = null;
        Value thisPtr = null;
        if(node.Func instanceof IdentifierExprNode){
            String funcName = ((IdentifierExprNode)node.Func).identifier;
            if(curClass != null) func = funcTable.get("_"+curClass.name+"_"+funcName);
            if(func == null) func = funcTable.get(funcName);
        }else{
            assert node.Func instanceof ObjectMemberExprNode;
            ((ObjectMemberExprNode) node.Func).base.accept(this);
            thisPtr = ((ObjectMemberExprNode) node.Func).base.IRoperand;
            if(((ObjectMemberExprNode) node.Func).base.exprType instanceof ArrayTypeNode){
                node.IRoperand = arraySize(thisPtr);
                return;
            }
            String className;
            IRType classType = thisPtr.type.dePointed();
            if(classType instanceof StructType) className = ((StructType) classType).name;
            else{assert classType instanceof IntegerType;className = "class_string";}
            func = funcTable.get("_"+className+"_"+((ObjectMemberExprNode) node.Func).member);
        }
        if(node.AryList != null) node.AryList.forEach(tmp->{
            tmp.accept(this);
            Value tmpArg = tmp.IRoperand;
            // may cause bugs
            if(tmpArg instanceof StringConstant) tmpArg = getStringPtr(tmpArg);
            tmp.IRoperand = tmpArg;
        });
        assert func != null;
        Call newOperand = new Call(func,curBlock);
        if(thisPtr != null) newOperand.addArg(thisPtr);
        if(node.AryList != null) node.AryList.forEach(tmp-> newOperand.addArg(tmp.IRoperand));
        func.setUsed();
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
        assert cScope.isValid();
        curFunction = curClass == null ? funcTable.get(node.identifier) : funcTable.get("_"+curClass.name+"_"+node.identifier);
        FunctionType funcType = (FunctionType) curFunction.type;
        cScope = new IRScope(cScope, IRScope.scopeType.Func);
        Value.refresh();
        IRBasicBlock tmpEntry = new IRBasicBlock(curFunction.name,curFunction); // entry-Block
        IRBasicBlock tmpExit = new IRBasicBlock(curFunction.name,curFunction); // exit-Block
        Value tmpReturnValue;
        if(!funcType.toString().equals("void")){
            curFunction.returnAddress =  stackAlloc("_return",funcType.returnType);
            tmpReturnValue = memoryLoad("_return",curFunction.returnAddress,tmpExit);
        }else tmpReturnValue = new Value("Anonymous",new VoidType());
        new Ret(tmpReturnValue,tmpExit);
        curBlock = curFunction.entryBlock();
        for(int i = 0;i < funcType.parametersName.size();++i){
            Value tmpArg = new Value("_arg",funcType.parametersType.get(i));
            curFunction.addParameter(tmpArg);
            Alloc realArg = this.stackAlloc(funcType.parametersName.get(i),tmpArg.type);
            this.memoryStore(tmpArg,realArg);
            cScope.setVariable(funcType.parametersName.get(i),realArg);
        }
        if(node.funcBody.stmtList != null) node.funcBody.stmtList.forEach(stmt-> stmt.accept(this));
        if(curBlock.terminator == null) new Branch(curBlock, curFunction.exitBlock());
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
        Value newOperand = null;
        if(op == Operator.logic_and || op == Operator.logic_or){
            node.LOperand.accept(this);
            Value tmpRs1 = node.LOperand.IRoperand;
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
        }else {
            node.ROperand.accept(this);
            Value tmpRs2 = node.ROperand.IRoperand;
            if (op != Operator.assign) {
                node.LOperand.accept(this);
                Value tmpRs1 = node.LOperand.IRoperand; // lvalue do not need load in.
                if(tmpRs1 instanceof StringConstant) tmpRs1 = getStringPtr(tmpRs1);
                if(tmpRs2 instanceof StringConstant) tmpRs2 = getStringPtr(tmpRs2);
                if (tmpRs1 instanceof IRConstant && tmpRs2 instanceof IRConstant) {
                    newOperand = calculateConstant(op, (IRConstant) tmpRs1, (IRConstant) tmpRs2);
                } else {
                    if(tmpRs1.type.isEqual(new PointerType(new IntegerType(8)))){
                        assert tmpRs2.type.isEqual(new PointerType(new IntegerType(8)));
                        newOperand = callStringOperator(op,tmpRs1,tmpRs2);
                    }else {
                        switch (op) {
                            case add, sub, mul, sdiv, srem, shl, ashr, and, or, xor -> newOperand = new Binary(op, tmpRs1, tmpRs2, curBlock);
                            case eq, ne, sgt, sge, slt, sle -> {
                                if (tmpRs2 instanceof NullConstant) ((NullConstant) tmpRs2).setType(tmpRs1.type);
                                newOperand = new Icmp(op, tmpRs1, tmpRs2, curBlock);
                                newOperand = new Zext(newOperand,new BoolType(),curBlock);
                            }
                            default -> throw new RuntimeException("[Debug] Unknown Op again. :(");
                        }
                    }
                }
            } else {
                Value _address = getAddress(node.LOperand);
                assert _address != null;
                if (tmpRs2 instanceof NullConstant) ((NullConstant) tmpRs2).setType(_address.type.dePointed());
                if (tmpRs2 instanceof StringConstant) tmpRs2 = getStringPtr(tmpRs2);
                newOperand = tmpRs2;
                this.memoryStore(newOperand, _address);
            }
        }
        node.IRoperand = newOperand;
    }

    @Override
    public void visit(MonoExprNode node) {
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
                assert newValue != null; assert address != null;
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
            addControl(curBlock,node.condition.IRoperand,thenBlock,elseBlock);
            curBlock = elseBlock;
            node.elseCode.accept(this);
            new Branch(curBlock,termBlock);
        }else addControl(curBlock,node.condition.IRoperand,thenBlock,termBlock);
        curBlock = thenBlock;
        node.thenCode.accept(this);
        new Branch(curBlock,termBlock);
        curBlock = termBlock;
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(WhileStmtNode node) {
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
        addControl(curBlock,node.condition.IRoperand,loopBody,termBlock);
        curBlock = loopBody;
        node.loopBody.accept(this);
        new Branch(curBlock,condition);
        curBlock = termBlock;
        this.popStack();
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(ForStmtNode node) {
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
            addControl(curBlock,node.condition.IRoperand,loopBody,termBody);
        }else new Branch(curBlock,loopBody);
        curBlock = loopBody;
        if(node.loopBody != null) node.loopBody.accept(this);
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
            String className = node.newType.typeId;
            StructType classType = (StructType)typeTable.get(className).dePointed();
            newOperand = heapAlloc(new PointerType(classType),new IntConstant(classType.byteSize()));
            Call constructor = new Call(funcTable.get("_"+classType.name+"_"+className),curBlock);
            constructor.addArg(newOperand);
        }
        node.IRoperand = newOperand;
    }

    @Override
    public void visit(ArrayAccessExprNode node) {
        if(!cScope.isValid()) return;
        Value address = getAddress(node);
        assert address != null;
        node.IRoperand = this.memoryLoad("_array",address,curBlock);
    }

    @Override
    public void visit(ClassDefNode node) {
        assert cScope.isValid();
        assert curClass == null;
        curClass = (StructType) typeTable.get(node.classIdentifier).dePointed();
        cScope = new IRScope(cScope, IRScope.scopeType.Class);
        curClass.indexTable.forEach((identifier,index)->cScope.setVariable(identifier,new IntConstant(index)));
        if(node.memberFunction != null) node.memberFunction.forEach(tmp->tmp.accept(this));

        curClass = null;
        cScope = cScope.upRoot();
    }

    @Override
    public void visit(ObjectMemberExprNode node) {
        if(!cScope.isValid()) return;
        node.base.accept(this);
        Value baseAddress = node.base.IRoperand;
        StructType baseType = (StructType) baseAddress.type.dePointed();
        Gep newOperand = new Gep(new PointerType(baseType.typeTable.get(node.member)),baseAddress,curBlock);
        newOperand.addIndex(new IntConstant(0)).addIndex(new IntConstant(baseType.indexTable.get(node.member)));
        node.IRoperand = memoryLoad(node.member,newOperand,curBlock);
    }

    @Override
    public void visit(ThisExprNode node) {
        if(!cScope.isValid()) return;
        assert curClass != null;
        Value ptr = cScope.fetchValue("_this"); assert  ptr != null;
        node.IRoperand = memoryLoad("_this",ptr,curBlock);
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

    private Alloc stackAlloc(String identifier, IRType _ty){
        return new Alloc(identifier,_ty,curFunction.entryBlock());
    }

    private Value heapAlloc(IRType targetType, Value byteSize){
        IRFunction malloc = funcTable.get("_malloc");
        Value returnValue = new Call(malloc,curBlock);
        ((Call)returnValue).addArg(byteSize);
        malloc.setUsed();
        if(!targetType.isEqual(returnValue.type)) returnValue = new Bitcast(returnValue,targetType,curBlock);
        return returnValue;
    }

    private Value memoryLoad(String identifier, Value address, IRBasicBlock _block){
        return new Load(identifier,address,_block);
    }

    private void memoryStore(Value value, Value address){
        new Store(value,address,curBlock);
    }

    private Value getAddress(ASTNode node){
        if(node instanceof IdentifierExprNode){
            String identifier = ((IdentifierExprNode) node).identifier;
            Value returnValue = cScope.fetchValue(identifier);
            if(cScope.isClass(identifier)){
                assert curClass != null; assert curFunction != null;
                Value ptr = cScope.fetchValue("_this"); assert ptr != null;
                ptr = memoryLoad("_this",ptr,curBlock);
                returnValue = new Gep(new PointerType(curClass.typeTable.get(identifier)),ptr,curBlock);
                ((Gep)returnValue).addIndex(new IntConstant(0)).addIndex(new IntConstant(curClass.indexTable.get(identifier)));
            }
            return returnValue;
        }else if(node instanceof ObjectMemberExprNode){
            ((ObjectMemberExprNode) node).base.accept(this);
            Value baseAddress = ((ObjectMemberExprNode) node).base.IRoperand; // Class store ptr
            StructType baseType = (StructType) baseAddress.type.dePointed();
            Gep returnValue = new Gep(new PointerType(baseType.typeTable.get(((ObjectMemberExprNode) node).member)),baseAddress,curBlock);
            returnValue.addIndex(new IntConstant(0)).addIndex(new IntConstant(baseType.indexTable.get(((ObjectMemberExprNode) node).member)));
            return returnValue;
        }else if(node instanceof ArrayAccessExprNode){
            Value ptrAddress = getAddress(((ArrayAccessExprNode) node).array);
            Value address = memoryLoad("_array",ptrAddress,curBlock);
            ((ArrayAccessExprNode) node).index.accept(this);
            Gep biasAddress = new Gep(address.type, address, curBlock);
            biasAddress.addIndex(((ArrayAccessExprNode) node).index.IRoperand);
            return biasAddress;
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
        IRConstant returnValue;
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
        Value tmpAddress = this.stackAlloc(op.toString(),new BoolType());
        IRBasicBlock dBlock = new IRBasicBlock("_dBlock",curFunction);  // direct
        IRBasicBlock sBlock = new IRBasicBlock("_sBlock",curFunction);  // second
        IRBasicBlock tBlock = new IRBasicBlock("_tBlock",curFunction);  // terminal
        switch(op){
            case logic_and -> addControl(curBlock,tmpRs1,sBlock,dBlock);
            case logic_or -> addControl(curBlock,tmpRs1,dBlock,sBlock);
        }
        this.curBlock = dBlock;
        this.memoryStore(tmpRs1,tmpAddress); new Branch(curBlock,tBlock);
        this.curBlock = sBlock;
        node.ROperand.accept(this); Value tmpRs2 = node.ROperand.IRoperand;
        this.memoryStore(tmpRs2,tmpAddress); new Branch(curBlock,tBlock);
        this.curBlock = tBlock;
        return this.memoryLoad("circuit",tmpAddress,curBlock);
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
            if (initValue instanceof StringConstant) initValue = getStringPtr(initValue);
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
        Value ptr = this.memoryLoad("array_ptr",ptrAddress,curBlock);
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

    private Value arraySize(Value address){
        Value i32Pointer = new Bitcast(address,new PointerType(new IntegerType(32)),curBlock);
        Gep biasAddress = new Gep(new PointerType(new IntegerType(32)),i32Pointer,curBlock);
        biasAddress.addIndex(new IntConstant(-1));
        return memoryLoad("array_size",biasAddress,curBlock);
    }

    private Value getStringPtr(Value raw){
        assert raw instanceof StringConstant;
        Gep ptr = new Gep(new PointerType(new IntegerType(8)),raw,curBlock);
        ptr.addIndex(new IntConstant(0)).addIndex(new IntConstant(0));
        return ptr;
    }

    private Value callStringOperator(Operator op,Value str1, Value str2){
        assert str1.type.isEqual(new PointerType(new IntegerType(8))) && str2.type.isEqual(new PointerType(new IntegerType(8)));
        Call returnValue;
        IRFunction calledFunction;
        switch(op){
            case add ->returnValue = new Call(calledFunction = funcTable.get("_str_splice"),curBlock);
            case eq -> returnValue = new Call(calledFunction = funcTable.get("_str_eq"),curBlock);
            case ne -> returnValue = new Call(calledFunction = funcTable.get("_str_ne"),curBlock);
            case slt -> returnValue = new Call(calledFunction = funcTable.get("_str_lt"),curBlock);
            case sle -> returnValue = new Call(calledFunction = funcTable.get("_str_le"),curBlock);
            case sgt -> returnValue = new Call(calledFunction = funcTable.get("_str_gt"),curBlock);
            case sge -> returnValue = new Call(calledFunction = funcTable.get("_str_ge"),curBlock);
            default -> throw new RuntimeException("[Debug] Unknown operator :(");
        }
        calledFunction.setUsed();
        returnValue.addArg(str1).addArg(str2);
        return returnValue;
    }

    private void addControl(IRBasicBlock cBlock, Value flag, IRBasicBlock tBlock, IRBasicBlock fBlock){
        assert !flag.type.isEqual(new IntegerType(1));
        Value _condition = new Trunc(flag,new IntegerType(1),cBlock);
        new Branch(cBlock,_condition,tBlock,fBlock);
    }
}