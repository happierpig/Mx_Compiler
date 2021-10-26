package AST;

import Utils.Position;

public class BoolConstantExprNode extends ExprNode{
    public boolean value;
    public BoolConstantExprNode(boolean _flag,Position _pos){
        super(_pos);
        this.value = _flag;
        this.exprType = new ClassTypeNode("bool",_pos);
    }
    public boolean getValue(){
        return this.value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}