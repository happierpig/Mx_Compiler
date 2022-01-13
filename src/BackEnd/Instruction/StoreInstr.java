package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Register;

public class StoreInstr extends Instruction{
    // sw rs2, imm(rs1)
    // 0 rs2 ; 1 rs1(contain offset)
    public StoreInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op);
    }

    @Override
    public String printASM() {
        return String.format("%s\t%s, %s(%s)", op, operandList.get(0).getName(), operandList.get(1).getName(), ((Register)operandList.get(1)).offset.getName());
    }
}