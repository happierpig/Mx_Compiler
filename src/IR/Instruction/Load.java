package IR.Instruction;


import IR.Operand.IROperand;
import IR.Operand.Register;
import IR.TypeSystem.IRType;
import IR.TypeSystem.Pointer;

public class Load extends IRInstruction{
    public Register destReg;
    public IROperand address;

    public Load(Register _dest,IROperand _addr){
        this.destReg = _dest;
        this.address = _addr;
        this.destReg.setType(_addr.type.dePointer());
    }

    @Override
    public String toString() {
        return this.destReg.getName() + " = load " + this.destReg.type.toString() + ", " + this.address.toString();
    }
}