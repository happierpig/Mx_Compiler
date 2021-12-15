package MiddleEnd.Instruction;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.Infrastructure.IRBuilder;
import MiddleEnd.Infrastructure.IRVisitor;

public class Binary extends IRInstruction{
    public IRBuilder.Operator op;

    public Binary(IRBuilder.Operator _op, Value rs1, Value rs2, IRBasicBlock _block) {
        super(_op.toString(), rs1.type, _block);
        assert rs1.type.isEqual(rs2.type);
        this.op = _op;
        this.addOperand(rs1);
        this.addOperand(rs2);
    }

    @Override
    public String toString() {
        return this.getName() + " = " + op + " " + this.getOperand(0).getTypeName() + ", " + this.getOperand(1).getName();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}