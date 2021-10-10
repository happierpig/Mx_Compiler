package AST;

import Utils.Position;

public abstract class StmtNode extends ASTNode{
    public StmtNode(Position _pos){
        super(_pos);
    }
}