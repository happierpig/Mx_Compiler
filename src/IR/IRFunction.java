package IR;

import IR.BaseClass.User;
import IR.BaseClass.Value;
import IR.TypeSystem.IRType;
import java.util.ArrayList;

public class IRFunction extends User{
    public ArrayList<IRBasicBlock> blockList;
    public Value returnAddress; // returnType == void ? null : valid_address;

    public IRFunction(String _name, IRType _type) {
        super(_name, _type);
        this.blockList = new ArrayList<>();
        this.returnAddress = null;
    }

    public void addBlock(IRBasicBlock _bb){
        blockList.add(_bb);
    }

    public IRBasicBlock entryBlock(){ return this.blockList.get(0); }

    public IRBasicBlock exitBlock(){ return this.blockList.get(1); }

    public void addParameter(Value _para){
        this.addOperand(_para);
    }

    @Override
    public String getName() {
        return "@" + this.name;
    }

    @Override
    public String toString() {
        StringBuilder raw = new StringBuilder();
        raw.append("define ").append(this.type.toString()).append(' ').append(this.getName()).append('(');
        if(this.operands.size() != 0){
            this.operands.forEach(tmp->raw.append(tmp.getTypeName()).append(", "));
            raw.delete(raw.length()-2,raw.length());
        }
        raw.append(")\t{\n");
        blockList.forEach(tmp->raw.append(tmp.toString()));
        raw.append("}\n");
        return raw.toString();
    }
}