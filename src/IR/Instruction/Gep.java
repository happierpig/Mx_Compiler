package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.TypeSystem.ArrayType;
import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;

public class Gep extends IRInstruction{
    public int index;

    public Gep(String _name, Value _value, int _index, IRBasicBlock _block) {
        super(_name+"_gep", new PointerType(((ArrayType)_value.type.dePointed()).baseType), _block);
        this.addOperand(_value);
        this.index = _index;
    }

    @Override
    public String toString() {
        return this.getName() + " = getelementptr inbounds " + this.getOperand(0).type.dePointed().toString()
                + ", " + this.getOperand(0).getTypeName() + ", i32 0, i32 " + index;
    }
}