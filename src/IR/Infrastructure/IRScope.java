package IR.Infrastructure;

import IR.Operand.IROperand;
import java.util.HashMap;

public class IRScope{
    public IRScope parent;
    public HashMap<String, IROperand> valueTable;

    public IRScope(IRScope _parent){
        this.parent = _parent;
        this.valueTable = new HashMap<>();
    }

    public IROperand fetchOperand(String identifier){
        IROperand tmp = this.valueTable.get(identifier);
        return tmp == null ? this.parent.fetchOperand(identifier) : tmp;
    }

    public void setVariable(String identifier,IROperand operand){
        this.valueTable.put(identifier,operand);
    }
}