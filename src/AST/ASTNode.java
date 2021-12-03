package AST;

import IR.Operand.IROperand;
import Utils.Position;

public abstract class ASTNode{
    private Position pos;
    public IROperand operand;
    public ASTNode(Position _pos){
        this.pos = _pos;
        this.operand = null;
    }
    public Position getPos(){
        return pos;
    }

    abstract public void accept(ASTVisitor visitor);
}