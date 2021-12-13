package MiddleEnd.TypeSystem;

public class LabelType extends IRType{

    @Override
    public int byteSize() {
        throw new RuntimeException("[Debug] Why use byteSize in label type ?");
    }

    @Override
    public String toString() {
        return "label";
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof LabelType);
    }
}