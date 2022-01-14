package BackEnd.Operand;

public class VirtualRegister extends Register{
    public VirtualRegister(int virtualCount) {
        super("v"+virtualCount);
    }

    public VirtualRegister(int _color,int virtualCount){
        super("v"+virtualCount);
        this.color = _color;
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