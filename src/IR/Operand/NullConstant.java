package IR.Operand;

import IR.TypeSystem.VoidType;

public class NullConstant extends IRConstant{

    public NullConstant() {
        super("null", new VoidType());
    }

    @Override
    public String getName() {
        throw new RuntimeException("[Debug] Why use toString in nullConstant ?");
    }

    @Override
    public String toString() {
        throw new RuntimeException("[Debug] Why use toString in nullConstant ?");
    }
}