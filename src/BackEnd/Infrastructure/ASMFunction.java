package BackEnd.Infrastructure;

import BackEnd.Operand.Operand;
import java.util.ArrayList;

public class ASMFunction extends Operand{
    public ArrayList<ASMBlock> blockList;

    public ASMFunction(String _name) {
        super(_name);
        blockList = new ArrayList<>();
    }

    public void addBlock(ASMBlock _block){
        blockList.add(_block);
    }
}