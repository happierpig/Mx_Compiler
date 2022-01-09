package BackEnd.Operand;

public class GlobalVar extends Operand{
    public String literal; // String literal or something else;
    // la use name to get address

    public GlobalVar(String _name) {
        super(_name);
        literal = null;
    }

    public GlobalVar(String _name, String _value){
        super(_name);
        literal = _value;
    }
}