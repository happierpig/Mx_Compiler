package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import BackEnd.Operand.Register;
import BackEnd.Operand.VirtualRegister;

/*
    Pseudo-instruction used to load immediate to destination register
 */
public class LiInstr extends Instruction{
    // 0 for rd ; 1 for imm
    public LiInstr(ASMBlock _curBlock) {
        super(_curBlock, "li");
    }

    @Override
    public void rewriteUse(String origin, VirtualRegister born) {
        throw new RuntimeException("[Debug] Why call this");
    }

    @Override
    public void rewriteDef(String origin, VirtualRegister born) {
        if(rd.getName().equals(origin)){
            rd = born;
            def.remove(origin);
            def.add(born.getName());
        }
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 2;
        rd = args[0]; rs1 = args[1]; rs2 = null;
        this.def.add(rd.getName());
        return this;
    }

    @Override
    public String printASM() {
        return op + "\t" + rd.getName() + "," + rs1.getName();
    }
}