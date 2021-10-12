package AST;

import Utils.Position;

public class IdentifierExprNode extends ExprNode{

    private String identifier;
    public IdentifierExprNode(String _id,Position _pos){
        super(_pos);
        this.identifier = _id;
    }

    public String getIdentifier(){
        return this.identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}