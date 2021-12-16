package MiddleEnd.Instruction;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.IRType;

public class Gep extends IRInstruction{
    // 0 for basePointer ; 1 -> n for index
    public Gep(IRType targetType, Value calculatedPointer, IRBasicBlock _block) {
        super("gep", targetType, _block);
        this.addOperand(calculatedPointer);
    }

    public Gep addIndex(Value _value){
        this.addOperand(_value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder raw = new StringBuilder();
        raw.append(this.getName()).append(" = getelementptr inbounds ").append(getOperand(0).type.dePointed().toString())
                .append(", ").append(getOperand(0).getTypeName());
        assert this.operands.size() > 1;
        for(int i = 1;i < operands.size();++i) raw.append(", ").append(getOperand(i).getTypeName());
        return raw.toString();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}