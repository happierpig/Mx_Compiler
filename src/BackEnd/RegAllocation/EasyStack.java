package BackEnd.RegAllocation;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Infrastructure.ASMFunction;
import BackEnd.Infrastructure.ASMModule;
import BackEnd.Instruction.*;
import BackEnd.Operand.*;

import java.util.HashMap;
import java.util.ListIterator;

public class EasyStack{
    public ASMModule ripe;
    public HashMap<String, Integer> subsTable;
    // t0 : 5 ; t1: 6 ; t2 : 7; t3 : 28;

    public EasyStack(ASMModule raw){
        ripe = raw;
        subsTable = new HashMap<>();
    }

    public void process(){
        ripe.functions.forEach(func->{
            subsTable.clear();
            this.regAlloc(func);
        });
    }

    public void regAlloc(ASMFunction func){
        for(ASMBlock bb : func.blockList){
            ListIterator<Instruction> it = bb.instructionList.listIterator();
            while(it.hasNext()){
                Instruction inst = it.next();
                if(inst.rd instanceof VirtualRegister vReg){
                    if(vReg.color != 32) inst.rd = new PhysicalRegister(vReg);
                    else{
                        int offset = findVReg(func,vReg.getName());
                        inst.rd = new PhysicalRegister(5,vReg);
                        addStore(it,"t0",offset);
                    }
                }
                if(inst.rs1 instanceof VirtualRegister vReg){
                    if(vReg.color != 32) inst.rs1 = new PhysicalRegister(vReg);
                    else{
                        int offset = findVReg(func,vReg.getName());
                        inst.rs1 = new PhysicalRegister(6,vReg);
                        addLoad(it,"t1",offset);
                    }
                }
                if(inst.rs2 instanceof VirtualRegister vReg){
                    if(vReg.color != 32) inst.rs2 = new PhysicalRegister(vReg);
                    else{
                        int offset = findVReg(func,vReg.getName());
                        inst.rs2 = new PhysicalRegister(7,vReg);
                        addLoad(it,"t2",offset);
                    }
                }
            }
        }
    }

    public void addLoad(ListIterator<Instruction> it,String color,int offset){
        assert  offset >= 0;
        it.previous();
        if(offset < 2048){
            Instruction newInstr = new LoadInstr(null,"lw");
            newInstr.addOperand(new PhysicalRegister(color),new PhysicalRegister("s0",new Immediate(-offset)));
            it.add(newInstr);
        }else{  // previous li add load
            it.add(new LiInstr(null).addOperand(new PhysicalRegister("t3"),new Immediate(-offset)));
            it.add(new ArthInstr("add",null).addOperand(new PhysicalRegister("t3"),new PhysicalRegister("s0"),new PhysicalRegister("t3")));
            it.add(new LoadInstr(null,"lw").addOperand(new PhysicalRegister(color),new PhysicalRegister("t3")));
        }
        it.next();
    }

    public void addStore(ListIterator<Instruction> it,String color,int offset){
        assert  offset >= 0;
        if(offset < 2048){
            Instruction newInstr = new StoreInstr(null,"sw");
            newInstr.addOperand(new PhysicalRegister(color),new PhysicalRegister("s0",new Immediate(-offset)));
            it.add(newInstr);
        }else{  // previous li add load
            it.add(new LiInstr(null).addOperand(new PhysicalRegister("t3"),new Immediate(-offset)));
            it.add(new ArthInstr("add",null).addOperand(new PhysicalRegister("t3"),new PhysicalRegister("s0"),new PhysicalRegister("t3")));
            it.add(new StoreInstr(null,"sw").addOperand(new PhysicalRegister(color),new PhysicalRegister("t3")));
        }
    }

    public int findVReg(ASMFunction func,String vName){
        int offset;
        if(subsTable.containsKey(vName)) offset = subsTable.get(vName);
        else{
            offset = func.allocStack().value;
            subsTable.put(vName,offset);
        }
        return offset;
    }
}