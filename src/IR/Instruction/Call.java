package IR.Instruction;

import IR.IRFunction;
import IR.Operand.Register;
import IR.TypeSystem.Void;
import java.util.LinkedList;

public class Call extends IRInstruction{
    public IRFunction func;
    public Register destReg;
    public LinkedList<Register> parameters;

    public Call(IRFunction _func) {
        this.func = _func;
        this.destReg = null;
        this.parameters = new LinkedList<>();
    }

    public Call(IRFunction _func,Register _destReg){
        this.func = _func;
        this.destReg = _destReg;
        this.destReg.setType(_func.returnType);
        this.parameters = new LinkedList<>();
    }

    public void addParameter(Register _reg){
        this.parameters.add(_reg);
    }

    @Override
    public String toString() {
        StringBuilder raw = new StringBuilder();
        if(!(this.func.returnType instanceof Void)) {
            raw.append(this.destReg.getName()).append(" = ");
        }
        raw.append("call ").append(this.func.returnType.toString()).append(" @").append(this.func.getName()).append('(');
        if(this.parameters.size() != 0){
            this.parameters.forEach(tmp->raw.append(tmp.toString()).append(", "));
            raw.delete(raw.length()-2,raw.length());
        }
        raw.append(')');
        return raw.toString();
    }
}