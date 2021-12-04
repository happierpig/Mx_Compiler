package IR;

import IR.Instruction.GlobalDef;
import IR.Operand.StringConstant;
import java.util.ArrayList;

public class IRModule{
    public ArrayList<IRFunction> functionList;
    public ArrayList<StringConstant> stringList;
    public ArrayList<GlobalDef> globalDefList;
    public ArrayList<IRFunction> globalInitList;

    public IRModule(){
        functionList = new ArrayList<>();
        stringList = new ArrayList<>();
        globalDefList = new ArrayList<>();
        globalInitList = new ArrayList<>();
    }

    public void addFunction(IRFunction _func){
        functionList.add(_func);
    }

    public void addString(StringConstant _string){
        stringList.add(_string);
    }

    public void addGlobalDef(GlobalDef _gd){
         globalDefList.add(_gd);
    }

    public String toString(){
        StringBuilder raw = new StringBuilder();
        if(stringList.size() != 0) stringList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        if(globalDefList.size() != 0) globalDefList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        if(globalInitList.size() != 0) {
            //todo :
        }
        functionList.forEach(tmp->raw.append(tmp.toString()));
        return raw.toString();
    }
}