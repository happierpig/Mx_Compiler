package AST;

import Utils.Position;

import java.util.ArrayList;

public class RootNode extends ASTNode{
    private ArrayList<FuncDefNode> functions;
    private ArrayList<ClassDefNode> classes;
    private ArrayList<VarDefStmtNode> global_variables;
    public RootNode(ArrayList<FuncDefNode> _func,ArrayList<ClassDefNode> _class,ArrayList<VarDefStmtNode> _var,Position _pos){
        super(_pos);
        this.functions = _func;
        this.classes = _class;
        this.global_variables = _var;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}