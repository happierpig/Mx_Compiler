package IR;

import IR.Instruction.GlobalDef;
import IR.Operand.StringConstant;
import java.util.LinkedList;

public class IRModule{
    public LinkedList<IRFunction> functions;
    public LinkedList<StringConstant> strings;
    public LinkedList<GlobalDef> globalDefs;


    public IRModule(){
        functions = new LinkedList<>();
        strings = new LinkedList<>();
        globalDefs = new LinkedList<>();
    }

    public void addFunction(IRFunction _fun){
        this.functions.add(_fun);
    }

    public String toLLVM(){
        StringBuilder raw = new StringBuilder();
        if(strings.size() != 0) strings.forEach(tmp->raw.append(tmp.printLLVM()).append("\n"));
        if(globalDefs.size() != 0) globalDefs.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        functions.forEach(tmp->raw.append(tmp.toString()));
        return raw.toString();
    }
}