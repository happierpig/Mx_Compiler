package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class LoadInstr extends Instruction{
    // 0 rd ; 1 imm ; 2 rs1
    public LoadInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op); // lb lh lw lbu lbu
    }

    @Override
    public String printASM() {
        return String.format("%s\t%s, %s(%s)", op, operandList.get(0).getName(), operandList.get(1).getName(), operandList.get(2).getName());
    }
}