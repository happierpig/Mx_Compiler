package IR.TypeSystem;

public class ArrayType extends IRType{
    public IRType baseType;
    public int size;

    public ArrayType(IRType _type, int _size){
        this.baseType = _type;
        this.size = _size;
    }

    @Override
    public String toString() {
        return "[" + this.size + " x " + this.baseType.toString() + "]";
    }

    @Override
    public boolean isEqual(IRType other) {
        if(other instanceof ArrayType) return this.baseType.isEqual(((ArrayType)other).baseType)
                && this.size == ((ArrayType)other).size;
        else return false;
    }
}