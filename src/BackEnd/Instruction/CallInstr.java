package BackEnd.Instruction;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Operand.Operand;
import BackEnd.Operand.PhysicalRegister;

public class CallInstr extends Instruction{
    // 0 func
    public CallInstr(ASMBlock _curBlock) {
        super(_curBlock, "call");
    }

    @Override
    public Instruction addOperand(Operand... args) {
        assert args.length == 1;
        rd = args[0]; rs1 = null; rs2 = null;
        PhysicalRegister.callerSaved.forEach(index->this.def.add(PhysicalRegister.phyRegName.get(index)));
        return this;
    }

    @Override
    public String printASM() {
        return op + "\t" + rd.getName();
    }
}