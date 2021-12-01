package IR.Operand;

import IR.TypeSystem.*;

public class Register extends IROperand{
    String name;
    public static int globalCount = 0;

    public Register(IRType _type){
        super(_type);
        this.name = "_temp" + Register.globalCount;
        Register.globalCount++;
    }

    @Override
    public String toString() {
        return this.type.toString() + " %" + this.name;
    }

    public String getName(){
        return "%" + this.name;
    }

    public static void refresh(){
        Register.globalCount = 0;
    }

    public void setType(IRType _type){
        this.type = _type;
    }
}