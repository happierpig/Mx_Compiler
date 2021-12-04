package IR.Instruction;

import IR.BaseClass.User;
import IR.IRBasicBlock;
import IR.TypeSystem.IRType;

// Referenced from https://llvm.org/doxygen/IR_2Instruction_8h_source.html
//===----------------------------------------------------------------------===//
// This file contains the declaration of the Instruction class, which is the
// base class for all of the LLVM instructions.
//===----------------------------------------------------------------------===//
public abstract class IRInstruction extends User{
    public IRBasicBlock parentBlock;

    public IRInstruction(String _name, IRType _type, IRBasicBlock _block) {
        super(_name, _type);
        this.parentBlock = _block;
        if(_block != null) _block.addInstruction(this);
    }

}