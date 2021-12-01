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
        this.destReg.setType(this.dePointer(_addr.type));
    }

    @Override
    public String toString() {
        return this.destReg.getName() + " = load " + this.destReg.type.toString() + ", " + this.address.toString();
    }

    public IRType dePointer(IRType _source){
        if(_source instanceof Pointer){
            if(((Pointer) _source).dimSize == 1) return ((Pointer)_source).baseType;
            else return new Pointer(((Pointer) _source).baseType,((Pointer) _source).dimSize-1);
        }else throw new RuntimeException("[Debug] address not pointer");
    }
}