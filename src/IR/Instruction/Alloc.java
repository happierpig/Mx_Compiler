package IR.Instruction;

import IR.Operand.Register;
import IR.TypeSystem.IRType;
import IR.TypeSystem.Pointer;

public class Alloc extends IRInstruction{
    public Register destReg;
    public IRType type;

    public Alloc(Register _destReg,IRType _type){
        this.destReg = _destReg;
        this.type = _type;
        this.destReg.setType(new Pointer(_type));
    }

    @Override
    public String toString() {
        return this.destReg.getName() + " = alloca " + this.type.toString();
    }
}