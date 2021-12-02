package IR;

import IR.Operand.StringConstant;

import java.util.HashMap;
import java.util.LinkedList;

public class IRModule{
    public LinkedList<IRFunction> functions;
    public HashMap<String, StringConstant> strings;

    public IRModule(){
        functions = new LinkedList<>();
        strings = new HashMap<>();
    }

    public String toLLVM(){
        StringBuilder raw = new StringBuilder();
        if(strings.size() != 0){
            strings.forEach((k,v)->raw.append(v.toString()));
        }
        functions.forEach(tmp->raw.append(tmp.toString()));
        return raw.toString();
    }
}