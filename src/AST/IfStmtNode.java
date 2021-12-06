package AST;

import Utils.Position;

public class IfStmtNode extends StmtNode{
    public ExprNode condition;
    public StmtNode thenCode,elseCode;
    public IfStmtNode(ExprNode _condition,StmtNode _then,StmtNode _else,Position _pos){
        super(_pos);
        this.condition = _condition;
        this.thenCode = _then;
        this.elseCode = _else; // maybe null
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}