package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public class ArthInstr extends Instruction{
    // 0 rd ; 1 rs1 ; 2 rs2
    public ArthInstr(String _op, ASMBlock _curBlock) {
        super(_curBlock,_op);
    }

    @Override
    public String printASM() {
        return op + "\t" + operandList.get(0).getName() + "," + operandList.get(1) + "," + operandList.get(2);
    }
}