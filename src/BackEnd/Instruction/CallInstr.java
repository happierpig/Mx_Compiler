package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class CallInstr extends Instruction{
    // 0 func
    public CallInstr(ASMBlock _curBlock) {
        super(_curBlock, "call");
    }

    @Override
    public String printASM() {
        return op + "\t" + operandList.get(0).getName();
    }
}