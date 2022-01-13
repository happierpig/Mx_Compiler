package BackEnd.Operand;

/*
    A class for Immediate and frame-pointer offset
 */
public class Immediate extends Operand{
    public int value;

    public Immediate(int _value){
        super(String.valueOf(_value));
        this.value = _value;
    }
}