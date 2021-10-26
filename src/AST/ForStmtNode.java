package AST;

import Utils.Position;

public class ForStmtNode extends StmtNode{
    public StmtNode init,loopBody;
    // todo : Use StmtNode to denote init reasonable?
    public ExprNode condition,iteration;
    public ForStmtNode(StmtNode _init, ExprNode _condition, ExprNode _iteration, StmtNode _body, Position _pos){
        super(_pos);
        this.init = _init;
        this.condition = _condition;
        this.iteration = _iteration;
        this.loopBody = _body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}