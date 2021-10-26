package AST;

import Utils.Position;

public class BinaryExprNode extends ExprNode{
    public enum Op{ADD,SUB,MUL,DIV,MOD,SHL,SHR,GT,LT,GE,LE,EQ,NE,AND,XOR,OR,LAND,LOR,ASSIGN}
    public Op operator;
    public ExprNode LOperand,ROperand;
    public BinaryExprNode(Op op,Position _pos,ExprNode _LOperand,ExprNode _ROperand){
        super(_pos);
        this.LOperand = _LOperand;
        this.ROperand = _ROperand;
        this.operator = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}