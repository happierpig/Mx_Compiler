package IR.TypeSystem;

import java.util.ArrayList;

public class FunctionType extends IRType{
    public IRType returnType;
    public ArrayList<IRType> parameters;

    public FunctionType(IRType _returnTy){
        this.returnType = _returnTy;
        this.parameters = new ArrayList<>();
    }

    public void addParameters(IRType _ty){
        this.parameters.add(_ty);
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