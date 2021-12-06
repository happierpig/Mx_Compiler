package IR.Instruction;

import IR.IRBasicBlock;
import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;

public class GlobalDef extends IRInstruction{

    public GlobalDef(String _name, IRType _type) {
        super(_name+"_glo", new PointerType(_type), null);
    }

    @Override
    public String getName() {
        return "@" + this.name;
    }

    // toString for instruction is to print llvm
    @Override
    public String toString() {
        return this.getName() + " = global " + this.type.dePointed().toString() + " zeroinitializer, align " + this.type.dePointed().byteSize();
    }
}