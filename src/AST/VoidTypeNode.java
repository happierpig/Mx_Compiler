package AST;

import Utils.Position;

public class VoidTypeNode extends TypeNode{
    public VoidTypeNode(Position _pos){
        super("void",_pos);
    }
}