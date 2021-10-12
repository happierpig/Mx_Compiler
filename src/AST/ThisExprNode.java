package AST;

import Utils.Position;

public class ThisExprNode extends ExprNode{
    public ThisExprNode(Position _pos){
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}