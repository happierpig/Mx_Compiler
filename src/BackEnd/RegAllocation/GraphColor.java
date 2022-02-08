package BackEnd.RegAllocation;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Infrastructure.ASMFunction;
import BackEnd.Infrastructure.ASMModule;
import BackEnd.Instruction.*;
import BackEnd.Operand.Immediate;
import BackEnd.Operand.PhysicalRegister;
import BackEnd.Operand.VirtualRegister;
import org.antlr.v4.runtime.misc.Pair;
import java.util.*;

public class GraphColor {
    static final int K = 28;
    public ASMModule ripe;

    public HashSet<String> physical_register;
    public HashSet<Pair<String,String>> conflict_check;
    public HashMap<String, HashSet<String>> conflict_graph;

    public HashMap<String,Integer> degree;
    public HashMap<String,Integer> weight;

    public HashMap<String,HashSet<MoveInstr>> move_list;
    public HashSet<MoveInstr> worklist_move;
    public HashSet<MoveInstr> active_move;
    public HashSet<MoveInstr> constrained_move;
    public HashSet<MoveInstr> coalesced_move;
    public HashSet<MoveInstr> frozen_move;

    public HashSet<String> initial;
    public HashMap<String,String> alias;
    public HashMap<String,Integer> color;

    public HashSet<String> simplify_worklist;
    public HashSet<String> freeze_worklist;
    public HashSet<String> spill_worklist;

    public Stack<String> select_stack;
    public HashSet<String> coalesced_nodes;
    public HashSet<String> colored_nodes;
    public HashSet<String> spilled_nodes;

    public GraphColor(ASMModule raw){
        this.ripe = raw;
        physical_register = new HashSet<>(PhysicalRegister.phyRegName);
        int count = 0;
//        System.out.println(this.ripe.printASM());
//        System.out.println("\t\t\t--------Start------");
        for(ASMFunction func : raw.functions){
            if(func.isBuiltin) continue;
            ++count;
            process(func);
            regAllocate(func);
//            System.out.print("\t\t\t-----After"); System.out.println(count);System.out.println(this.ripe.printASM());
        }
    }

