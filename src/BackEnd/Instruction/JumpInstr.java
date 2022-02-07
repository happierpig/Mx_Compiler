package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import BackEnd.Operand.VirtualRegister;

public class JumpInstr extends Instruction{
    // 0 dest
    public JumpInstr(ASMBlock _curBlock) {
        super(_curBlock, "j");
    }

    @Override
    public void rewriteUse(String origin, VirtualRegister born) {
        throw new RuntimeException("[Debug] Why call this");
    }

    @Override
    public void rewriteDef(String origin, VirtualRegister born) {
        throw new RuntimeException("[Debug] Why call this");
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