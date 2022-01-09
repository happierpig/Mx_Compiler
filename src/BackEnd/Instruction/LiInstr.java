package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

/*
    Pseudo-instruction used to load immediate to destination register
 */
public class LiInstr extends Instruction{
    // 0 for rd ; 1 for imm
    public LiInstr(ASMBlock _curBlock) {
        super(_curBlock, "li");
    }

    @Override
    public String printASM() {
        return op + "\t" + operandList.get(0).getName() + "," + operandList.get(1).getName();
    }
}