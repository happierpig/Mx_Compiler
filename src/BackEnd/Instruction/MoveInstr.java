package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class MoveInstr extends Instruction{
    // 0 rd ; 1 rs
    public MoveInstr(ASMBlock _curBlock) {
        super(_curBlock, "mv");
    }

    @Override
    public String printASM() {
        return op + "\t" + operandList.get(0).getName() + "," + operandList.get(1).getName();
    }
}