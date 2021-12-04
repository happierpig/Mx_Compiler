package IR.Operand;

import IR.TypeSystem.IntegerType;

public class IntConstant extends IRConstant{
    public int value;

    public IntConstant(int _value) {
        super("_int_const", new IntegerType(32));
        this.value = _value;
    }

    @Override
    public String getName() {
        return String.valueOf(this.value);
    }

    @Override
    public String toString() {
        throw new RuntimeException("[Debug] Why use toString in integerConst ?");
    }
}