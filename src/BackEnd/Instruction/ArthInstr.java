package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Immediate;
import BackEnd.Operand.Operand;

public class ArthInstr extends Instruction{
    // 0 rd ; 1 rs1 ; 2 rs2(maybe imm)
    public ArthInstr(String _op, ASMBlock _curBlock) {
        super(_curBlock,_op);
    }

    @Override
    public void addOperand(Operand... args) {
        assert  args.length == 3;
        rd = args[0]; rs1 = args[1]; rs2 = args[2];
    }

    @Override
    public String printASM() {
        if(rs2 instanceof Immediate) return op + "i\t" + rd.getName() + ", " + rs1.getName() + ", " + rs2;
        else return op + "\t" + rd.getName() + ", " + rs1.getName() + ", " + rs2.getName();
    }
}