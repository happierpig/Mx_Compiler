package BackEnd.RegAllocation;

import BackEnd.Infrastructure.ASMBlock;
import BackEnd.Infrastructure.ASMBuilder;
import BackEnd.Infrastructure.ASMFunction;
import BackEnd.Infrastructure.ASMModule;
import BackEnd.Instruction.Instruction;
import BackEnd.Instruction.MoveInstr;
import BackEnd.Operand.PhysicalRegister;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphColor {
    public ASMModule ripe;

    public HashSet<String> physical_register;
    public HashSet<Pair<String,String>> conflict_check;
    public HashMap<String, ArrayList<String>> conflict_graph;

    public GraphColor(ASMModule raw){
        this.ripe = raw;
        physical_register = new HashSet<>(PhysicalRegister.phyRegName);
        // todo:
        raw.functions.forEach(func->{
            if(func.isBuiltin) return;
            this.liveAnalysis(func);
            StringBuilder rawString = new StringBuilder();
            rawString.append(func.getName()).append(":\n");
            conflict_check.forEach(tmp->rawString.append('\t').append(tmp.a).append(" <-> ").append(tmp.b).append('\n'));
            System.out.println(rawString.toString());
        });
    }

    /*
        Analyse function's liveness and build conflict graph.
        Information is stored in global class variable.
     */
    private void liveAnalysis(ASMFunction func){
        this.conflict_check = new HashSet<>();
        this.conflict_graph = new HashMap<>();
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
                if(inst instanceof MoveInstr){
                    inst_out.removeAll(inst.use);
                }
                inst.def.forEach(tmpu-> inst_out.forEach(tmpv->addEdge(tmpu,tmpv)));
                inst_out.removeAll(inst.def);
                inst_out.addAll(inst.use);
            }
            assert inst_out.size() == bb_live_in.get(bb).size();
        }
    }

    private void addEdge(String u, String v){
        if(conflict_check.contains(new Pair<>(u,v)) || u.equals(v)) return;
        conflict_check.add(new Pair<>(u,v));
        conflict_check.add(new Pair<>(v,u));
        if(!physical_register.contains(u)) {
            ArrayList<String> u_list = conflict_graph.get(u);
            if (u_list == null) u_list = new ArrayList<>();
            u_list.add(v);
        }
        if(!physical_register.contains(v)) {
            ArrayList<String> v_list = conflict_graph.get(v);
            if(v_list == null) v_list = new ArrayList<>();
            v_list.add(u);
        }
    }

}