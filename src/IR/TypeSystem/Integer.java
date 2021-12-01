package IR.TypeSystem;


public class Integer extends IRType{
    public final int bitWidth;

    public Integer(int width){
        this.bitWidth = width;
    }

    @Override
    public String toString() {
        return "i" + this.bitWidth;
    }

    @Override
    public boolean isEqual(IRType other) {
        if(other instanceof Integer) return ((Integer) other).bitWidth == this.bitWidth;
        else return false;
    }
}