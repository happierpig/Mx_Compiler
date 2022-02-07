package BackEnd.Operand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PhysicalRegister extends Register{

    public static final ArrayList<String> phyRegName = new ArrayList<>(Arrays.asList("zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"));

    public static final ArrayList<Integer> callerSaved = new ArrayList<>(Arrays.asList(
            1,
            5,6,7,
            10,11,12,13,14,15,16,17,
            28,29,30,31
    ));

    public static final ArrayList<Integer> calleeSaved = new ArrayList<>(Arrays.asList(
//            2, sp backup and recover by hand
//            8, s0 backup and recover by hand
            9,
            18,19,20,21,22,23,24,25,26,27
    ));

    public static final HashSet<Integer> callerSaved_check = new HashSet<>(callerSaved);

    public PhysicalRegister(String _name) {
        super(_name);
    }

    public PhysicalRegister(VirtualRegister other){
        super(phyRegName.get(other.color));
        this.color = other.color;
        this.offset = new Immediate(other.offset.value);
    }

    public PhysicalRegister(int _color,VirtualRegister other){
        super(phyRegName.get(_color));
        this.color = _color;
        this.offset = new Immediate(other.offset.value);
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