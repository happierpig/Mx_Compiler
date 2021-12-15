package MiddleEnd;

import MiddleEnd.BaseClass.User;
import MiddleEnd.BaseClass.Value;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.FunctionType;
import MiddleEnd.TypeSystem.IRType;
import java.util.ArrayList;

public class IRFunction extends User{
    public ArrayList<IRBasicBlock> blockList;
    public Value returnAddress; // returnType == void ? null : valid_address;
    public boolean isBuiltin = false;
    public boolean isUsed = false;

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

    public void setBuiltin(){
        this.isBuiltin = true;
    }

    public void setUsed(){
        this.isUsed = true;
    }

    @Override
    public String getName() {
        return "@" + this.name;
    }

    @Override
    public String toString() {
        StringBuilder raw = new StringBuilder();
        if(!isBuiltin) {
            raw.append("define ").append(this.type.toString()).append(' ').append(this.getName()).append('(');
            if (this.operands.size() != 0) {
                this.operands.forEach(tmp -> raw.append(tmp.getTypeName()).append(", "));
                raw.delete(raw.length() - 2, raw.length());
            }
            raw.append(")\t{\n");
            blockList.forEach(tmp -> raw.append(tmp.toString()));
            raw.append("}\n");
        }else if(isUsed){
            raw.append("declare ").append(this.type.toString()).append(' ').append(this.getName()).append('(');
            if (((FunctionType)this.type).parametersType.size() != 0) {
                ((FunctionType)this.type).parametersType.forEach(tmp -> raw.append(tmp.toString()).append(", "));
                raw.delete(raw.length() - 2, raw.length());
            }
            raw.append(")\n");
        }
        return raw.toString();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}