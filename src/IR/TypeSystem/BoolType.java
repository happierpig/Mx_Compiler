package IR.TypeSystem;

public class BoolType extends IRType{

    @Override
    public int byteSize() {
        return 1;
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