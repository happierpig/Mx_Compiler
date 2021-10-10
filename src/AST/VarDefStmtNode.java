package AST;

import Utils.Position;
import java.util.ArrayList;


public class VarDefStmtNode extends StmtNode{
    private ArrayList<VarDefNode> elements;

    public VarDefStmtNode(TypeNode _type,ArrayList<VarDefNode> _list,Position _pos){
        super(_pos);
        this.elements = _list;
    }

}