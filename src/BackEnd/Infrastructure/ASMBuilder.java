package BackEnd.Infrastructure;

import BackEnd.Instruction.*;
import BackEnd.Operand.*;
import MiddleEnd.BaseClass.User;
import MiddleEnd.BaseClass.Value;
import MiddleEnd.IRBasicBlock;
import MiddleEnd.IRFunction;
import MiddleEnd.IRModule;
import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.Instruction.*;
import MiddleEnd.Operand.BoolConstant;
import MiddleEnd.Operand.IntConstant;
import MiddleEnd.Operand.NullConstant;
import MiddleEnd.Operand.StringConstant;
import MiddleEnd.TypeSystem.ArrayType;
import MiddleEnd.TypeSystem.IRType;
import MiddleEnd.TypeSystem.StructType;
import MiddleEnd.TypeSystem.VoidType;


public class ASMBuilder implements IRVisitor{
    public ASMFunction curFunction;
    public ASMBlock curBlock;


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
        node.ASMOperand = new Immediate(node.value ? 1 : 0);
    }

    @Override
    public void visit(IntConstant node) {
        node.ASMOperand = new Immediate(node.value);
    }

    @Override
    public void visit(NullConstant node) {
        node.ASMOperand = new Immediate(0);
    }

    @Override
    public void visit(StringConstant node) {
        node.ASMOperand = new GlobalVar(node.name,node.value);
    }

    @Override
    public void visit(GlobalDef node) {
        node.ASMOperand = new GlobalVar(node.name);
    }

    @Override
    public void visit(Alloc node) {
        node.ASMOperand = new VirtualRegister(curFunction.allocStack(),8); // x8 = s0
    }

    @Override
    public void visit(Binary node) {
        node.operands.forEach(this::recurDown);
        String op;
        switch(node.op){
            case sdiv -> op = "div";
            case srem -> op = "rem";
            case shl -> op = "sll";
            case ashr -> op = "sra";
            default -> op = node.op.toString();
        }
        Register newOperand = new VirtualRegister();
        this.arthForm(newOperand,node.getOperand(0).ASMOperand,node.getOperand(1).ASMOperand,op);
        node.ASMOperand = newOperand;
    }

    @Override
    public void visit(Bitcast node) {
        recurDown(node.getOperand(0));
        node.ASMOperand = node.getOperand(0).ASMOperand;
    }

    @Override
    public void visit(Icmp node) { // transform to slt; todo : optimization for i
        Register newOperand = new VirtualRegister();
        node.operands.forEach(this::recurDown);
        Operand rs1 = node.getOperand(0).ASMOperand;
        Operand rs2 = node.getOperand(1).ASMOperand;
        if(rs1 instanceof Immediate tmp){
            rs1 = new VirtualRegister();
            new LiInstr(curBlock).addOperand(rs1,tmp);
        }
        if(rs2 instanceof Immediate tmp){
            rs2 = new VirtualRegister();
            new LiInstr(curBlock).addOperand(rs2,tmp);
        }
        String op = "slt";
        switch(node.op){
            case sgt -> new ArthInstr(op,curBlock).addOperand(newOperand,rs2,rs1);
            case slt -> new ArthInstr(op,curBlock).addOperand(newOperand,rs1,rs2);
            case sge -> {
                new ArthInstr(op,curBlock).addOperand(newOperand,rs1,rs2);
                new ArthInstr("xor",curBlock).addOperand(newOperand,newOperand,new Immediate(1)); // not SSA
            }
            case sle -> {
                new ArthInstr(op,curBlock).addOperand(newOperand,rs2,rs1);
                new ArthInstr("xor",curBlock).addOperand(newOperand,newOperand,new Immediate(1)); // not SSA
            }
        }
        node.ASMOperand = newOperand;
    }

    @Override
    public void visit(Trunc node) {
        recurDown(node.getOperand(0));
        node.ASMOperand = node.getOperand(0).ASMOperand;
    }

    @Override
    public void visit(Zext node) {
        recurDown(node.getOperand(0));
        node.ASMOperand = node.getOperand(0).ASMOperand;
    }

    @Override
    public void visit(Branch node) {
        node.ASMOperand = null;
        node.operands.forEach(this::recurDown);
        if(node.operands.size() == 1) new JumpInstr(curBlock).addOperand(node.getOperand(0).ASMOperand);
        else{
            // br flag block1 block2 -> bne flag zero block1 + j block2
            Operand flag = node.getOperand(0).ASMOperand;
            if(flag instanceof Immediate tmp){
                flag = new VirtualRegister();
                new LiInstr(curBlock).addOperand(flag,tmp);
            }
            new BranchInstr(curBlock,"bne").addOperand(node.getOperand(1).ASMOperand,flag,PhysicalRegister.getPhyReg(0));
            new JumpInstr(curBlock).addOperand(node.getOperand(2).ASMOperand);
        }
    }

    @Override
    public void visit(Call node) {

    }

    @Override
    public void visit(Gep node) {
        /*
            Pointer Calculate :
            1. Two index : constant folded into immediate
            2. Only one index : constant(Immediate) or Variable(Reg)
            Exception: String
         */
        Operand newOperand;
        node.operands.forEach(this::recurDown);
        Operand basePointer = node.getOperand(0).ASMOperand;
        IRType baseType = node.getOperand(0).type.dePointed();
        if(baseType instanceof ArrayType){ // String
            assert basePointer instanceof GlobalVar;
            newOperand = new VirtualRegister();
            new LaInstr(curBlock).addOperand(newOperand,basePointer);
        }else if(baseType instanceof StructType){   // Class
            assert node.getOperand(2) instanceof IntConstant;
            assert basePointer instanceof Register;
            int offset = ((StructType) baseType).getOffset(((IntConstant) node.getOperand(2)).value);
            newOperand = basePointer;
            ((Register)newOperand).offset = new Immediate(offset);
        }else{  // Array
            assert basePointer instanceof Register;
            Value indexValue = node.getOperand(1);
            if(indexValue instanceof IntConstant){
                int offset = ((IntConstant) indexValue).value * baseType.byteSize();
                newOperand = basePointer;
                ((Register)newOperand).offset = new Immediate(offset);
            }else{
                assert indexValue.ASMOperand instanceof Register;
                newOperand = new VirtualRegister();
                Register biasReg = new VirtualRegister();
                this.arthForm(biasReg,indexValue.ASMOperand,new Immediate(baseType.byteSize()),"mul");
                this.arthForm((Register) newOperand,biasReg,basePointer,"add");
                ((Register)newOperand).offset = new Immediate(0);
            }
        }
        node.ASMOperand = newOperand;
    }

    @Override
    public void visit(Load node) {
        Register newOperand = new VirtualRegister();
        recurDown(node.getOperand(0));
        Operand pointerOperand = node.getOperand(0).ASMOperand;
        if(pointerOperand instanceof Register){
            // Virtual Register for Gep ; Physical Register for stack variable
            new LoadInstr(curBlock,"lw").addOperand(newOperand, pointerOperand);
        }else{
            assert pointerOperand instanceof GlobalVar;
            Register addressReg = new VirtualRegister();
            new LaInstr(curBlock).addOperand(addressReg, pointerOperand);
            addressReg.offset = new Immediate(0);
            new LoadInstr(curBlock,"lw").addOperand(newOperand,addressReg);
        }
        node.ASMOperand = newOperand;
    }

    @Override
    public void visit(Ret node) {
        node.ASMOperand = null;
        new RetInstr(curBlock);
        if(node.type instanceof VoidType) return;
        recurDown(node.getOperand(0));
        Operand returnValue = node.getOperand(0).ASMOperand;
        assert returnValue instanceof Register;
        new MoveInstr(curBlock).addOperand(new VirtualRegister(10)); // x10 = a0
    }

    @Override
    public void visit(Store node) {
        node.ASMOperand = null; // Store will not be used
        recurDown(node.getOperand(0));
        recurDown(node.getOperand(1));
        Operand pointerOp = node.getOperand(1).ASMOperand;
        Operand valueOp = node.getOperand(0).ASMOperand;
        Operand value = valueOp;
        if(valueOp instanceof Immediate){
            value = new VirtualRegister();
            new LiInstr(curBlock).addOperand(value, valueOp);
        } else assert value instanceof Register;
        if(pointerOp instanceof Register) new StoreInstr(curBlock,"sw").addOperand(value,pointerOp);
        else {
            assert pointerOp instanceof GlobalVar;
            Register addressReg = new VirtualRegister();
            new LaInstr(curBlock).addOperand(addressReg,pointerOp);
            addressReg.offset = new Immediate(0);
            new StoreInstr(curBlock,"sw").addOperand(value,addressReg);
        }
    }

    private void recurDown(Value node){  // used to travel on Operand_list
        if(node instanceof IRBasicBlock){
            if(node.ASMOperand == null) ((IRBasicBlock) node).accept(this);
        }else{
            assert node instanceof User;
            if(node.ASMOperand == null) ((User) node).accept(this);
        }
    }

    private boolean checkImmInstr(String op){
        switch(op){
            case "sub","mul","div","rem" ->{return false;}
            default ->{ return true;}
        }
    }

    private void arthForm(Register dest, Operand unknown, Operand imm, String op){
        if(unknown instanceof Immediate){ // swap
            Operand tmp = unknown;
            unknown = imm;
            imm = tmp;
        }
        assert unknown instanceof Register;
        if(imm instanceof Immediate){
            if(checkImmInstr(op)){
                new ArthInstr(op,curBlock).addOperand(dest,unknown,imm);
            }else{
                Register immReg = new VirtualRegister();
                new LiInstr(curBlock).addOperand(immReg,imm);
                new ArthInstr(op,curBlock).addOperand(dest,unknown,immReg);
            }
        }else{
            new ArthInstr(op,curBlock).addOperand(dest,unknown,imm);
        }
    }
}