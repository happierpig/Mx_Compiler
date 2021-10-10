package AST;

import Utils.Position;
import java.util.ArrayList;

public class BlockStmtNode extends StmtNode{
    private ArrayList<StmtNode> stmtList;
    public BlockStmtNode(ArrayList<StmtNode> _list,Position _pos){
        super(_pos);
        this.stmtList = _list;
    }
}