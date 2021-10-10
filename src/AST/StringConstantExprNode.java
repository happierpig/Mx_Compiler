package AST;

import Utils.Position;

public class StringConstantExprNode extends ExprNode{
    private String value;
    public StringConstantExprNode(String _value,Position _pos){
        super(_pos);
        this.value = _value;
    }
    public String getValue(){
        return this.value;
    }
}