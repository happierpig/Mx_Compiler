package IR.TypeSystem;


public abstract class IRType{

    public abstract String toString();

    public abstract boolean isEqual(IRType other);

    public IRType dePointer(){
        if(this instanceof Pointer){
            if(((Pointer) this).dimSize == 1) return ((Pointer)this).baseType;
            else return new Pointer(((Pointer) this).baseType,((Pointer) this).dimSize-1);
        }else throw new RuntimeException("[Debug] address not pointer");
    }
}