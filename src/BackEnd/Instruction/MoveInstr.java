package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;

public class MoveInstr extends Instruction{
    // 0 rd ; 1 rs
    public MoveInstr(ASMBlock _curBlock) {
        super(_curBlock, "mv");
    }

    @Override
    public void addOperand(Operand... args) {
        assert args.length == 2;
        rd = args[0]; rs1 = args[1]; rs2 = null;
    }

    @Override
    public String printASM() {
        return op + "\t" + rd.getName() + "," + rs1.getName();
    }
}