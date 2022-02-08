package Optimization;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.IRFunction;
import MiddleEnd.IRModule;
import MiddleEnd.Instruction.Alloc;
import MiddleEnd.Instruction.IRInstruction;
import MiddleEnd.Instruction.Load;
import MiddleEnd.Instruction.Store;
import MiddleEnd.Operand.Temporary;
import java.util.HashMap;
import java.util.LinkedList;

public class Mem2Reg{
    public IRModule ripe;
    public HashMap<Alloc, Temporary> alloc_table;
    public HashMap<Load,Temporary> load_map;

    public Mem2Reg(IRModule raw){
        this.ripe = raw;
        this.ripe.functionList.forEach(func->{
            if(func.isBuiltin) return;
            process(func);
        });
    }

    private void preProcess(){
        alloc_table = new HashMap<>();
        load_map = new HashMap<>();
    }

    private void process(IRFunction func){
        preProcess();
        for(IRBasicBlock bb : func.blockList){
            LinkedList<IRInstruction> rewriteList = new LinkedList<>();
            for(IRInstruction inst : bb.instructions){
                if(inst instanceof Alloc){
                    assert !alloc_table.containsKey((Alloc)inst);
                    alloc_table.put((Alloc)inst,new Temporary(inst.type.dePointed()));
                    continue;
                }
                if(inst instanceof Load){
                    Value address = inst.getOperand(0);
                    if(address instanceof Alloc){
                        assert alloc_table.containsKey((Alloc)address);
                        Temporary newTemp = new Temporary(address.type.dePointed());
                        load_map.put((Load)inst, newTemp);
                        rewriteList.add(new Store(alloc_table.get((Alloc) address),newTemp,null));
                        continue;
                    }
                }
                modifyInst(inst);
                rewriteList.add(inst);
            }
            bb.instructions = rewriteList;
            modifyInst(bb.terminator);
        }
    }

    private void modifyInst(IRInstruction inst){
        if(inst == null) return;
        for(int i = 0;i < inst.operands.size();++i){
            Value op = inst.getOperand(i);
            if(op instanceof Load){
                if(load_map.containsKey(op)){
                    inst.operands.set(i,load_map.get((Load)op));
                }
            }
            if(op instanceof Alloc){
                assert alloc_table.containsKey((Alloc)op);
                inst.operands.set(i,alloc_table.get((Alloc)op));
            }
        }
    }
}