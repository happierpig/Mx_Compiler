package BackEnd.Infrastructure;

import BackEnd.Operand.Immediate;
import BackEnd.Operand.Operand;
import BackEnd.Operand.Register;
import BackEnd.Operand.VirtualRegister;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ASMFunction extends Operand{
    public ArrayList<ASMBlock> blockList;
    public ArrayList<Register> arguments;
    public ArrayList<VirtualRegister> calleeSaved;
    public int stackBias;
    public int virtualIndex;
    public boolean isBuiltin;

    public ASMFunction(String _name) {
        super(_name);
        blockList = new ArrayList<>();
        arguments = new ArrayList<>();
        calleeSaved = new ArrayList<>();
        stackBias = 4; // first four bytes are for s0
        virtualIndex = 0;
    }

    public void addBlock(ASMBlock _block){
        blockList.add(_block);
    }

    public ASMBlock entryBlock(){
        return blockList.get(0);
    }

    public ASMBlock exitBlock(){
        if(blockList.size() == 1) return blockList.get(0);
        return blockList.get(1);
    }

    public Immediate allocStack(){
        this.stackBias += 4;
        return new Immediate(this.stackBias);
    }

    public String printASM(){
        StringBuilder raw = new StringBuilder();
        raw.append('\t').append(".globl").append('\t').append(getName()).append('\n');
        raw.append('\t').append(".p2align").append('\t').append(1).append('\n');
        raw.append('\t').append(".type").append('\t').append(getName()).append(",@function\n");
        raw.append(getName()).append(':').append('\n');
        blockList.forEach(bb->raw.append(bb.printASM()).append('\n'));
        raw.append('\t').append(".size").append('\t').append(getName()).append(", .-").append(getName()).append('\n');
        raw.append("\t\t\t # -- End function");
        return raw.toString();
    }
}