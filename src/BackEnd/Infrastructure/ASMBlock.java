package BackEnd.Infrastructure;

import BackEnd.Instruction.Instruction;
import BackEnd.Operand.Operand;
import java.util.LinkedList;

public class ASMBlock extends Operand{
    public LinkedList<Instruction> instructionList;

    public ASMBlock(String _name,ASMFunction _func) {
        super("."+_name);
        this.instructionList = new LinkedList<>();
        _func.addBlock(this);
    }

    public void addInstruction(Instruction _instr){
        this.instructionList.add(_instr);
    }

    public String printASM(){
        StringBuilder raw = new StringBuilder();
        raw.append(getName()).append(":\n");
        instructionList.forEach(inst->raw.append('\t').append(inst.printASM()).append('\n'));
        return raw.toString();
    }
}