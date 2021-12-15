package BackEnd.Infrastructure;

import BackEnd.Instruction.Instruction;
import BackEnd.Operand.Operand;
import java.util.ArrayList;

public class ASMBlock extends Operand{
    public ArrayList<Instruction> instructionList;

    public ASMBlock(String _name) {
        super("."+_name);
        this.instructionList = new ArrayList<>();
    }

    public void addInstruction(Instruction _instr){
        this.instructionList.add(_instr);
    }

}