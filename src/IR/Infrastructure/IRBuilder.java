package IR.Infrastructure;

import AST.*;
import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRModule;
import IR.Instruction.*;
import IR.Operand.BoolConstant;
import IR.Operand.IntConstant;
import IR.Operand.NullConstant;
import IR.Operand.StringConstant;
import IR.TypeSystem.*;
import Utils.GlobalScope;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor {
    public IRModule targetModule;
    public GlobalScope gScope;
    public IRScope cScope;
    public HashMap<String, IRType> typeTable;
    public HashMap<String, IRFunction> funcTable;
    public IRBasicBlock curBlock;
    public IRFunction curFunction;

    public IRBuilder(IRModule _module, GlobalScope _gScope){
        this.targetModule = _module;
        this.gScope = _gScope;
        this.cScope = new IRScope(null);
        this.typeTable = new HashMap<>();
        this.funcTable = new HashMap<>();
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
            funcTable.put(funcName,new IRFunction(funcName,funcType));
        });

        //todo : collect Class information
    }

    @Override
    public void visit(RootNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(IntConstantExprNode node) {
        node.operand = new IntConstant(node.value);
    }

    @Override
    public void visit(BoolConstantExprNode node) {
        node.operand = new BoolConstant(node.value);
    }

    @Override
    public void visit(StringConstantExprNode node) {
        //todo : check repeated constant declaration
        StringConstant stringLiteral = new StringConstant(processRaw(node.value));
        targetModule.addString(stringLiteral);
        node.operand = stringLiteral;
    }

    @Override
    public void visit(NullConstantExprNode node) {
        node.operand = new NullConstant();
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
        node.operand = value;
        if(node.initValue != null){ // init
            if(cScope.parent != null){ // local variable
                node.initValue.accept(this);
                Value initValue;
                if(!(node.initValue.operand instanceof NullConstant)) {
                    if (node.initValue.operand instanceof StringConstant) {
                        initValue = new Gep(node.identifier, node.initValue.operand, 0, curBlock);
                    }else initValue = node.initValue.operand;
                    this.memoryStore(initValue,value);
                }
            }else{
                //todo : global value init;
            }
        }
    }

    @Override
    public void visit(VarDefStmtNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(IdentifierExprNode node) {
        node.operand = memoryLoad(node.identifier,cScope.fetchValue(node.identifier));
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
        Call newOperand = new Call(func,curBlock);
        if(node.AryList != null) node.AryList.forEach(tmp->{
            tmp.accept(this);
            newOperand.addArg(tmp.operand);
        });
        node.operand = newOperand;
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if(node.returnVal != null){
            Value returnValue;
            node.returnVal.accept(this);
            returnValue = node.returnVal.operand;
            this.memoryStore(returnValue,curFunction.returnAddress);
        }
        new Branch(curBlock,curFunction.exitBlock());
        curBlock = null;
    }

    @Override
    public void visit(FuncDefNode node) {
        curFunction = funcTable.get(node.identifier);
        IRBasicBlock tmpEntry = new IRBasicBlock(node.identifier,curFunction); // entry-Block
        IRBasicBlock tmpExit = new IRBasicBlock(node.identifier,curFunction); // exit-Block
        cScope = new IRScope(cScope);
        Value.refresh();
        Value tmpReturnValue;
        if(!curFunction.type.toString().equals("void")){
            curFunction.returnAddress = new Alloc("_exit_address",((FunctionType)curFunction.type).returnType,tmpEntry);
            tmpReturnValue = new Load("_return_value",curFunction.returnAddress,tmpExit);
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
            if(curBlock == null) curBlock = new IRBasicBlock(node.identifier,curFunction);
            stmt.accept(this);
        });

        curBlock = null;
        cScope = cScope.parent;
        targetModule.addFunction(curFunction);
    }

    @Override
    public void visit(ArrayAccessExprNode node) {

    }

    @Override
    public void visit(ArrayTypeNode node) {

    }

    @Override
    public void visit(BinaryExprNode node) {

    }

    @Override
    public void visit(BlockStmtNode node) {

    }

    @Override
    public void visit(BreakStmtNode Node) {

    }

    @Override
    public void visit(ClassDefNode Node) {

    }

    @Override
    public void visit(ClassTypeNode node) {

    }

    @Override
    public void visit(ContinueStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {

    }

    @Override
    public void visit(ForStmtNode node) {

    }

    @Override
    public void visit(IfStmtNode node) {

    }

    @Override
    public void visit(MonoExprNode node) {

    }

    @Override
    public void visit(NewExprNode node) {

    }

    @Override
    public void visit(ObjectMemberExprNode node) {

    }

    @Override
    public void visit(ThisExprNode node) {

    }

    @Override
    public void visit(VoidTypeNode node) {

    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(LambdaExprNode node) {

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

    private Load memoryLoad(String identifier, Value address){
        return new Load(identifier,address,curBlock);
    }

    private void memoryStore(Value value, Value address){new Store(value,address,curBlock);}


}