package IR.Infrastructure;

import AST.*;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRModule;
import IR.Instruction.*;
import IR.Operand.*;
import IR.TypeSystem.IRType;
import IR.TypeSystem.Integer;
import IR.TypeSystem.Pointer;
import IR.TypeSystem.Void;
import Utils.GlobalScope;
import java.util.HashMap;


public class IRBuilder implements ASTVisitor{
    public GlobalScope gScope;
    public IRScope cScope;
    public HashMap<String, IRType> typeTable;
    public IRBasicBlock cBlock;
    public IRFunction cFunction;

    public IRModule targetModule;

    public IRBuilder(GlobalScope _gScope,IRModule _module){
        this.targetModule = _module;
        this.gScope = _gScope;
        this.cScope = new IRScope(null);
        typeTable = new HashMap<>();
        gScope.Class_Table.forEach((className,classScope)->{
            switch (className) {
                case "int" -> typeTable.put("int", new Integer(32));
                case "bool" -> typeTable.put("bool", new Integer(8));
                case "string" -> typeTable.put("string", new Pointer(new Integer(8)));
                //todo :
//                default -> typeTable.put(className,)
            }
        });
        typeTable.put("void",new Void());
    }

    @Override
    public void visit(RootNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(VarDefNode node) {
        if(cScope.parent == null){    // global
            GlobalVariable operand = new GlobalVariable(node.identifier);
            GlobalDef instr = new GlobalDef(operand,this.typeTable.get(node.varType.typeId));
            this.cScope.setVariable(node.identifier,operand);
            this.targetModule.globalDefs.add(instr);
            if(node.initValue != null){
                node.initValue.accept(this);
                if(node.initValue.operand instanceof IRConstant){
                    instr.setInitValue((IRConstant) node.initValue.operand);
                }else{
                    //todo : add global init
                }
            }
        }else{
            Register operand = new Register(null);
            Alloc alloc_instr = new Alloc(operand,this.typeTable.get(node.varType.typeId));
            this.cScope.setVariable(node.identifier,operand);
            this.cBlock.addInstruction(alloc_instr);
            if(node.initValue != null){
                node.initValue.accept(this);
                if(node.initValue.operand instanceof IRConstant){
                    Store store_instr = new Store(node.initValue.operand,operand);
                    this.cBlock.addInstruction(store_instr);
                }
                //todo :
            }
        }
    }

    @Override
    public void visit(VarDefStmtNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(ClassDefNode node) {
    }

    @Override
    public void visit(FuncDefNode node) {
        this.cScope  = new IRScope(this.cScope);
        Register.refresh();
        this.cFunction = new IRFunction(node.identifier);
        this.cFunction.returnType = this.typeTable.get(node.funcType.typeId);
        this.cFunction.entryBlock = new IRBasicBlock(node.identifier);
        this.cFunction.exitBlock = new IRBasicBlock(node.identifier);
        cBlock = cFunction.entryBlock;
        if(node.parameterList != null){
            node.parameterList.forEach(para->{
                Register paraReg = new Register(typeTable.get(para.varType.typeId));
                Register realReg = new Register(null);
                Alloc allocInstr = new Alloc(realReg, paraReg.type);
                Store loadInstr = new Store(paraReg,realReg);
                cScope.setVariable(para.identifier,realReg);
                cBlock.addInstruction(allocInstr).addInstruction(loadInstr);
            });
        }
        if(!node.funcType.typeId.equals("void")){
            cFunction.returnAddress = new Register(null);
            Alloc allocInstr = new Alloc(cFunction.returnAddress,cFunction.returnType);
            cBlock.addInstruction(allocInstr);
            Register tmpReg = new Register(null);
            Load loadInstr = new Load(tmpReg,cFunction.returnAddress);
            Ret retInstr = new Ret(tmpReg);
            cFunction.exitBlock.addInstruction(loadInstr).addInstruction(retInstr);
        }else {
            cFunction.returnAddress = null;
            Ret retInstr = new Ret(null);
            cFunction.exitBlock.addInstruction(retInstr);
        }
        if(node.funcBody.stmtList != null) node.funcBody.stmtList.forEach(tmp->tmp.accept(this));
        targetModule.addFunction(this.cFunction);
        this.cScope = this.cScope.parent;
    }

    @Override
    public void visit(LambdaExprNode node) {
    }

    @Override
    public void visit(ReturnStmtNode node) {
    }

    @Override
    public void visit(IdentifierExprNode node) {
    }

    @Override
    public void visit(NewExprNode node) {
    }

    @Override
    public void visit(ObjectMemberExprNode node) {
    }

    @Override
    public void visit(FuncCallExprNode node) {
    }

    @Override
    public void visit(ArrayAccessExprNode node) {
    }

    @Override
    public void visit(MonoExprNode node) {
    }

    @Override
    public void visit(BinaryExprNode node) {
    }

    @Override
    public void visit(ThisExprNode node) {
    }

    @Override
    public void visit(ExprStmtNode node) {
    }

    @Override
    public void visit(BlockStmtNode node) {

    }

    @Override
    public void visit(BreakStmtNode node) {
    }

    @Override
    public void visit(ContinueStmtNode node) {
    }

    @Override
    public void visit(ForStmtNode node) {
    }

    @Override
    public void visit(WhileStmtNode node) {
    }

    @Override
    public void visit(IfStmtNode node) {
    }

    @Override
    public void visit(BoolConstantExprNode node) {

    }

    @Override
    public void visit(IntConstantExprNode node) {
        node.operand = new IntConstant(node.value);
    }
    @Override
    public void visit(StringConstantExprNode node) {
        node.operand = new StringConstant(node.value);
        this.targetModule.strings.add((StringConstant) node.operand);
    }

    @Override public void visit(ArrayTypeNode node) {}

    @Override public void visit(ClassTypeNode node) {}

    @Override public void visit(NullConstantExprNode node) {}

    @Override public void visit(VoidTypeNode node) {}

}