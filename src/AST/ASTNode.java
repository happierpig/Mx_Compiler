package AST;

import MiddleEnd.BaseClass.Value;
import Utils.Position;

public abstract class ASTNode{
    private Position pos;
    public Value IRoperand;

    public ASTNode(Position _pos){
        this.pos = _pos;
        this.IRoperand = null;
    }

    public Position getPos(){
        return pos;
    }

    abstract public void accept(ASTVisitor visitor);
}