    private void regAllocate(ASMFunction func){
        assert spilled_nodes.size() == 0;
        for(ASMBlock bb : func.blockList){
            LinkedList<Instruction> newList = new LinkedList<>();
            for(Instruction inst : bb.instructionList){
                if(inst instanceof MoveInstr){
                    if(Objects.equals(color.get(inst.rd.getName()), color.get(inst.rs1.getName()))) continue;
                }
                if(inst.rd instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rd;
                    Integer finalColor = color.get(vReg.getName());
                    assert finalColor != null;
                    inst.rd = new PhysicalRegister(finalColor,vReg);
                }
                if(inst.rs1 instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rs1;
                    Integer finalColor = color.get(vReg.getName());
                    assert finalColor != null;
                    inst.rs1 = new PhysicalRegister(finalColor,vReg);
                }
                if(inst.rs2 instanceof VirtualRegister){
                    VirtualRegister vReg = (VirtualRegister) inst.rs2;
                    Integer finalColor = color.get(vReg.getName());
                    assert finalColor != null;
                    inst.rs2 = new PhysicalRegister(finalColor,vReg);
                }
                newList.add(inst);
            }
            bb.instructionList = newList;
        }
        LinkedList<Instruction> entryList = func.entryBlock().instructionList;
        LinkedList<Instruction> exitList = func.exitBlock().instructionList;
        int offset = func.stackBias;
        if(offset < 2048){
            entryList.addFirst(new ArthInstr("add",null).addOperand(
                    new PhysicalRegister("s0"),new PhysicalRegister("sp"),new Immediate(offset)));
            entryList.addFirst(new ArthInstr("add",null)
                    .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new Immediate(-offset)));
            exitList.addLast(new ArthInstr("add",null)
                    .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new Immediate(offset)));
        }else{
            entryList.addFirst(new ArthInstr("sub",null).addOperand(
                    new PhysicalRegister("s0"),new PhysicalRegister("sp"),new PhysicalRegister("t0")));
            entryList.addFirst(new ArthInstr("add",null)
                    .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new PhysicalRegister("t0")));
            entryList.addFirst(new LiInstr(null).addOperand(new PhysicalRegister("t0"),new Immediate(-offset)));
            exitList.addLast(new LiInstr(null).addOperand(new PhysicalRegister("t0"),new Immediate(offset)));
            exitList.addLast(new ArthInstr("add",null)
                    .addOperand(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new PhysicalRegister("t0")));
        }
        // Maintain s0
        entryList.addFirst(new StoreInstr(null,"sw").addOperand(
                new PhysicalRegister("s0"),new PhysicalRegister("sp",new Immediate(-4))));
        exitList.addLast(new LoadInstr(null,"lw").addOperand(
                new PhysicalRegister("s0"),new PhysicalRegister("sp",new Immediate(-4))));
        exitList.addLast(new RetInstr(null));
    }

    private void process(ASMFunction func){
        preProcess(func);
        liveAnalysis(func);
        makeWorkList();
        while(true){
            if(simplify_worklist.size() != 0) simplify();
            else if(worklist_move.size() != 0) coalesce();
            else if(freeze_worklist.size() != 0) freeze();
            else if(spill_worklist.size() != 0) selectSpill();
            else break;
        }
        assignColors();
        if(spilled_nodes.size() != 0){
            rewriteProgram(func);
            process(func);
        }
    }

    private void preProcess(ASMFunction func){
        conflict_check = new HashSet<>();
        conflict_graph = new HashMap<>();
        degree = new HashMap<>();
        weight = new HashMap<>();
        move_list = new HashMap<>();
        worklist_move = new HashSet<>();
        active_move = new HashSet<>();
        constrained_move = new HashSet<>();
        coalesced_move = new HashSet<>();
        frozen_move = new HashSet<>();
        initial = new HashSet<>();
        alias = new HashMap<>();
        color = new HashMap<>();
        simplify_worklist = new HashSet<>();
        freeze_worklist = new HashSet<>();
        spill_worklist = new HashSet<>();
        select_stack = new Stack<>();
        coalesced_nodes = new HashSet<>();
        colored_nodes = new HashSet<>();
        spilled_nodes = new HashSet<>();

        for(int i = 0;i < 32;++i) color.put(PhysicalRegister.phyRegName.get(i),i);
        for(ASMBlock bb : func.blockList){
            for(Instruction inst : bb.instructionList){
                initial.addAll(inst.def);
                initial.addAll(inst.use);
            }
        }
        for(String regName : initial){
            if(physical_register.contains(regName)){
                weight.put(regName,Integer.MAX_VALUE);
                degree.put(regName,Integer.MAX_VALUE);
            }else{
                weight.put(regName,0);
                degree.put(regName,0);
            }
            conflict_graph.put(regName,new HashSet<>());
            move_list.put(regName,new HashSet<>());
            alias.put(regName,null);
        }
        initial.removeAll(physical_register);
    }

    /*
        Analyse function's liveness and build conflict graph.
        Information is stored in global class variable.
     */
    private void liveAnalysis(ASMFunction func){
        // Compress that each node represents a basic block.
        HashMap<ASMBlock,HashSet<String>> bb_def = new HashMap<>();
        HashMap<ASMBlock,HashSet<String>> bb_use = new HashMap<>();
        for(ASMBlock bb : func.blockList){
            HashSet<String> tmp_def = new HashSet<>();
            HashSet<String> tmp_use = new HashSet<>();
            for(Instruction inst : bb.instructionList){
                inst.use.forEach(tmp->{
                    if(tmp_def.contains(tmp)) return;
                    tmp_use.add(tmp);
                });
                tmp_def.addAll(inst.def);
            }
            bb_def.put(bb,tmp_def);
            bb_use.put(bb,tmp_use);
        }
        // live_ness analysis on basic block
        HashMap<ASMBlock,HashSet<String>> bb_live_in = new HashMap<>();
        HashMap<ASMBlock,HashSet<String>> bb_live_out = new HashMap<>();
        for(ASMBlock bb : func.blockList){
            bb_live_in.put(bb,new HashSet<>());
            bb_live_out.put(bb,new HashSet<>());
        }
        while(true){
            boolean flag = true;
            for(int i = func.blockList.size() - 1;i >= 0;--i){
                ASMBlock bb = func.blockList.get(i);
                HashSet<String> tmp_out = new HashSet<>();
                bb.successors.forEach(tmpbb->tmp_out.addAll(bb_live_in.get(tmpbb)));
                int check_out_new = tmp_out.size(),check_out_old = bb_live_out.get(bb).size();
                bb_live_out.replace(bb,tmp_out);
                HashSet<String> tmp_in = new HashSet<>(bb_use.get(bb));
                HashSet<String> tmp_set = new HashSet<>(tmp_out);
                tmp_set.removeAll(bb_def.get(bb));
                tmp_in.addAll(tmp_set);
                int check_in_new = tmp_in.size(),check_in_old = bb_live_in.get(bb).size();
                bb_live_in.replace(bb,tmp_in);
                flag = flag && (check_in_new == check_in_old) && (check_out_new == check_out_old);
            }
            if(flag) break;
        }
        // Construct conflict graph.
        for(ASMBlock bb : func.blockList){
            HashSet<String> inst_out = bb_live_out.get(bb);
            for(int i = bb.instructionList.size() - 1;i >= 0;--i){
                Instruction inst = bb.instructionList.get(i);
                // calculate weight
                inst.use.forEach(tmp->{
                    if(!physical_register.contains(tmp)) weight.replace(tmp,weight.get(tmp)+1);
                });
                inst.def.forEach(tmp->{
                    if(!physical_register.contains(tmp)) weight.replace(tmp,weight.get(tmp)+1);
                });

                if(inst instanceof MoveInstr){
                    inst.use.forEach(inst_out::remove);
                    inst.use.forEach(tmp->move_list.get(tmp).add((MoveInstr) inst));
                    inst.def.forEach(tmp->move_list.get(tmp).add((MoveInstr) inst));
                    worklist_move.add((MoveInstr) inst);
                }
                inst.def.forEach(tmpu-> inst_out.forEach(tmpv->addEdge(tmpu,tmpv)));
                inst.def.forEach(inst_out::remove);
                inst_out.addAll(inst.use);
            }
            assert inst_out.size() == bb_live_in.get(bb).size();
        }
    }

    private void addEdge(String u, String v){
        if(conflict_check.contains(new Pair<>(u,v)) || u.equals(v)) return;
        conflict_check.add(new Pair<>(u,v));
        conflict_check.add(new Pair<>(v,u));
        // Pre-colored nodes don't need adjacency lists.
        if(!physical_register.contains(u)) {
            conflict_graph.get(u).add(v);
            degree.replace(u,degree.get(u)+1);
        }
        if(!physical_register.contains(v)) {
            conflict_graph.get(v).add(u);
            degree.replace(v,degree.get(v)+1);
        }
    }

    private HashSet<MoveInstr> nodeMoves(String regName){
        HashSet<MoveInstr> retVal = new HashSet<>(worklist_move);
        retVal.addAll(active_move);
        retVal.retainAll(move_list.get(regName));
        return retVal;
    }

    private boolean moveRelated(String regName){
        return nodeMoves(regName).size() != 0;
    }

    private void makeWorkList(){
        for(String regName : initial){
            if(degree.get(regName) >= K) spill_worklist.add(regName);
            else if(moveRelated(regName)) freeze_worklist.add(regName);
            else simplify_worklist.add(regName);
        }
    }

    private HashSet<String> adjacent(String regName){
        HashSet<String> retVal = new HashSet<>(conflict_graph.get(regName));
        select_stack.forEach(retVal::remove);
        retVal.removeAll(coalesced_nodes);
        return retVal;
    }

    private void enableMoves(HashSet<String> nodes){
        for(String regName : nodes){
            nodeMoves(regName).forEach(tmpReg->{
                if(active_move.contains(tmpReg)){
                    active_move.remove(tmpReg);
                    worklist_move.add(tmpReg);
                }
            });
        }
    }

    private void decrementDegree(String regName){
        int oldDegree = degree.get(regName);
        degree.replace(regName,oldDegree - 1);
        if(oldDegree == K){
            HashSet<String> tmpSet = new HashSet<>(conflict_graph.get(regName));
            tmpSet.add(regName);
            enableMoves(tmpSet);
            spill_worklist.remove(regName);
            if(moveRelated(regName)) freeze_worklist.add(regName);
            else simplify_worklist.add(regName);
        }
    }

    private void simplify(){
        String toSimplify = simplify_worklist.iterator().next();
        simplify_worklist.remove(toSimplify);
        select_stack.push(toSimplify);
        adjacent(toSimplify).forEach(this::decrementDegree);
    }

    private void addWorkList(String regName){
        if((!physical_register.contains(regName)) && (!moveRelated(regName)) && degree.get(regName) < K){
            freeze_worklist.remove(regName);
            simplify_worklist.add(regName);
        }
    }

    private String getAlias(String node){
        return coalesced_nodes.contains(node) ? getAlias(alias.get(node)) : node;
    }

    private boolean OK(String t, String r){
        return degree.get(t) < K || physical_register.contains(t) || conflict_check.contains(new Pair<>(t,r));
    }

    private boolean conservative(HashSet<String> nodes){
        int k = 0;
        for(String regName : nodes){
            if(degree.get(regName) >= K) k += 1;
        }
        return k < K;
    }

    private void combine(String u, String v){
        if(freeze_worklist.contains(v)) freeze_worklist.remove(v);
        else spill_worklist.remove(v);
        coalesced_nodes.add(v);
        alias.put(v,u);
        move_list.get(u).addAll(move_list.get(v));
        adjacent(v).forEach(regName->{
            addEdge(regName,u);
            decrementDegree(regName);
        });
        if(degree.get(u) >= K && freeze_worklist.contains(u)){
            freeze_worklist.remove(u);
            spill_worklist.add(u);
        }
    }

    private void coalesce(){
        MoveInstr toCoalesce = worklist_move.iterator().next();
        worklist_move.remove(toCoalesce);
        String x = getAlias(toCoalesce.rd.getName()), y = getAlias(toCoalesce.rs1.getName()),u,v;
        if(physical_register.contains(y)){
            u = y;
            v = x;
        }else{
            u = x;
            v = y;
        }
        if(u.equals(v)){
            coalesced_move.add(toCoalesce);
            addWorkList(u);
        }else if(physical_register.contains(v) || conflict_check.contains(new Pair<>(u,v))){
            constrained_move.add(toCoalesce);
            addWorkList(u);
            addWorkList(v);
        }else{
            boolean mode = true; // true for George coalesce ; false for active move
            if(physical_register.contains(u)){
                for(String tmp : adjacent(v)){
                    if(!OK(tmp,u)){
                        mode = false;
                        break;
                    }
                }
            }else{
                HashSet<String> tmp = adjacent(u);
                tmp.addAll(adjacent(v));
                if(!conservative(tmp)) mode = false;
            }
            if(mode){
                coalesced_move.add(toCoalesce);
                combine(u,v);
                addWorkList(u);
            }else active_move.add(toCoalesce);
        }
    }

    private void freezeMove(String u){
        for(MoveInstr inst : nodeMoves(u)){
            String v;
            if(getAlias(u).equals(getAlias(inst.rs1.getName()))) v = getAlias(inst.rd.getName());
            else v = getAlias(inst.rs1.getName());
            active_move.remove(inst);
            frozen_move.add(inst);
            if(nodeMoves(v).size() == 0 && degree.get(v) < K){
                freeze_worklist.remove(v);
                simplify_worklist.add(v);
            }
        }
    }

    private void freeze(){
        String toFreeze = freeze_worklist.iterator().next();
        freeze_worklist.remove(toFreeze);
        simplify_worklist.add(toFreeze);
        freezeMove(toFreeze);
    }

    private void selectSpill(){
        // Priority : weight / degree
        String toSpill = null;
        double min = -1;
        for(String candidate : spill_worklist){
            if(degree.get(candidate) == 0) continue;
            double value = 1.0 * weight.get(candidate) / degree.get(candidate);
            if(min < 0 || min > value){
                min = value;
                toSpill = candidate;
            }
        }
        assert toSpill != null;
        spill_worklist.remove(toSpill);
        simplify_worklist.add(toSpill);
        freezeMove(toSpill);
    }

    private void assignColors(){
        while(!select_stack.empty()){
            String toAssign = select_stack.pop();
            ArrayList<Integer> okColors = new ArrayList<>();
            for(int i = 31;i >= 5;--i) okColors.add(i);
            okColors.add(1); // ra ; 28 physical register
            conflict_graph.get(toAssign).forEach(w->{
                String tmp = getAlias(w);
                if(colored_nodes.contains(tmp) || physical_register.contains(tmp)) okColors.remove(color.get(tmp));
            });
            if(okColors.size() == 0) spilled_nodes.add(toAssign);
            else{
                colored_nodes.add(toAssign);
                boolean assigned = false; // for assign callerSaved in priority
                for (int futureColor : okColors) {
                    if (PhysicalRegister.callerSaved_check.contains(futureColor)) {
                        assigned = true;
                        color.put(toAssign, futureColor);
                        break;
                    }
                }
                if(!assigned){ // only callee saved
                    color.put(toAssign,okColors.get(0));
                }
            }
        }
        coalesced_nodes.forEach(regName->color.put(regName,color.get(getAlias(regName))));
    }

    private void rewriteProgram(ASMFunction func){
        HashMap<String,Integer> subTable = new HashMap<>();
        spilled_nodes.forEach(regName->{
            int offset = func.allocStack().value;
            subTable.put(regName,offset);
        });
        for(ASMBlock bb : func.blockList){
            LinkedList<Instruction> writtenInst = new LinkedList<>();
            for(Instruction inst : bb.instructionList){
                for(int i = 0;i < inst.use.size();++i){
                    String regName = inst.use.get(i);
                    if(spilled_nodes.contains(regName)){ // insert load
                        int offset = subTable.get(regName);
                        assert offset >= 0;
                        VirtualRegister tmpReg = new VirtualRegister(func.virtualIndex++);
                        if(offset < 2048){
                            Instruction newInstr = new LoadInstr(null,"lw");
                            newInstr.addOperand(tmpReg,new VirtualRegister(new Immediate(-offset),8, func.virtualIndex++));
                            writtenInst.add(newInstr);
                        }else{  // previous li add load
                            VirtualRegister tmpAddress = new VirtualRegister(func.virtualIndex++);
                            writtenInst.add(new LiInstr(null).addOperand(tmpAddress,new Immediate(-offset)));
                            writtenInst.add(new ArthInstr("add",null).addOperand(tmpAddress,new PhysicalRegister("s0"),tmpAddress));
                            writtenInst.add(new LoadInstr(null,"lw").addOperand(tmpReg,tmpAddress));
                        }
                        inst.rewriteUse(regName,tmpReg);
                    }
                }
                writtenInst.add(inst);
                for(String regName : inst.def){
                    if(spilled_nodes.contains(regName)){ // insert load
                        int offset = subTable.get(regName);
                        assert offset >= 0;
                        VirtualRegister tmpReg = new VirtualRegister(func.virtualIndex++);
                        if(offset < 2048){
                            Instruction newInstr = new StoreInstr(null,"sw");
                            newInstr.addOperand(tmpReg,new VirtualRegister(new Immediate(-offset),8, func.virtualIndex++));
                            writtenInst.add(newInstr);
                        }else{  // previous li add load
                            VirtualRegister tmpAddress = new VirtualRegister(func.virtualIndex++);
                            writtenInst.add(new LiInstr(null).addOperand(tmpAddress,new Immediate(-offset)));
                            writtenInst.add(new ArthInstr("add",null).addOperand(tmpAddress,new PhysicalRegister("s0"),tmpAddress));
                            writtenInst.add(new StoreInstr(null,"sw").addOperand(tmpReg,tmpAddress));
                        }
                        inst.rewriteDef(regName,tmpReg);
                    }
                }
            }
            bb.instructionList = writtenInst;
        }
    }

}