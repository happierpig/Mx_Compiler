package AST;

import Utils.Position;

public class ObjectMemberExprNode extends ExprNode{
    private ExprNode base;
    private String member;
    // todo: How to Parse Class Object Function Call
    public ObjectMemberExprNode(ExprNode _base,String _member,Position _pos){
        super(_pos);
        this.base = _base;
        this.member = _member;
    }

}