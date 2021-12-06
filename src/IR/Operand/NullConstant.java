package IR.Operand;

import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;
import IR.TypeSystem.VoidType;

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
}