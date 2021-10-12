package AST;

import Utils.Position;
import java.util.ArrayList;

public class NewExprNode extends ExprNode{
    private TypeNode newType;
    private int DimSize; // array dimension
    private ArrayList<ExprNode> SizeList;
    public NewExprNode(TypeNode _type,int _size,ArrayList<ExprNode> _List,Position _pos){
        super(_pos);
        this.newType = _type;
        this.DimSize = _size;
        this.SizeList = _List;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}