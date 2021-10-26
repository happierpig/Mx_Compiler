package Utils;

import AST.*;
import java.util.HashMap;
import java.util.Map;

public class Scope{
    public Map<String, TypeNode> Variable_Table;
    public Scope parent;

    public Scope(Scope _parent){
        Variable_Table = new HashMap<>();
        this.parent = _parent;
    }

    public TypeNode fetch_Variable_Type(String identifier){
        if(Variable_Table.containsKey(identifier)) return Variable_Table.get(identifier);
        else if(this.parent != null) return parent.fetch_Variable_Type(identifier);
        else return null;
    }

    public boolean contains_Variable(String identifier){
        return Variable_Table.containsKey(identifier);
    }

    public void define_Variable(String identifier,TypeNode _type){
        Variable_Table.put(identifier, _type);
    }


}