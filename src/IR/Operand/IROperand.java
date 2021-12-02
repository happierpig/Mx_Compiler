package IR.Operand;

import IR.TypeSystem.*;

public abstract class IROperand{
    public IRType type;

    public IROperand(){
        this.type = null;
    }

    public IROperand(IRType _type){
        this.type = _type;
    }

    public abstract String toString();

    public abstract String getName();
}