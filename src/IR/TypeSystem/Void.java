package IR.TypeSystem;

public class Void extends IRType{

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof Void);
    }
}