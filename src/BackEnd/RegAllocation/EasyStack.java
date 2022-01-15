package BackEnd.RegAllocation;

import BackEnd.Infrastructure.ASMFunction;
import BackEnd.Infrastructure.ASMModule;
import BackEnd.Operand.Register;

import java.util.HashMap;

public class EasyStack{
    public ASMModule ripe;
    public HashMap<String,Register> subsTable;

    public EasyStack(ASMModule raw){
        ripe = raw;
        subsTable = new HashMap<>();
    }

    public void regAlloc(ASMFunction func){

    }

}