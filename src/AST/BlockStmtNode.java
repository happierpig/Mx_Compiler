package AST;

import Utils.Position;
import java.util.ArrayList;

public class BlockStmtNode extends StmtNode{
    public ArrayList<StmtNode> stmtList;
    public BlockStmtNode(ArrayList<StmtNode> _list,Position _pos){
        super(_pos);
        this.stmtList = _list;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}