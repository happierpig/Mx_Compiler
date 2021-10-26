package AST;

import Utils.Position;

public class MonoExprNode extends ExprNode{

    public enum Op{LNOT,BITNOT,PREINC,PREDEC,AFTINC,AFTDEC,POS,NEG}
    //              !    ~                                  +   -

    public Op operator;
    public ExprNode operand;
    public MonoExprNode(Op _op,ExprNode _operand,Position pos){
        super(pos);
        this.operator = _op;
        this.operand = _operand;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}