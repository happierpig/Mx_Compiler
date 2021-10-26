package AST;


import Utils.Position;

public class ArrayTypeNode extends TypeNode{
    public int dimSize;

    // visitArrayType :  by induction
    public ArrayTypeNode(TypeNode _base, Position _pos){
        super(_base.typeId,_pos);
        if(_base instanceof ArrayTypeNode){
            this.dimSize = 1 + ((ArrayTypeNode)_base).dimSize;
        }else{
            this.dimSize = 1;
        }
    }

    public ArrayTypeNode(String _baseType,int _dim,Position _pos){
        super(_baseType,_pos);
        this.dimSize = _dim;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}