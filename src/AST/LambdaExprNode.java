package AST;

import Utils.Position;
import java.util.ArrayList;

public class LambdaExprNode extends ExprNode{
    public TypeNode ReturnType;
    public ArrayList<VarDefNode> parameterList;
    public BlockStmtNode funcBody;
    public ArrayList<ExprNode> AryList;

    public LambdaExprNode(ArrayList<VarDefNode> _para, ArrayList<ExprNode> _ary, BlockStmtNode _body, Position _pos){
        super(_pos);
        this.ReturnType = null;
        this.parameterList = _para;
        this.AryList = _ary;
        this.funcBody = _body;
    }



    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}