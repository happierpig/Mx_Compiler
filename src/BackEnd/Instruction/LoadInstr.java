package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
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
    public Instruction addOperand(Operand... args) {
        assert args.length == 2;
        rd = args[0]; rs1 = args[1]; rs2 = null;
        return this;
    }

    @Override
    public String printASM() {
        return String.format("%s\t%s, %s(%s)", op, rd.getName(), ((Register)rs1).offset, rs1.getName());
    }
}