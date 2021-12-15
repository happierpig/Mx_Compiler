package MiddleEnd.Instruction;

import MiddleEnd.IRBasicBlock;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.IRType;
import MiddleEnd.BaseClass.Value;

public class Bitcast extends IRInstruction{

    public Bitcast(Value baseValue, IRType targetType, IRBasicBlock _block) {
        super(baseValue.name+"_BC", targetType, _block);
        this.addOperand(baseValue);
    }

    @Override
    public String toString() {
        return this.getName() + " = bitcast " + this.getOperand(0).getTypeName() + " to " + this.type.toString();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}