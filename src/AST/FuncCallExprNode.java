package AST;

import Utils.Position;

import java.util.ArrayList;

public class FuncCallExprNode extends ExprNode{
    public ExprNode Func;
    public ArrayList<ExprNode> AryList;

    public FuncCallExprNode(ExprNode _Func,ArrayList<ExprNode> _List,Position _pos){
        super(_pos);
        this.Func = _Func;
        this.AryList = _List;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}