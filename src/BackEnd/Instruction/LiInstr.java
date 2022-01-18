package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;

/*
    Pseudo-instruction used to load immediate to destination register
 */
public class LiInstr extends Instruction{
    // 0 for rd ; 1 for imm
    public LiInstr(ASMBlock _curBlock) {
        super(_curBlock, "li");
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 2;
        rd = args[0]; rs1 = args[1]; rs2 = null;
        return this;
    }

    @Override
    public String printASM() {
        return op + "\t" + rd.getName() + "," + rs1.getName();
    }
}