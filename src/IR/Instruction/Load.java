package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;

public class Load extends IRInstruction{

    public Load(String _name, Value _address, IRBasicBlock _block) {
        super(_name+".load",_address.type.dePointed() , _block);
        this.addOperand(_address);
    }

    @Override
    public String toString() {
        return this.getName() + " = load " + this.type.toString() + ", " + this.getOperand(0).getTypeName() + ", align " + this.type.byteSize();
    }
}