package IR.Infrastructure;

import AST.*;


public class IRBuilder implements ASTVisitor{

    @Override
    public void visit(RootNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(VarDefNode node) {

    }

    @Override
    public void visit(VarDefStmtNode node) {
    }

    @Override
    public void visit(ClassDefNode node) {
    }

    @Override
    public void visit(FuncDefNode node) {
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

    @Override public void visit(ArrayTypeNode node) {}
    @Override public void visit(BoolConstantExprNode node) {}
    @Override public void visit(ClassTypeNode node) {}
    @Override public void visit(IntConstantExprNode node) {}
    @Override public void visit(NullConstantExprNode node) {}
    @Override public void visit(StringConstantExprNode node) {}
    @Override public void visit(VoidTypeNode node) {}

}