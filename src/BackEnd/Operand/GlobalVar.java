package BackEnd.Operand;

public class GlobalVar extends Operand{
    public String literal;

    public GlobalVar(String _name) {
        super(_name);
        literal = null;
    }

    public GlobalVar(String _name, String _value){
        super(_name);
        literal = _value;
    }
}