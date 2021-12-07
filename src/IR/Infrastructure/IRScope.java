package IR.Infrastructure;

import IR.BaseClass.Value;
import java.util.HashMap;

public class IRScope{
    public enum scopeType {Global, Flow, Func, Common}
    public IRScope parent;
    public HashMap<String, Value> valueTable;
    public scopeType type;
    public boolean valid;

    public IRScope(IRScope _parent, scopeType _ty){
        this.type = _ty;
        this.parent = _parent;
        this.valueTable = new HashMap<>();
        if(_parent == null) this.valid = true;
        else this.valid = _parent.valid;
    }

    public Value fetchValue(String identifier){
        Value tmp = this.valueTable.get(identifier);
        return tmp == null ? this.parent.fetchValue(identifier) : tmp;
    }

    public void setVariable(String identifier,Value operand){
        this.valueTable.put(identifier,operand);
    }

    public void setInvalid(){
        switch(this.type){
            case Flow, Global -> {}
            case Common, Func -> this.valid = false;
        }
    }

    public IRScope upRoot(){
        assert this.parent != null;
        if(!this.valid) this.parent.setInvalid();
        return this.parent;
    }

    public boolean isValid(){
        return this.valid;
    }
}