package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;

public class BranchInstr extends Instruction{
    // 0 destBlock ; 1 rs1 ; 2 rs2
    public BranchInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op);
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 3;
        rd = args[0]; rs1 = args[1]; rs2 = args[2];
        return this;
    }

    @Override
    public String printASM() {
        return op + "\t" + rs1.getName() + "," + rs2.getName() + "," + rd.getName();
    }
}