package MiddleEnd.Operand;

import MiddleEnd.BaseClass.User;
import MiddleEnd.BaseClass.Value;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.IRType;

/*
    This class is used for memory to register optimization.
    Each Temporary represents a virtual register in assembly.
 */
public class Temporary extends User {

    public Temporary(IRType _type) {
        super("mem2reg_", _type);
    }

    @Override
    public String toString() {
        throw new RuntimeException("[Debug] Why use toString in Temporary ?");
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

}