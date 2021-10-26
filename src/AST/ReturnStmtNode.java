package AST;

import Utils.Position;

public class ReturnStmtNode extends StmtNode{
    public ExprNode returnVal;
    public ReturnStmtNode(ExprNode _returnVal, Position _pos){
        super(_pos);
        this.returnVal = _returnVal;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}