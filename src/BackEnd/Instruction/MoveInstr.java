package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;

public class MoveInstr extends Instruction{
    // 0 rd ; 1 rs
    public MoveInstr(ASMBlock _curBlock) {
        super(_curBlock, "mv");
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 2;
        rd = args[0]; rs1 = args[1]; rs2 = null;
        this.def.add(rd.getName());
        this.use.add(rs1.getName());
        return this;
    }

    @Override
    public String printASM() {
        return op + "\t" + rd.getName() + "," + rs1.getName();
    }
}