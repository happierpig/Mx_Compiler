package MiddleEnd.TypeSystem;

public class BoolType extends IRType{

    @Override
    public int byteSize() {
        return 4;   // for asm
    }

    @Override
    public String toString() {
        return "i8";
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof BoolType);
    }
}