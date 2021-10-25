package AST;


import Utils.Position;

public class ArrayTypeNode extends TypeNode{
    // record baseType and array dimension.
    public TypeNode noArrType;
    public int dimSize;

    // visitArrayType : iteration operation
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

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}