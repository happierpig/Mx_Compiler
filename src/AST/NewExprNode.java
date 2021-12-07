package AST;

import Utils.Position;
import java.util.ArrayList;

public class NewExprNode extends ExprNode{
    public TypeNode newType;
    public int DimSize; // array dimension； Zero means new class object;
    public ArrayList<ExprNode> SizeList;
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

    public boolean isArray(){
        return this.DimSize > 0;
    }
}