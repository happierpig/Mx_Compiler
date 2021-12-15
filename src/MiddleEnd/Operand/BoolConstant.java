package MiddleEnd.Operand;

import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.BoolType;

public class BoolConstant extends IRConstant{
    public boolean value; // 1 for True and 0 for False

    // Boolean use 1 bit cause bool in use is 1 bit
    public BoolConstant(boolean _flag){
        super("_bool_const",new BoolType());
        this.value = _flag;
    }

    @Override
    public String getName() {
        return value ? String.valueOf(1) : String.valueOf(0);
    }

    @Override
    public String toString() {
        throw new RuntimeException("[Debug] Why use toString in boolConstant ?");
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}