package AST;


import Utils.Position;

public class BreakStmtNode extends StmtNode{

    public BreakStmtNode(Position _pos){
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}