package BackEnd.Operand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PhysicalRegister extends Register{

    public static final ArrayList<String> phyRegName = new ArrayList<>(Arrays.asList("zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"));

    public static final HashMap<String,PhysicalRegister> phyReg = new HashMap<>(){
        {PhysicalRegister.phyRegName.forEach(tmp->put(tmp,new PhysicalRegister(tmp)));}
    };

    public PhysicalRegister(String _name) {
        super(_name);
    }

    public static PhysicalRegister getPhyReg(String name){
        PhysicalRegister tmp = phyReg.get(name);
        assert tmp != null;
        return tmp;
    }
}