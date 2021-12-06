package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.TypeSystem.IRType;

public class Trunc extends IRInstruction{

    public Trunc(String _name, Value _origin, IRType targetTy, IRBasicBlock _block) {
        super(_name+".trunc", targetTy, _block);
        this.addOperand(_origin);
    }

    @Override
    public String toString() {
        return this.getName() + " = trunc " + this.getOperand(0).getTypeName() + " to " + this.type.toString();
    }
}