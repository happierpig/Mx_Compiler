package AST;

import Utils.Position;
import java.util.ArrayList;

public class FuncDefNode extends ASTNode{
    private TypeNode funcType; // void is distinguished from constructor,for void is voidTypeNode and constructor is null
    private String identifier;
    private ArrayList<VarDefNode> parameterList;
    private BlockStmtNode funcBody;

    public FuncDefNode(TypeNode _funcType,String _name,ArrayList<VarDefNode> _list,BlockStmtNode _body,Position _pos){
        super(_pos);
        this.funcType = _funcType;
        this.identifier = _name;
        this.parameterList = _list;
        this.funcBody = _body;
    }
}