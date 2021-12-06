package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.TypeSystem.IRType;

public class Zext extends IRInstruction{

    public Zext(Value _origin, IRType targetTy, IRBasicBlock _block) {
        super("zext", targetTy, _block);
        this.addOperand(_origin);
    }

    @Override
    public String toString() {
        return this.getName() + " = zext " + this.getOperand(0).getTypeName() + " to " + this.type.toString();
    }
}