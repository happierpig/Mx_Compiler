package IR.Operand;

import IR.TypeSystem.IntegerType;

public class BoolConstant extends IRConstant{
    public boolean value; // 1 for True and 0 for False

    public BoolConstant(boolean _flag){
        super("_bool_const",new IntegerType(8));
        this.value = _flag;
    }

    @Override
    public String getName() {
        return String.valueOf(this.value);
    }

    @Override
    public String toString() {
        throw new RuntimeException("[Debug] Why use toString in boolConstant ?");
    }
}