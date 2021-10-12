package AST;

import Utils.Position;

public class NullConstantExprNode extends ExprNode{
    public NullConstantExprNode(Position _pos){
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}