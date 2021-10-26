package AST;

import Utils.Position;

public class ExprStmtNode extends StmtNode{
    public ExprNode expr;
    public ExprStmtNode(ExprNode _expr,Position _pos){
        super(_pos);
        this.expr = _expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}