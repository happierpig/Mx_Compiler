package AST;

import Utils.Position;

public class ExprStmtNode extends StmtNode{
    private ExprNode expr;
    public ExprStmtNode(ExprNode _expr,Position _pos){
        super(_pos);
        this.expr = _expr;
    }
}