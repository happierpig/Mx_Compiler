package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Instruction{
    public Operand rd,rs1,rs2;
    public String op;

    public Instruction(ASMBlock _curBlock,String _op){
        _curBlock.addInstruction(this);
        this.op = _op;
    }

    public abstract void addOperand(Operand ... args);


    public abstract String printASM();
}