package AST;

import Utils.Position;

public class ArrayAccessExprNode extends ExprNode{
    public ExprNode array;
    public ExprNode index;

    public ArrayAccessExprNode(ExprNode _array,ExprNode _index,Position _pos){
        super(_pos);
        this.array = _array;
        this.index = _index;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}