package BackEnd.Infrastructure;

import BackEnd.Operand.Immediate;
import BackEnd.Operand.Operand;
import java.util.ArrayList;

public class ASMFunction extends Operand{
    public ArrayList<ASMBlock> blockList;
    public int stackBias;

    public ASMFunction(String _name) {
        super(_name);
        blockList = new ArrayList<>();
        stackBias = 0;
    }

    public void addBlock(ASMBlock _block){
        blockList.add(_block);
    }

    public Immediate allocStack(){
        Immediate tmp = new Immediate(this.stackBias);
        this.stackBias += 4;
        return tmp;
    }
}