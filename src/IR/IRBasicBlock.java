package IR;

import IR.BaseClass.Value;
import IR.Instruction.Branch;
import IR.Instruction.IRInstruction;
import IR.Instruction.Ret;
import IR.TypeSystem.LabelType;
import java.util.LinkedList;

public class IRBasicBlock extends Value {
    public LinkedList<IRInstruction> instructions;
    public IRInstruction terminator;
    public IRFunction parentFunction;

    public IRBasicBlock(String _name, IRFunction _parent) {
        super(_name+"_bb", new LabelType());
        this.parentFunction = _parent;
        _parent.addBlock(this);
        this.instructions = new LinkedList<>();
        this.terminator = null;
    }

    public void addInstruction(IRInstruction _instr){
        if(_instr instanceof Branch || _instr instanceof Ret) setTerminator(_instr);
        else this.instructions.add(_instr);
    }

    public void setTerminator(IRInstruction _terminator){
        if(this.terminator != null) throw new RuntimeException("[Debug] duplicate set terminator.");
        this.terminator = _terminator;
    }

    @Override
    public String toString() {
        StringBuilder raw = new StringBuilder();
        raw.append(this.name); raw.append(":");
        // pred
        if(this.useList.size() != 0){
            raw.append("\t\t\t\t\t ;preds = ");
            useList.forEach(tmp->raw.append(((Branch)tmp).parentBlock.getName()).append(", "));
            raw.delete(raw.length()-2,raw.length());
        }
        raw.append("\n");
        instructions.forEach(tmp->raw.append("\t").append(tmp.toString()).append("\n"));
        raw.append("\t").append(terminator.toString()).append("\n");
        return raw.toString();
    }
}