package IR.TypeSystem;


public abstract class IRType{

    public abstract String toString();

    public abstract boolean isEqual(IRType other);
}