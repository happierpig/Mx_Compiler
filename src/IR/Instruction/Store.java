package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.Operand.BoolConstant;
import IR.TypeSystem.IRType;
import IR.TypeSystem.VoidType;

public class Store extends IRInstruction{

    public Store(Value _value, Value _address, IRBasicBlock _block) {
        super("_store", new VoidType(), _block);
        this.addOperand(_value);
        this.addOperand(_address);
    }

    @Override
    public String getName() {
        throw new RuntimeException("[Debug] Why get name in Store ? ");
    }

    @Override
    public String toString() {
        return "store " + this.getOperand(0).getTypeName() + ", " + this.getOperand(1).getTypeName() + ", align " + this.getOperand(0).type.byteSize();
    }
}