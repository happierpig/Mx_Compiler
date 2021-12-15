package BackEnd.Infrastructure;

import MiddleEnd.IRBasicBlock;
import MiddleEnd.IRFunction;
import MiddleEnd.IRModule;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.Instruction.*;
import MiddleEnd.Operand.BoolConstant;
import MiddleEnd.Operand.IntConstant;
import MiddleEnd.Operand.NullConstant;
import MiddleEnd.Operand.StringConstant;

public class ASMBuilder implements IRVisitor{

    @Override
    public void visit(IRBasicBlock node) {

    }

    @Override
    public void visit(IRFunction node) {

    }

    @Override
    public void visit(IRModule node) {

    }

    @Override
    public void visit(BoolConstant node) {

    }

    @Override
    public void visit(IntConstant node) {

    }

    @Override
    public void visit(NullConstant node) {

    }

    @Override
    public void visit(StringConstant node) {

    }

    @Override
    public void visit(Alloc node) {

    }

    @Override
    public void visit(Binary node) {

    }

    @Override
    public void visit(Bitcast node) {

    }

    @Override
    public void visit(Branch node) {

    }

    @Override
    public void visit(Call node) {

    }

    @Override
    public void visit(Gep node) {

    }

    @Override
    public void visit(GlobalDef node) {

    }

    @Override
    public void visit(Icmp node) {

    }

    @Override
    public void visit(Load node) {

    }

    @Override
    public void visit(Ret node) {

    }

    @Override
    public void visit(Store node) {

    }

    @Override
    public void visit(Trunc node) {

    }

    @Override
    public void visit(Zext node) {

    }
}