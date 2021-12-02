package IR;

import IR.Operand.Register;
import IR.TypeSystem.IRType;
import java.util.LinkedList;

public class IRFunction{
    public String funcName;
    public IRType returnType;
    public IRBasicBlock entryBlock;
    public IRBasicBlock exitBlock;
    public LinkedList<Register> parameters;
    public LinkedList<IRBasicBlock> blocks;

    public IRFunction(String _name){
        this.funcName = "_func_" + _name;
        this.parameters = new LinkedList<>();
        this.blocks = new LinkedList<>();
    }

    public String getName(){
        return this.funcName;
    }

    public String toString(){
        StringBuilder raw = new StringBuilder();
        raw.append("define ").append(returnType.toString()).append(" @").append(this.getName()).append('(');
        if(parameters.size() != 0){
            parameters.forEach(tmp->raw.append(tmp.toString()).append(", "));
            raw.delete(raw.length()-2,raw.length());
        }
        raw.append(")\t{\n");
        blocks.forEach(tmp->raw.append(tmp.toString()));
        raw.append("}\n");
        return raw.toString();
    }
}