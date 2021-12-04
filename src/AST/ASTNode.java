package AST;

import IR.BaseClass.Value;
import Utils.Position;

public abstract class ASTNode{
    private Position pos;
    public Value operand;

    public ASTNode(Position _pos){
        this.pos = _pos;
        this.operand = null;
    }

    public Position getPos(){
        return pos;
    }

    abstract public void accept(ASTVisitor visitor);
}