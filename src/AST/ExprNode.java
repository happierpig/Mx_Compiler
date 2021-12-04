package AST;

import Utils.Position;

public abstract class ExprNode extends ASTNode{
    public TypeNode exprType;
    public boolean isAssignable;

    public ExprNode(Position _pos){
        super(_pos);
        this.exprType = null;
        this.isAssignable = false;
    }
}
