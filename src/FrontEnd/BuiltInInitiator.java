package FrontEnd;

import Utils.GlobalScope;
import AST.*;
import Utils.Position;

import java.util.ArrayList;

public class BuiltInInitiator{
    public GlobalScope init(GlobalScope initScope){
        // built-in function
        ArrayList<VarDefNode> tmp_List = new ArrayList<>();
        tmp_List.add(new VarDefNode(new ClassTypeNode("string",new Position(-1,-1)),"str",null,new Position(-1,-1)));
        FuncDefNode tmp = new FuncDefNode(new VoidTypeNode(new Position(-1,-1)),"print",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("print",tmp);

        tmp = new FuncDefNode(new VoidTypeNode(new Position(-1,-1)),"println",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("println",tmp);

        tmp_List = new ArrayList<>();
        tmp_List.add(new VarDefNode(new ClassTypeNode("int",new Position(-1,-1)),"n",null,new Position(-1,-1)));
        tmp = new FuncDefNode(new VoidTypeNode(new Position(-1,-1)),"printInt",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("printInt",tmp);

        tmp = new FuncDefNode(new VoidTypeNode(new Position(-1,-1)),"printlnInt",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("printlnInt",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("string",new Position(-1,-1)),"getString",null,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("getString",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("int",new Position(-1,-1)),"getInt",null,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("getInt",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("string",new Position(-1,-1)),"toString",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("toString",tmp);

        // named for _malloc so that user can not call this function
        tmp = new FuncDefNode(new ClassTypeNode("string",new Position(-1,-1)),"_malloc",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_malloc",tmp);

        // operator overload for string
        tmp_List = new ArrayList<>();
        tmp_List.add(new VarDefNode(new ClassTypeNode("string",new Position(-1,-1)),"str1",null,new Position(-1,-1)));
        tmp_List.add(new VarDefNode(new ClassTypeNode("string",new Position(-1,-1)),"str2",null,new Position(-1,-1)));

        tmp = new FuncDefNode(new ClassTypeNode("string",new Position(-1,-1)),"_str_splice",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_str_splice",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("bool",new Position(-1,-1)),"_str_eq",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_str_eq",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("bool",new Position(-1,-1)),"_str_ne",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_str_ne",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("bool",new Position(-1,-1)),"_str_lt",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_str_lt",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("bool",new Position(-1,-1)),"_str_le",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_str_le",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("bool",new Position(-1,-1)),"_str_gt",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_str_gt",tmp);

        tmp = new FuncDefNode(new ClassTypeNode("bool",new Position(-1,-1)),"_str_ge",tmp_List,null,new Position(-1,-1));
        tmp.isBuiltin = true;
        initScope.define_Function("_str_ge",tmp);

        //built-in class
        initScope.define_Class("bool",new GlobalScope(initScope));
        initScope.define_Class("int",new GlobalScope(initScope));
        GlobalScope class_tmp = new GlobalScope(initScope); tmp_List = new ArrayList<>();
        class_tmp.define_Function("length",new FuncDefNode(new ClassTypeNode("int",new Position(-1,-1)),"length",null,null,new Position(-1,-1)).setBuiltin());
        tmp_List.add(new VarDefNode(new ClassTypeNode("int",new Position(-1,-1)),"left",null,new Position(-1,-1)));
        tmp_List.add(new VarDefNode(new ClassTypeNode("int",new Position(-1,-1)),"right",null,new Position(-1,-1)));
        class_tmp.define_Function("substring",new FuncDefNode(new ClassTypeNode("string",new Position(-1,-1)),"substring",tmp_List,null,new Position(-1,-1)).setBuiltin());
        class_tmp.define_Function("parseInt",new FuncDefNode(new ClassTypeNode("int",new Position(-1,-1)),"parseInt",null,null,new Position(-1,-1)).setBuiltin());
        tmp_List = new ArrayList<>();
        tmp_List.add(new VarDefNode(new ClassTypeNode("int",new Position(-1,-1)),"pos",null,new Position(-1,-1)));
        class_tmp.define_Function("ord",new FuncDefNode(new ClassTypeNode("int",new Position(-1,-1)),"ord",tmp_List,null,new Position(-1,-1)).setBuiltin());
        initScope.define_Class("string",class_tmp);
        return initScope;
    }
}