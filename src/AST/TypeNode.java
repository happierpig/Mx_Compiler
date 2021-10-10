package AST;

import Utils.Position;

public abstract class TypeNode extends ASTNode{
    private String typeId;

    public TypeNode(String _id,Position _pos){
        super(_pos);
        this.typeId = _id;
    }

    public String getType(){
        return typeId;
    }
}