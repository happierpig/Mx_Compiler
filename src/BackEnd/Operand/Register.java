package BackEnd.Operand;

public abstract class Register extends Operand{
    public Immediate offset;
    public Register(String _name) {
        super(_name);
        this.offset = null;
    }
}