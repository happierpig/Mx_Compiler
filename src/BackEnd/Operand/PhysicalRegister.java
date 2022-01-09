package BackEnd.Operand;

import java.util.ArrayList;
import java.util.Arrays;

public class PhysicalRegister extends Register{

    public static final ArrayList<String> phyRegName = new ArrayList<>(Arrays.asList("zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"));

    public static final ArrayList<PhysicalRegister> callerSaved = new ArrayList<>(){
        {
            add(getPhyReg(1));
            for(int i = 5;i <= 7;++i) add(getPhyReg(i));
            for(int i = 10;i <= 17;++i) add(getPhyReg(i));
            for(int i = 28;i <= 31;++i) add(getPhyReg(i));
        }
    };

    public static final ArrayList<PhysicalRegister> calleeSaved = new ArrayList<>(){
        {
            add(getPhyReg(2));
            add(getPhyReg(8));  add(getPhyReg(9));
            for(int i = 18;i <= 27;++i) add(getPhyReg(i));
        }
    };

    public PhysicalRegister(String _name) {
        super(_name);
    }

    public PhysicalRegister(String _name,Immediate _offset){
        super(_name);
        this.offset = _offset;
    }

    public static PhysicalRegister getPhyReg(String name){
        return new PhysicalRegister(name);
    }

    public static PhysicalRegister getPhyReg(int index){
        String name = PhysicalRegister.phyRegName.get(index);
        return new PhysicalRegister(name);
    }
}