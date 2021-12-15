package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class RetInstr extends Instruction{

    public RetInstr(ASMBlock _curBlock) {
        super(_curBlock, "ret");
    }

    @Override
    public String printASM() {
        return op;
    }
}