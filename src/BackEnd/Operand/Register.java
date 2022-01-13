package BackEnd.Operand;

public abstract class Register extends Operand{
    public Immediate offset;
    public int color;    // 32 for null; 0-31 represent physical register.

    public Register(String _name) {
        super(_name);
        this.offset = null;
        this.color = 32;
    }

    public void setOffset(Immediate _offset){
        this.offset = _offset;
    }

    public void paintColor(int _color){
        assert color == 32;
        this.color = _color;
    }
}