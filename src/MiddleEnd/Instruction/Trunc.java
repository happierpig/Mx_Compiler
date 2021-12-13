package MiddleEnd.Instruction;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.TypeSystem.IRType;

public class Trunc extends IRInstruction{

    public Trunc(Value _origin, IRType targetTy, IRBasicBlock _block) {
        super("trunc_", targetTy, _block);
        this.addOperand(_origin);
    }

    @Override
    public String toString() {
        return this.getName() + " = trunc " + this.getOperand(0).getTypeName() + " to " + this.type.toString();
    }
}