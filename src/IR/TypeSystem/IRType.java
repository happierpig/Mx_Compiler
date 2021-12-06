package IR.TypeSystem;

public abstract class IRType{
    public abstract int byteSize();
    public abstract String toString();
    public abstract boolean isEqual(IRType other);
    public IRType dePointed(){
        if(this instanceof PointerType){
            if(((PointerType) this).dimSize == 1) return ((PointerType)this).baseType;
            else return new PointerType(((PointerType) this).baseType,((PointerType) this).dimSize-1);
        }else throw new RuntimeException("[Debug] address not PointerType");
    }
}