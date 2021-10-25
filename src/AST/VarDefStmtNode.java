package AST;

import Utils.Position;
import java.util.ArrayList;


public class VarDefStmtNode extends StmtNode{
    public ArrayList<VarDefNode> elements;

    public VarDefStmtNode(ArrayList<VarDefNode> _list,Position _pos){
        super(_pos);
        this.elements = _list;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}