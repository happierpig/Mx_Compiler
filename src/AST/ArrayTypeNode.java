package AST;


import Utils.Position;

public class ArrayTypeNode extends TypeNode{
    // record baseType and array dimension.
    private TypeNode noArrType;
    private int dimSize;

    public ArrayTypeNode(TypeNode _base, Position _pos){
        super(_base.getType(),_pos);
        if(_base instanceof ArrayTypeNode){
            this.noArrType = ((ArrayTypeNode)_base).noArrType;
            this.dimSize = 1 + ((ArrayTypeNode)_base).dimSize;
        }else{
            this.noArrType = _base;
            this.dimSize = 1;
        }
    }
}