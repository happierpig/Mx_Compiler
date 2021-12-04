package IR.Operand;

import IR.TypeSystem.ArrayType;
import IR.TypeSystem.IntegerType;
import IR.TypeSystem.PointerType;

public class StringConstant extends IRConstant{
    public String value;

    // string after processing
    public StringConstant(String _value){
        super("_str",new PointerType(new ArrayType(new IntegerType(8), _value.length()+1)));
        this.value = _value + "\\00";
    }

    @Override
    public String getName() {
        return "@" + this.name;
    }

    @Override
    public String toString() {
        return this.getName() + " = private unnamed_addr constant " + ((PointerType)this.type).baseType.toString() + " c\"" + this.value + "\"";
    }
}