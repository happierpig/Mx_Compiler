package BackEnd.Operand;

public class VirtualRegister extends Register{
    public VirtualRegister(int virtualCount) {
        super("v"+virtualCount);
        this.offset = new Immediate(0);
    }

    public VirtualRegister(VirtualRegister other){
        super(other.name);
        this.offset = new Immediate(other.offset.value);
        this.color = other.color;
    }

    public VirtualRegister(int _color,int virtualCount){
        super("v"+virtualCount);
        this.color = _color;
        this.offset = new Immediate(0);
    }

    public VirtualRegister(Immediate _offset,int _color,int virtualCount){
        super("v"+virtualCount);
        this.offset = _offset;
        this.color = _color;
    }

    @Override
    public String getName(){
        if(color == 32) return this.name;
        else return PhysicalRegister.phyRegName.get(color);
    }
}