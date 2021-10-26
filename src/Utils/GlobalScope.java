package Utils;

import AST.*;

import java.util.HashMap;
import java.util.Map;

public class GlobalScope extends Scope{
    public Map<String,FuncDefNode> Functions_Table;
    public Map<String,GlobalScope> Class_Table;

    public GlobalScope(Scope _parent){
        super(_parent);
        Functions_Table = new HashMap<>();
        Class_Table = new HashMap<>();
    }

    public boolean contains_Function(String identifier){return Functions_Table.containsKey(identifier);}

    public boolean contains_Class(String identifier){return Class_Table.containsKey(identifier);}

    public void define_Function(String identifier,FuncDefNode _node){Functions_Table.put(identifier,_node);}

    public void define_Class(String identifier,GlobalScope _node){Class_Table.put(identifier, _node);}

    public FuncDefNode fetch_Function(String identifier){
        if(contains_Function(identifier)) return this.Functions_Table.get(identifier);
        else return null;
    }
}