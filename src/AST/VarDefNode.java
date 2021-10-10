package AST;

import Utils.Position;

public class VarDefNode extends ASTNode{
    private TypeNode varType;
    private String identifier;
    private ExprNode initValue;

    public VarDefNode(TypeNode _type,String _id,ExprNode _initVal,Position _pos){
        super(_pos);
        this.identifier = _id;
        this.initValue = _initVal;
        this.varType = _type;
    }
}