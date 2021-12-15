package BackEnd.Operand;

public class Immediate extends Operand{
    public int value;

    public Immediate(int _value){
        super(String.valueOf(_value));
        this.value = _value;
    }
}