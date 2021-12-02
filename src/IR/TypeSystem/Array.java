package IR.TypeSystem;

public class Array extends IRType{
    public IRType baseType; // maybe recursive
    public int size;

    public Array(IRType _type,int _size){
        this.baseType = _type;
        this.size = _size;
    }

    @Override
    public String toString() {
        return "[" + this.size + " x " + this.baseType.toString() + "]";
    }

    @Override
    public boolean isEqual(IRType other) {
        if(other instanceof Array) return this.baseType.isEqual(((Array)other).baseType) && this.size == ((Array)other).size;
        else return false;
    }
}