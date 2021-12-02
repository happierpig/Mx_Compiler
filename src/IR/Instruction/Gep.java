package IR.Instruction;

import IR.Operand.IROperand;
import IR.Operand.Register;
import IR.TypeSystem.Array;
import IR.TypeSystem.Pointer;

public class Gep extends IRInstruction{
    public Register destReg;
    public IROperand source;
    public int index;
    public Array arrayType;

    public Gep(Register _dest,IROperand _source,int _index){
        this.destReg = _dest;
        this.source = _source;
        this.index = _index;
        this.arrayType = (Array) this.source.type.dePointer();
        this.destReg.setType(new Pointer(this.arrayType.baseType));
    }

    @Override
    public String toString() {
        return this.destReg.getName() + " = getelementptr inbounds " + arrayType.toString() + ", " + source.type.toString() + " " + source.getName() + ", i32 0, i32 " + index;
    }
}