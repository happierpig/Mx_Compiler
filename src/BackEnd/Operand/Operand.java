package BackEnd.Operand;

public abstract class Operand{
    public String name;

    public Operand(String _name){
        this.name = _name;
    }

    public String getName(){
        return this.name;
    }
}