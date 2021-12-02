package IR.Operand;

import IR.TypeSystem.IRType;
import IR.TypeSystem.Pointer;

public class GlobalVariable extends IROperand{
    public String name;
    public IRType type;

    public GlobalVariable(String _name,IRType _type){
        this.name = _name;
        this.type = new Pointer(_type);
    }

    @Override
    public String toString() {
        return this.type.toString() + this.getName();
    }

    @Override
    public String getName() {
        return "@_glo_" + this.name;
    }
}