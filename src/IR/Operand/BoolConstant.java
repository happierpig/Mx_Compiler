package IR.Operand;

import IR.TypeSystem.IntegerType;

public class BoolConstant extends IRConstant{
    public boolean value; // 1 for True and 0 for False

    // Boolean use 1 bit cause bool in use is 1 bit (while in memory is 8 bits)
    public BoolConstant(boolean _flag){
        super("_bool_const",new IntegerType(1));
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