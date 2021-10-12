package AST;


import Utils.Position;

public class ContinueStmtNode extends StmtNode{

    public ContinueStmtNode(Position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}