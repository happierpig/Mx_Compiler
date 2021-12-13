package MiddleEnd.Operand;

import MiddleEnd.BaseClass.User;
import MiddleEnd.TypeSystem.IRType;

public abstract class IRConstant extends User {
    public IRConstant(String name, IRType _type) {
        super(name, _type);
    }
}