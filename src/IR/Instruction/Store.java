package IR.Instruction;


import IR.Operand.IROperand;

public class Store extends IRInstruction{
    public IROperand value;
    public IROperand address;

    public Store(IROperand _value,IROperand _address){
        this.value = _value;
        this.address = _address;
    }

    @Override
    public String toString() {
        return "store " + this.value.toString() + ", " + this.address.toString();
    }
}