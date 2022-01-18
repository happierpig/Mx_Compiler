package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;

/*
    Pseudo-instruction used to load symbol's static data address into destination register
 */
public class LaInstr extends Instruction{
    // 0 rd ; 1 symbol
    public LaInstr(ASMBlock _curBlock) {
        super(_curBlock, "la");
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