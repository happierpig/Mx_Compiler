package AST;

import Utils.Position;

import java.util.ArrayList;

public class ClassDefNode extends ASTNode{
    private String classIdentifier;
    private ArrayList<VarDefStmtNode> memberVariable;
    private ArrayList<FuncDefNode> memberFunction;
    // Constructor check arranged to be next
    public ClassDefNode(Position _pos){
        super(_pos);
    }
}