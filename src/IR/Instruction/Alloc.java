package IR.Instruction;

import IR.IRBasicBlock;
import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;

public class Alloc extends IRInstruction{
    public Alloc(String _name, IRType _type, IRBasicBlock _block) {
        super(_name+".alloc", new PointerType(_type), _block);
    }

    @Override
    public String toString() {
        return this.getName() + " = alloca " + this.type.dePointed().toString() + ", align " + this.type.dePointed().byteSize();
    }
}