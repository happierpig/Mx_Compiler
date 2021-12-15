package MiddleEnd.Operand;

import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.IRType;
import MiddleEnd.TypeSystem.PointerType;
import MiddleEnd.TypeSystem.VoidType;

public class NullConstant extends IRConstant{

    public NullConstant() {
        super("null", new PointerType(new VoidType())); // must be pointer type
    }

    public NullConstant(PointerType _ty) {
        super("null", _ty); // must be pointer type
    }

    // used in every occasion when nullptr is used;
    public void setType(IRType _ty){
        this.type = _ty;
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public String toString() {
        throw new RuntimeException("[Debug] Why use toString in nullConstant ?");
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}