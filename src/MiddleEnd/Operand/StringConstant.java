package MiddleEnd.Operand;

import MiddleEnd.Infrastructure.IRVisitor;
import MiddleEnd.TypeSystem.ArrayType;
import MiddleEnd.TypeSystem.IntegerType;
import MiddleEnd.TypeSystem.PointerType;

public class StringConstant extends IRConstant{
    public String value;

    // string after processing
    public StringConstant(String _value){
        super("_str",new PointerType(new ArrayType(new IntegerType(8), _value.length())));
        this.value = _value;
    }

    @Override
    public String getName() {
        return "@" + this.name;
    }

    @Override
    public String toString() {
        return this.getName() + " = private unnamed_addr constant " + ((PointerType)this.type).baseType.toString() + " c\"" + processRaw(this.value) + "\", align 1";
    }

    private String processRaw(String raw){
        return raw
                .replace("\\", "\\5C")
                .replace("\n", "\\0A")
                .replace("\"", "\\22")
                .replace("\t", "\\09")
                .replace("\0","\\00");
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}