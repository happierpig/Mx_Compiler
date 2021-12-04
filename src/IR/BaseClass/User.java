package IR.BaseClass;

import IR.TypeSystem.IRType;
import java.util.ArrayList;

// Referenced from https://llvm.org/doxygen/User_8h_source.html
//===----------------------------------------------------------------------===//
//
// This class defines the interface that one who uses a Value must implement.
// Each instance of the Value class keeps track of what User's have handles
// to it.
//
//  * Instructions are the largest class of Users.
//  * Constants may be users of other constants (think arrays and stuff)
//
//===----------------------------------------------------------------------===//
public abstract class User extends Value{
    public ArrayList<Value> operands;

    public User(String _name, IRType _type) {
        super(_name, _type);
        this.operands = new ArrayList<>();
    }

    public void addOperand(Value _operand){
        _operand.addUser(this);
        this.operands.add(_operand);
    }

    public Value getOperand(int index){
        return operands.get(index);
    }
}