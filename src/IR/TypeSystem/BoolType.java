package IR.TypeSystem;

public class BoolType extends IRType{
    public boolean inMem;

    public BoolType() {
        inMem = false;
    }

    public void setInMem(){
        inMem = true;
    }

    @Override
    public int byteSize() {
        return 1;
    }

    @Override
    public String toString() {
        return inMem ? "i8" : "i1";
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof BoolType) && (((BoolType) other).inMem == inMem);
    }
}