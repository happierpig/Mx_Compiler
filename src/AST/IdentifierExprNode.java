package AST;

import Utils.Position;

public class IdentifierExprNode extends ExprNode{
    public String identifier;

    public IdentifierExprNode(String _id,Position _pos){
        super(_pos);
        this.identifier = _id;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}