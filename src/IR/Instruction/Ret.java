package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.TypeSystem.IRType;
import IR.TypeSystem.VoidType;

public class Ret extends IRInstruction{

    public Ret(Value _returnValue, IRBasicBlock _block) {
        super("_ret",_returnValue.type, _block);
        this.addOperand(_returnValue);
    }

    @Override
    public String getName() {
        throw new RuntimeException("[Debug] Why get name in Ret ? ");
    }

    @Override
    public String toString() {
        return "ret " + ((this.type instanceof VoidType) ? this.type.toString() : this.getOperand(0).getTypeName());
    }
}