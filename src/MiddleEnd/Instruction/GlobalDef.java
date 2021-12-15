package MiddleEnd.Instruction;

import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.IRType;
import MiddleEnd.TypeSystem.PointerType;

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

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}