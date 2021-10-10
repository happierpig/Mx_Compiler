package AST;

import Utils.Position;

public class ReturnStmtNode extends StmtNode{
    private ExprNode returnVal;
    public ReturnStmtNode(ExprNode _returnVal, Position _pos){
        super(_pos);
        this.returnVal = _returnVal;
    }
}