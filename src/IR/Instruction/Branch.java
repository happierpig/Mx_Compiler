package IR.Instruction;

import IR.BaseClass.Value;
import IR.IRBasicBlock;
import IR.TypeSystem.IntegerType;
import IR.TypeSystem.VoidType;

public class Branch extends IRInstruction{

    public Branch(IRBasicBlock _block, IRBasicBlock _destBlock) {
        super("_branch", new VoidType(), _block);
        this.addOperand(_destBlock);
    }

    public Branch(IRBasicBlock _block, Value _condition, IRBasicBlock _lBlock, IRBasicBlock _rBlock){
        super("_branch", new VoidType(), _block);
        assert _condition.type.isEqual(new IntegerType(1));
        this.addOperand(_condition);
        this.addOperand(_lBlock);
        this.addOperand(_rBlock);
    }

    @Override
    public String getName() {
        throw new RuntimeException("[Debug] Why get name in Branch ? ");
    }

    @Override
    public String toString() {
        if(this.operands.size() == 1){
            return "br " + this.getOperand(0).getTypeName();
        }else return "br " + this.getOperand(0).getTypeName() + ", "
                            + this.getOperand(1).getTypeName() + ", "
                            + this.getOperand(2).getTypeName();
    }
}