package BackEnd.Operand;

public class VirtualRegister extends Register{
    public static int virtualCount = 0;

    public VirtualRegister() {
        super("v"+virtualCount);
        virtualCount++;
    }

    public static void resetCount(){
        virtualCount = 0;
    }
}