package IR.Operand;

import IR.TypeSystem.IRType;

public abstract class IRConstant extends IROperand{
    public IRConstant(){
        super(null);
    }
    public IRConstant(IRType _type){
        super(_type);
    }
}