package IR.TypeSystem;

public class VoidType extends IRType{

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof VoidType);
    }
}