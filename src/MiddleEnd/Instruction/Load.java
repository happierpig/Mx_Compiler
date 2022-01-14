package MiddleEnd.Instruction;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.Infrastructure.IRVisitor;

public class Load extends IRInstruction{

    public Load(String _name, Value _address, IRBasicBlock _block) {
        super(_name+".load",_address.type.dePointed() , _block);
        this.addOperand(_address);
    }

    @Override
    public String toString() {
        return this.getName() + " = load " + this.type.toString() + ", " + this.getOperand(0).getTypeName() + ", align " + this.type.byteSize();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}