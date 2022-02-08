package MiddleEnd.Infrastructure;

import MiddleEnd.*;
import MiddleEnd.Operand.*;
import MiddleEnd.Instruction.*;

public interface IRVisitor {

    void visit(IRBasicBlock node);

    void visit(IRFunction node);

    void visit(IRModule node);

    void visit(BoolConstant node);

    void visit(IntConstant node);

    void visit(NullConstant node);

    void visit(StringConstant node);

    void visit(Alloc node);

    void visit(Binary node);

    void visit(Bitcast node);

    void visit(Branch node);

    void visit(Call node);

    void visit(Gep node);

    void visit(GlobalDef node);

    void visit(Icmp node);

    void visit(Load node);

    void visit(Ret node);

    void visit(Store node);

    void visit(Trunc node);

    void visit(Zext node);

    void visit(Temporary node);
}