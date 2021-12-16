package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Register;

/*
    About GEP(Pointer Calculation) :
    1. Constant: We use rs1 + imm
    2. Variable: We use rs1(by Add) + 0
 */
public class LoadInstr extends Instruction{
    // 0 rd ; 1 rs1(contains offset)
    public LoadInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op); // lb lh lw lbu lbu
    }

    @Override
    public String printASM() {
        return String.format("%s\t%s, %s(%s)", op, operandList.get(0).getName(), operandList.get(1).getName(), ((Register)operandList.get(1)).offset.getName());
    }
}