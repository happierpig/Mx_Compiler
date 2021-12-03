package IR.Instruction;

import IR.Operand.IROperand;

public class Ret extends IRInstruction{
    public IROperand returnVal;

    public Ret(IROperand _retVal){
        this.returnVal = _retVal;
    }

    @Override
    public String toString() {
        return "ret " + (returnVal == null ? "void" : this.returnVal.toString());
    }
}