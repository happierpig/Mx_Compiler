package AST;

import Utils.Position;

public class WhileStmtNode extends StmtNode{
    private ExprNode condition;
    private StmtNode loopBody;

    public WhileStmtNode(ExprNode _condition,StmtNode _body,Position _pos){
        super(_pos);
        this.condition = _condition;
        this.loopBody = _body;
    }
}