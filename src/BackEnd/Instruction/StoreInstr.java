package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class StoreInstr extends Instruction{
    // sw rs2, imm(rs1)
    // 0 rs2 ; 1 imm ; 2 rs1
    public StoreInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op);
    }

    @Override
    public String printASM() {
        return String.format("%s\t%s, %s(%s)", op, operandList.get(0).getName(), operandList.get(1).getName(), operandList.get(2).getName());
    }
}