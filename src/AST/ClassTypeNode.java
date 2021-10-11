package AST;

import Utils.Position;

// base type class for array type and denote the baseType/class
public class ClassTypeNode extends TypeNode{
    public ClassTypeNode(String _id, Position _pos){
        super(_id,_pos);
    }
}