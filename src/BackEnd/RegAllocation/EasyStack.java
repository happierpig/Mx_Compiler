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
    // todo: remake
    public EasyStack(ASMModule raw){
        ripe = raw;
        subsTable = new HashMap<>();
    }

    public void process(){
        ripe.functions.forEach(func->{
            if(func.isBuiltin) return;
            subsTable.clear();
            this.preProcess(func);
            /*
                1. addi sp
                2. backup s0 (done)
                3. update s0
                ...
                4. recover s0 (done)
                5. addi sp
                6. ret
             */
            int offset = func.stackBias;
            if(offset < 2048) {
                func.entryBlock().instructionList.addFirst(new ArthInstr("add",null)
                        .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new Immediate(-offset)));
                func.exitBlock().instructionList.addLast(new ArthInstr("add",null)
                        .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new Immediate(offset)));
                ListIterator<Instruction> it = func.entryBlock().instructionList.listIterator();
                it.next();it.next();
                it.add(new ArthInstr("add",null).addOperand(new PhysicalRegister("s0"),new  PhysicalRegister("sp"),new Immediate(offset)));
            }else{
                func.entryBlock().instructionList.addFirst(new ArthInstr("add",null)
                        .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new PhysicalRegister("t4")));
                func.entryBlock().instructionList.addFirst(new LiInstr(null).addOperand(new PhysicalRegister("t4"),new Immediate(-offset)));
                func.exitBlock().instructionList.addLast(new LiInstr(null).addOperand(new PhysicalRegister("t4"),new Immediate(offset)));
                func.exitBlock().instructionList.addLast(new ArthInstr("add",null)
                        .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new PhysicalRegister("t4")));
                ListIterator<Instruction> it = func.entryBlock().instructionList.listIterator();
                it.next();it.next();it.next();
                it.add(new ArthInstr("sub",null)
                        .addOperand(new PhysicalRegister("s0"),new PhysicalRegister("sp"),new PhysicalRegister("t4")));
            }
            func.exitBlock().instructionList.addLast(new RetInstr(null));
            this.regAlloc(func);
        });
    }

    public void regAlloc(ASMFunction func){
        for(ASMBlock bb : func.blockList){
            ListIterator<Instruction> it = bb.instructionList.listIterator();
            while(it.hasNext()){
                Instruction inst = it.next();
                if(inst.rs1 instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rs1;
                    if(vReg.color != 32) inst.rs1 = new PhysicalRegister(vReg);
                    else{
                        int offset = findVReg(vReg.getName());
                        inst.rs1 = new PhysicalRegister(6,vReg);
                        addLoad(it,"t1",offset);
                    }
                }
                if(inst.rs2 instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rs2;
                    if(vReg.color != 32) inst.rs2 = new PhysicalRegister(vReg);
                    else{
                        int offset = findVReg(vReg.getName());
                        inst.rs2 = new PhysicalRegister(7,vReg);
                        addLoad(it,"t2",offset);
                    }
                }
                if(inst.rd instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rd;
                    if(vReg.color != 32) inst.rd = new PhysicalRegister(vReg);
                    else{
                        int offset = findVReg(vReg.getName());
                        inst.rd = new PhysicalRegister(5,vReg);
                        addStore(it,"t0",offset);
                    }
                }
            }
        }
    }

    private void addLoad(ListIterator<Instruction> it,String color,int offset){
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

    private void addStore(ListIterator<Instruction> it,String color,int offset){
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

    private void preProcess(ASMFunction func){
        func.blockList.forEach(bb->{
            bb.instructionList.forEach(inst->{
                if(inst.rd instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rd;
                    if(vReg.color == 32) defineVReg(func,vReg.getName());
                }
                if(inst.rs1 instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rs1;
                    if(vReg.color == 32) defineVReg(func,vReg.getName());
                }
                if(inst.rs2 instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rs2;
                    if(vReg.color == 32) defineVReg(func,vReg.getName());
                }
            });
        });
    }

    private void defineVReg(ASMFunction func,String vName){
        if(!subsTable.containsKey(vName)) {
            int offset = func.allocStack().value;
            subsTable.put(vName, offset);
        }
    }

    private int findVReg(String vName){
        Integer offset = subsTable.get(vName);
        assert offset != null;
        return offset;
    }
}