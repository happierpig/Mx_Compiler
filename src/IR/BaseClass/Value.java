package IR.BaseClass;

import IR.TypeSystem.IRType;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced from https://llvm.org/doxygen/Value_8h_source.html
//===----------------------------------------------------------------------===//
/// This is a very important LLVM class. It is the base class of all values
/// computed by a program that may be used as operands to other values. Value is
/// the super class of other important classes such as Instruction and Function.
/// All Values have a Type. Type is not a subclass of Value. Some values can
/// have a name and they belong to some Module.  Setting the name on the Value
/// automatically updates the module's symbol table.
///
/// Every value has a "use list" that keeps track of which other Values are
/// using this Value.
//===----------------------------------------------------------------------===//
public class Value{
    public String name;
    public IRType type;
    public ArrayList<User> useList;
    public static HashMap<String, Integer> renamingMachine = new HashMap<>();

    public Value(String _name,IRType _type){
        this.name = renaming(_name);
        this.type = _type;
        this.useList = new ArrayList<>();
    }

    public String renaming(String _name){
        if(_name.equals("_f_main")) return "main";
        Integer count = renamingMachine.get(_name);
        if(count == null){
            count = 0;
            renamingMachine.put(_name,count);
        }else{
            count++;
            renamingMachine.put(_name,count);
        }
        return _name + count;
    }

    public static void refresh(){
        Integer count = renamingMachine.get("_str");
        renamingMachine.clear();
        renamingMachine.put("_str",count);
    }

    public void addUser(User _user){
        this.useList.add(_user);
    }

    public String getName(){
        return "%" + this.name;
    }

    public String getTypeName(){
        return type.toString() + " " + this.getName();
    }

    public String toString(){
        throw new RuntimeException("[Debug] Why use base value's toString");
    }
}