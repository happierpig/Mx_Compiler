package AST;

import Utils.Position;

public class ObjectMemberExprNode extends ExprNode{
    public ExprNode base;
    public String member;
    public boolean forFunc;
    public FuncDefNode funcInfo;
    public ObjectMemberExprNode(ExprNode _base,String _member,Position _pos){
        super(_pos);
        this.base = _base;
        this.member = _member;
        forFunc = false;
        funcInfo = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}