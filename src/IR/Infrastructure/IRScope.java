package IR.Infrastructure;

import IR.BaseClass.Value;
import java.util.HashMap;

public class IRScope{
    public IRScope parent;
    public HashMap<String, Value> valueTable;

    public IRScope(IRScope _parent){
        this.parent = _parent;
        this.valueTable = new HashMap<>();
    }

    public Value fetchValue(String identifier){
        Value tmp = this.valueTable.get(identifier);
        return tmp == null ? this.parent.fetchValue(identifier) : tmp;
    }

    public void setVariable(String identifier,Value operand){
        this.valueTable.put(identifier,operand);
    }
}