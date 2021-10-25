package AST;

import Utils.Position;

import java.util.ArrayList;

public class ClassDefNode extends ASTNode{
    public String classIdentifier;
    public ArrayList<VarDefStmtNode> memberVariable;
    public ArrayList<FuncDefNode> memberFunction;
    // Constructor check arranged to be next
    public ClassDefNode(String _id,ArrayList<VarDefStmtNode> _var,ArrayList<FuncDefNode> _func,Position _pos){
        super(_pos);
        this.classIdentifier = _id;
        this.memberVariable = _var;
        this.memberFunction = _func;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}