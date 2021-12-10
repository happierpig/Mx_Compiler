package IR.TypeSystem;

public class StructType extends IRType{

    @Override
    public int byteSize() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean isEqual(IRType other) {
        return false;
    }
}