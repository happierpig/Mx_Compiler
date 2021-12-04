package IR.TypeSystem;

public class LabelType extends IRType{

    @Override
    public String toString() {
        return "label";
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof LabelType);
    }
}