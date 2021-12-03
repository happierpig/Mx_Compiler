package IR.Operand;

import IR.TypeSystem.Integer;

public class IntConstant extends IRConstant{
    public int value;
    public IntConstant(int _value){
        super(new Integer(32));
        this.value = _value;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.value;
    }

    @Override
    public String getName() {
        throw new RuntimeException("[Debug] get int constant name");
    }
}