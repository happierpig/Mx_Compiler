package MiddleEnd.TypeSystem;

import java.util.ArrayList;

public class FunctionType extends IRType{
    public IRType returnType;
    public ArrayList<IRType> parametersType;
    public ArrayList<String> parametersName;

    public FunctionType(IRType _returnTy){
        this.returnType = _returnTy;
        this.parametersType = new ArrayList<>();
        this.parametersName = new ArrayList<>();
    }

    public void addParameters(IRType _ty, String _name){
        this.parametersType.add(_ty);
        this.parametersName.add(_name);
    }

    @Override
    public int byteSize() {
        throw new RuntimeException("[Debug] Why use byteSize in function type ?");
    }

    @Override
    public String toString() {
        return returnType.toString();
    }

    @Override
    public boolean isEqual(IRType other) {
        throw new RuntimeException("[Debug] Why use isEqual in Function ?");
    }
}