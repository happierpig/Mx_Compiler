package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;

public abstract class Instruction{

    public Instruction(ASMBlock _curBlock){

        _curBlock.addInstruction(this);
    }
}