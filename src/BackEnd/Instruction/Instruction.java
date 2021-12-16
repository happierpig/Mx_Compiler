package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Instruction{
    public ArrayList<Operand> operandList;
    public String op;

    public Instruction(ASMBlock _curBlock,String _op){
        this.operandList = new ArrayList<>();
        _curBlock.addInstruction(this);
        this.op = _op;
    }

    public void addOperand(Operand ... args){
        this.operandList.addAll(Arrays.asList(args));
    }

    public abstract String printASM();
}