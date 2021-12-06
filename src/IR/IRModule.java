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

    public void addGlobalInit(IRFunction _func){
        globalInitList.add(_func);
    }

    public String toString(){
        StringBuilder raw = new StringBuilder();
        if(stringList.size() != 0) stringList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        if(globalDefList.size() != 0) globalDefList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        if(globalInitList.size() != 0) {
            raw.append("@llvm.global_ctors = appending global [1 x { i32, void ()*, i8* }] [{ i32, void ()*, i8* } { i32 65535, void ()* @_GLOBAL_0, i8* null }]").append("\n");
            globalInitList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        }
        functionList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        return raw.toString();
    }
}