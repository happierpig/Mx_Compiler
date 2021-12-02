package IR.Operand;

import IR.TypeSystem.Array;
import IR.TypeSystem.Integer;
import IR.TypeSystem.Pointer;

public class StringConstant extends IROperand{
    public String message;
    public String name;

    public StringConstant(String _msg,String _name){
        this.message = reload(_msg);
        this.type = new Pointer(new Array(new Integer(8),this.message.length()));
        this.name = "_str_" + _name;
    }

    private String reload(String origin){
        return origin
                .replace("\\", "\\5C")
                .replace("\n", "\\0A")
                .replace("\"", "\\22")
                .replace("\0", "\\00")
                .replace("\t", "\\09");
    }

    @Override
    public String getName(){
        return "@" + this.name;
    }

    @Override
    public String toString() {
        return "@" + this.name + " = private unnamed_addr constant " + ((Pointer)this.type).baseType.toString() + "c\"" + this.message + "\"";
    }
}