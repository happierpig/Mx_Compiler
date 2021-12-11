package IR.TypeSystem;

import java.util.HashMap;

public class StructType extends IRType{
    public HashMap<String, IRType> typeTable;
    public HashMap<String, Integer> indexTable;
    public Integer count;
    public String name;

    public StructType(String _name){
        typeTable = new HashMap<>();
        indexTable = new HashMap<>();
        count = 0;
        name = "class_" + _name;
    }

    public StructType addMember(String identifier, IRType ty){
        typeTable.put(identifier,ty);
        indexTable.put(identifier, count++);
        return this;
    }

    @Override
    public int byteSize() {
        int total = 0;
        for(IRType ty : typeTable.values()) total += ty.byteSize();
        return total;
    }

    @Override
    public String toString() {
        return "%" + name;
    }

    @Override
    public boolean isEqual(IRType other) {
        return (other instanceof StructType) && ((StructType)other).name.equals(name);
    }
}