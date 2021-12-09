package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.IRFunction;
import IR.TypeSystem.FunctionType;
import IR.TypeSystem.VoidType;

public class Call extends IRInstruction{

    public Call(IRFunction _func, IRBasicBlock _block) {
        super("_call"+_func.name, ((FunctionType)_func.type).returnType, _block);
        this.addOperand(_func);
    }

    public Call addArg(Value _arg){
        this.addOperand(_arg);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder raw = new StringBuilder();
        if(!(this.type instanceof VoidType)){
            raw.append(this.getName()).append(" = ");
        }
        raw.append("call ").append(this.getOperand(0).getTypeName()).append('(');
        if(operands.size() > 1) {
            for (int i = 1; i < operands.size(); ++i) {
                raw.append(this.getOperand(i).getTypeName()).append(", ");
            }
            raw.delete(raw.length()-2,raw.length());
        }
        raw.append(')');
        return raw.toString();
    }
}