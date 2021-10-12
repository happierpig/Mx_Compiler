package AST;

import Utils.Position;

public class VoidTypeNode extends TypeNode{
    public VoidTypeNode(Position _pos){
        super("void",_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}