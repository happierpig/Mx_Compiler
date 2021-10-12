package AST;

import Utils.Position;

// base type class for array type and denote the int/string/bool/string_identifier
public class ClassTypeNode extends TypeNode{
    public ClassTypeNode(String _id, Position _pos){
        super(_id,_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}