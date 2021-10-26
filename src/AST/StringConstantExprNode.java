package AST;

import Utils.Position;

public class StringConstantExprNode extends ExprNode{
    public String value;
    public StringConstantExprNode(String _value,Position _pos){
        super(_pos);
        this.value = _value;
        this.exprType = new ClassTypeNode("string",_pos);
    }
    public String getValue(){
        return this.value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}