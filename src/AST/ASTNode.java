package AST;

import Utils.Position;

public abstract class ASTNode{
    private Position pos;
    public ASTNode(Position _pos){
        this.pos = _pos;
    }
    public Position getPos(){
        return pos;
    }
}