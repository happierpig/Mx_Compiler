package AST;

import Utils.Position;
public abstract class TypeNode extends ASTNode {
    public String typeId;

    public TypeNode(String _id,Position _pos){
        super(_pos);
        this.typeId = _id;
    }

    public boolean isEqual(TypeNode other){
        if(this instanceof ClassTypeNode && other instanceof ClassTypeNode) return this.typeId.equals(other.typeId);
        if(this instanceof ArrayTypeNode && other instanceof ArrayTypeNode) return this.typeId.equals(other.typeId) && ((ArrayTypeNode) this).dimSize == ((ArrayTypeNode)other).dimSize;
        return (this instanceof VoidTypeNode) && (other instanceof VoidTypeNode);
    }
}