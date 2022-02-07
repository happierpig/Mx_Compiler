package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import BackEnd.Operand.Register;
import BackEnd.Operand.VirtualRegister;

public class StoreInstr extends Instruction{
    // sw rs2, imm(rs1)
    // 0 rs2 ; 1 rs1(contain offset)
    public StoreInstr(ASMBlock _curBlock, String _op) {
        super(_curBlock, _op);
    }

    @Override
    public void rewriteUse(String origin, VirtualRegister born) {
        if(rs1.getName().equals(origin)){
            rs1 = born;
            use.remove(origin);
            use.add(born.getName());
        }
        if(rs2.getName().equals(origin)){
            rs2 = born;
            use.remove(origin);
            use.add(born.getName());
        }
    }

    @Override
    public void rewriteDef(String origin, VirtualRegister born) {
        throw new RuntimeException("[Debug] Why call rewrite Def");
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 2;
        rd = null; rs1 = args[1]; rs2 = args[0];
        this.use.add(rs1.getName());
        this.use.add(rs2.getName());
        return this;
    }

    @Override
    public String printASM() {
        return String.format("%s\t%s, %s(%s)", op, rs2.getName(), ((Register)rs1).offset, rs1.getName());
    }

}