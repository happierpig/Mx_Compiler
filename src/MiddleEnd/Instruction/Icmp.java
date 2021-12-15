package MiddleEnd.Instruction;

import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.Infrastructure.IRBuilder;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.IntegerType;

public class Icmp extends IRInstruction{
    public IRBuilder.Operator op;
    public Icmp(IRBuilder.Operator _op, Value rs1, Value rs2, IRBasicBlock _block) {
        super(_op.toString(), new IntegerType(1), _block);
        assert rs1.type.isEqual(rs2.type);
        this.op = _op;
        this.addOperand(rs1);
        this.addOperand(rs2);
    }

    @Override
    public String toString() {
        return this.getName() + " = icmp " + op + " " + this.getOperand(0).getTypeName() + ", " + this.getOperand(1).getName();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}