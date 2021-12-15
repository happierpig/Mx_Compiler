package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class BranchInstr extends Instruction{
    // 0 destBlock ; 1 rs1 ; 2 rs2
    public BranchInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op);
    }

    @Override
    public String printASM() {
        return op + "\t" + operandList.get(1).getName() + "," + operandList.get(2) + "," + operandList.get(0);
    }
}