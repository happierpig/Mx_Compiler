package BackEnd.Operand;

public class VirtualRegister extends Register{
    public static int virtualCount = 0;

    public VirtualRegister() {
        super("v"+virtualCount);
        virtualCount++;
    }

    public VirtualRegister(int _color){
        super("v"+virtualCount);
        virtualCount++;
        this.color = _color;
    }

    public VirtualRegister(Immediate _offset,int _color){
        super("v"+virtualCount);
        virtualCount++;
        this.offset = _offset;
        this.color = _color;
    }

    public static void resetCount(){
        virtualCount = 0;
    }
}