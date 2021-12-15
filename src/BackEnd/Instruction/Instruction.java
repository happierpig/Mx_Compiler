package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import java.util.ArrayList;

public abstract class Instruction{
    public ArrayList<Operand> operandList;
    public String op;

    public Instruction(ASMBlock _curBlock,String _op){
        this.operandList = new ArrayList<>();
        _curBlock.addInstruction(this);
        this.op = _op;
    }

    public Instruction addOperand(Operand _o){
        this.operandList.add(_o);
        return this;
    }

    public abstract String printASM();
}