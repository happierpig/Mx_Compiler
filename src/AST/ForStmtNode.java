package AST;

import Utils.Position;

public class ForStmtNode extends StmtNode{
    private StmtNode init,loopBody;
    private ExprNode condition,iteration;
    public ForStmtNode(StmtNode _init, ExprNode _condition, ExprNode _iteration, StmtNode _body, Position _pos){
        super(_pos);
        this.init = _init;
        this.condition = _condition;
        this.iteration = _iteration;
        this.loopBody = _body;
    }
}