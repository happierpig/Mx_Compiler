package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Immediate;

public class ArthInstr extends Instruction{
    // 0 rd ; 1 rs1 ; 2 rs2(maybe imm)
    public ArthInstr(String _op, ASMBlock _curBlock) {
        super(_curBlock,_op);
    }

    @Override
    public String printASM() {
        if(operandList.get(2) instanceof Immediate) return op + "i\t" + operandList.get(0).getName() + "," + operandList.get(1) + "," + operandList.get(2);
        else return op + "\t" + operandList.get(0).getName() + "," + operandList.get(1) + "," + operandList.get(2);
    }
}