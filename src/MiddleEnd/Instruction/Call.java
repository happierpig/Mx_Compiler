package MiddleEnd.Instruction;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.IRFunction;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.FunctionType;
import MiddleEnd.TypeSystem.VoidType;

public class Call extends IRInstruction{
    public Call(IRFunction _func, IRBasicBlock _block) {
        super("_call"+_func.name, ((FunctionType)_func.type).returnType, _block);
        this.addOperand(_func);
    }

    // 0：function 1-: arguments
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

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}