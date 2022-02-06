package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import BackEnd.Operand.Register;

/*
    Pseudo-instruction used to load symbol's static data address into destination register
 */
public class LaInstr extends Instruction{
    // 0 rd ; 1 symbol
    public LaInstr(ASMBlock _curBlock) {
        super(_curBlock, "la");
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 2;
        rd = args[0]; rs1 = args[1]; rs2 = null;
        this.def.add(rd.getName());
        return this;
    }

    @Override
    public String printASM() {
        return op + "\t" + rd.getName() + "," + rs1.getName();
    }
}