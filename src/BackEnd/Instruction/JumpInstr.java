package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;

public class JumpInstr extends Instruction{
    // 0 dest
    public JumpInstr(ASMBlock _curBlock) {
        super(_curBlock, "j");
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 1;
        rd = args[0]; rs1 = null; rs2 = null;
        return this;
    }

    @Override
    public String printASM() {
        return op + "\t" +rd.getName();
    }
}