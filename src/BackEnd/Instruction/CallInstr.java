package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;

public class CallInstr extends Instruction{
    // 0 func
    public CallInstr(ASMBlock _curBlock) {
        super(_curBlock, "call");
    }

    @Override
    public void addOperand(Operand... args) {
        assert args.length == 1;
        rd = args[0]; rs1 = null; rs2 = null;
    }

    @Override
    public String printASM() {
        return op + "\t" + rd.getName();
    }
}