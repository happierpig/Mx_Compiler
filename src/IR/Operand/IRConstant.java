package IR.Operand;

import IR.BaseClass.User;
import IR.TypeSystem.IRType;

public abstract class IRConstant extends User {
    public IRConstant(String name, IRType _type) {
        super(name, _type);
    }
}