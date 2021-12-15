package BackEnd.Infrastructure;

import BackEnd.Operand.GlobalVar;
import java.util.ArrayList;

public class ASMModule{
    public ArrayList<GlobalVar> globalVars;
    public ArrayList<ASMFunction> functions;

    public ASMModule() {
        globalVars = new ArrayList<>();
        functions = new ArrayList<>();
    }

    public void addFunc(ASMFunction _func){
        this.functions.add(_func);
    }

    public void addGlobalVar(GlobalVar _va){
        this.globalVars.add(_va);
    }
}