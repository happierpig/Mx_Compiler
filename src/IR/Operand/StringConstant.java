package IR.Operand;

import IR.TypeSystem.Array;
import IR.TypeSystem.Integer;
import IR.TypeSystem.Pointer;

public class StringConstant extends IRConstant {
    private static int globalCount = 0;
    public String message;
    public String name;

    public StringConstant(String _msg) {
        this.message = reload(_msg);
        this.type = new Pointer(new Array(new Integer(8), this.message.length()+1)); // +1 for \00
        this.name = "_str_" + StringConstant.globalCount;
        StringConstant.globalCount++;
    }

    private String reload(String origin) {
        return origin
                .substring(1,origin.length()-1)
                .replace("\\", "\\5C")
                .replace("\n", "\\0A")
                .replace("\"", "\\22")
                .replace("\0", "\\00")
                .replace("\t", "\\09");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "i8* getelementptr inbounds (" + ((Pointer) this.type).baseType.toString() + ", " + this.type.toString() + " @" + this.getName() + ", i32 0, i32 0)";
    }

    public String printLLVM() {
        return "@" + this.getName() + " = private unnamed_addr constant " + ((Pointer)this.type).baseType.toString() + " c\"" + this.message + "\\00\"";    }
}
