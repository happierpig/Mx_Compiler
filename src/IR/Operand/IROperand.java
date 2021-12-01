package IR.Operand;

import IR.TypeSystem.*;

public abstract class IROperand{
    public IRType type;

    public IROperand(IRType _type){
        this.type = _type;
    }

    public abstract String toString();
}