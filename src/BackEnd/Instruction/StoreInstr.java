package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import BackEnd.Operand.Register;

public class StoreInstr extends Instruction{
    // sw rs2, imm(rs1)
    // 0 rs2 ; 1 rs1(contain offset)
    public StoreInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op);
    }

    @Override
    public void addOperand(Operand... args) {
        assert args.length == 2;
        rd = null; rs1 = args[1]; rs2 = args[0];
    }

    @Override
    public String printASM() {
        return String.format("%s\t%s, %s(%s)", op, rs2.getName(), ((Register)rs1).offset, rs1.getName());
    }

}