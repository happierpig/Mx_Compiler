package MiddleEnd.TypeSystem;

public class VoidType extends IRType{

    @Override
    public int byteSize() {
        throw new RuntimeException("[Debug] Why use byteSize in void type ?");
    }

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof VoidType);
    }
}