package IR.TypeSystem;

public class IntegerType extends IRType{
    public int width;

    public IntegerType(int _width){
        this.width = _width;
    }

    @Override
    public int byteSize() {
        return this.width / 8;
    }

    @Override
    public int alignSize() {
        return this.width / 8;
    }

    @Override
    public String toString() {
        return "i" + this.width;
    }

    @Override
    public boolean isEqual(IRType other) {
        if(other instanceof IntegerType) return ((IntegerType) other).width == this.width;
        else return false;
    }
}