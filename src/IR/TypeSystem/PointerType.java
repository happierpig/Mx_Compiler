package IR.TypeSystem;

public class PointerType extends IRType{
    public IRType baseType;
    public int dimSize;

    public PointerType(IRType _base){
        if(_base instanceof PointerType){
            this.baseType = ((PointerType) _base).baseType;
            this.dimSize = ((PointerType) _base).dimSize + 1;
        }else{
            this.baseType = _base;
            this.dimSize = 1;
        }
    }

    public PointerType(IRType _base, int _dimSize){
        this.baseType = _base;
        this.dimSize = _dimSize;
    }

    @Override
    public int byteSize() {
        return 8;
    }

    @Override
    public String toString() {
        return this.baseType.toString() + "*".repeat(this.dimSize);
    }

    @Override
    public boolean isEqual(IRType other) {
        if(other instanceof PointerType) return this.baseType.isEqual(((PointerType)other).baseType)
                && this.dimSize == ((PointerType)other).dimSize;
        else return false;
    }
}