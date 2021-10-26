package AST;

import Utils.Position;

public class IntConstantExprNode extends ExprNode{
    public int value;

    public IntConstantExprNode(int _v,Position _pos){
        super(_pos);
        this.value = _v;
        this.exprType = new ClassTypeNode("int",_pos);
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}