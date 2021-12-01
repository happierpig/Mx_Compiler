package IR.TypeSystem;

public class Pointer extends IRType{
    public IRType baseType;
    public int dimSize;

    public Pointer(IRType _type){
        if(_type instanceof Pointer){
            this.baseType = ((Pointer) _type).baseType;
            this.dimSize = ((Pointer) _type).dimSize + 1;
        }else{
            this.baseType = _type;
            this.dimSize = 1;
        }
    }

    public Pointer(IRType _type,int _dim){
        this.baseType = _type;
        this.dimSize = _dim;
    }

    @Override
    public String toString() {
        return this.baseType.toString() + "*".repeat(this.dimSize);
    }

    @Override
    public boolean isEqual(IRType other) {
        if(other instanceof Pointer) return this.baseType.isEqual(((Pointer)other).baseType) && this.dimSize == ((Pointer)other).dimSize;
        else return false;
    }
}