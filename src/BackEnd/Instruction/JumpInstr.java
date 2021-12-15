package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class JumpInstr extends Instruction{
    // 0 dest
    public JumpInstr(ASMBlock _curBlock) {
        super(_curBlock, "j");
    }

    @Override
    public String printASM() {
        return op + "\t" + operandList.get(0).getName();
    }
}