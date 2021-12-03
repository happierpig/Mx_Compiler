package IR;

import IR.Instruction.IRInstruction;
import java.util.HashSet;
import java.util.LinkedList;

public class IRBasicBlock{
    public String blockName;
    public LinkedList<IRInstruction> instructions;
    public static int globalCount = 1;
    public HashSet<IRBasicBlock> pred;
    public HashSet<IRBasicBlock> suc;

    public IRBasicBlock(String _name){
        this.blockName = "_BB" + IRBasicBlock.globalCount + _name;
        ++IRBasicBlock.globalCount;
        instructions = new LinkedList<>();
        pred = new HashSet<>();
        suc = new HashSet<>();
    }

    public IRBasicBlock addInstruction(IRInstruction _instr){
        instructions.add(_instr);
        return this;
    }

    public void addPrev(IRBasicBlock _pred){
        pred.add(_pred);
    }

    public void addSuc(IRBasicBlock _suc){
        suc.add(_suc);
    }

    public String getName(){
        return this.blockName;
    }

    public String toString(){
        StringBuilder raw = new StringBuilder();
        raw.append(this.blockName); raw.append(":");
        // pred
        if(this.pred.size() != 0){
            raw.append("\t\t\t\t\t ;preds = ");
            pred.forEach(tmp->raw.append(tmp.blockName).append(", "));
            raw.delete(raw.length()-2,raw.length());
        }
        raw.append("\n");
        instructions.forEach(tmp->raw.append("\t").append(tmp.toString()).append("\n"));
        return raw.toString();
    }
}