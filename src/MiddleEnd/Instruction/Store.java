package MiddleEnd.Instruction;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.VoidType;

public class Store extends IRInstruction{

    public Store(Value _value, Value _address, IRBasicBlock _block) {
        super("_store", new VoidType(), _block);
        this.addOperand(_value); // 0 for value
        this.addOperand(_address); // 1 for address
    }

    @Override
    public String getName() {
        throw new RuntimeException("[Debug] Why get name in Store ? ");
    }

    @Override
    public String toString() {
        return "store " + this.getOperand(0).getTypeName() + ", " + this.getOperand(1).getTypeName() + ", align " + this.getOperand(0).type.byteSize();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}