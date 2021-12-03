package IR.Instruction;

import IR.Operand.GlobalVariable;
import IR.Operand.IRConstant;
import IR.Operand.IROperand;
import IR.TypeSystem.IRType;
import IR.TypeSystem.Pointer;

public class GlobalDef extends IRInstruction{
    //todo: global variable init process
    public GlobalVariable destVal;
    public IRType type;
    public IRConstant initValue;

    public GlobalDef(GlobalVariable _dest,IRType _type){
        this.destVal = _dest;
        this.type = _type;
        this.destVal.setType(new Pointer(this.type));
        this.initValue = null;
    }

    public void setInitValue(IRConstant _initvalue){
        this.initValue = _initvalue;
    }

    @Override
    public String toString() {
        if(this.initValue == null) return this.destVal.toString() + " = global " + this.type.toString() + " zeroinitializer";
        else return this.destVal.toString() + " = global " + " " + initValue.toString();
    }
}