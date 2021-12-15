package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class LaInstr extends Instruction{
    // 0 rd ; 1 symbol
    public LaInstr(ASMBlock _curBlock) {
        super(_curBlock, "la");
    }

    @Override
    public String printASM() {
        return op + "\t" + operandList.get(0).getName() + "," + operandList.get(1).getName();
    }
}