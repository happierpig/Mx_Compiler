package AST;

import Utils.Position;
import java.util.ArrayList;

public class FuncDefNode extends ASTNode{
    public TypeNode funcType; // void is distinguished from constructor,for void is voidTypeNode and constructor is null
    public String identifier;
    public ArrayList<VarDefNode> parameterList;
    public BlockStmtNode funcBody;

    public FuncDefNode(TypeNode _funcType,String _name,ArrayList<VarDefNode> _list,BlockStmtNode _body,Position _pos){
        super(_pos);
        this.funcType = _funcType;
        this.identifier = _name;
        this.parameterList = _list;
        this.funcBody = _body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}