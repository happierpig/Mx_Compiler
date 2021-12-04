package IR.Infrastructure;

import AST.*;

public class IRBuilder implements ASTVisitor {
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
    public void visit(BoolConstantExprNode node) {

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
    public void visit(FuncCallExprNode node) {

    }

    @Override
    public void visit(FuncDefNode node) {

    }

    @Override
    public void visit(IdentifierExprNode node) {

    }

    @Override
    public void visit(IfStmtNode node) {

    }

    @Override
    public void visit(IntConstantExprNode node) {

    }

    @Override
    public void visit(MonoExprNode node) {

    }

    @Override
    public void visit(NewExprNode node) {

    }

    @Override
    public void visit(NullConstantExprNode node) {

    }

    @Override
    public void visit(ObjectMemberExprNode node) {

    }

    @Override
    public void visit(ReturnStmtNode node) {

    }

    @Override
    public void visit(RootNode node) {

    }

    @Override
    public void visit(StringConstantExprNode node) {

    }

    @Override
    public void visit(ThisExprNode node) {

    }

    @Override
    public void visit(VarDefStmtNode node) {

    }

    @Override
    public void visit(VarDefNode node) {

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
}