package FrontEnd;

import AST.*;
import Utils.GlobalScope;
import Utils.Position;
import Utils.SemanticError;

public class PreProcessor implements ASTVisitor{
    public GlobalScope gScope;

    public PreProcessor(GlobalScope _gScope){
        this.gScope = _gScope;
    }

    @Override
    public void visit(RootNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
        if(!this.gScope.contains_Function("main") || !this.gScope.fetch_Function("main").funcType.isEqual(new ClassTypeNode("int",new Position(-1,-1)))) throw new SemanticError("Where is main function ?",node.getPos());
    }

    @Override
    public void visit(ClassDefNode node) {
        if(this.gScope.contains_Class(node.classIdentifier) || this.gScope.contains_Function(node.classIdentifier)) throw new SemanticError("Duplicate Declaration " + node.classIdentifier,node.getPos());
        else {
            GlobalScope forClass = new GlobalScope();
            for(VarDefStmtNode tmp : node.memberVariable){
                tmp.elements.forEach(var->{
                    if(forClass.contains_Variable(var.identifier)) throw new SemanticError("Duplicate Declaration Variable " + var.identifier + " in Class " + node.classIdentifier,tmp.getPos());
                    else forClass.define_Variable(var.identifier,var.varType);
                });
            }
            node.memberFunction.forEach(func->{
                if(func.funcType == null && !func.identifier.equals(node.classIdentifier)) throw new SemanticError("Invalid Constructor in Class " + node.classIdentifier,func.getPos());
                else if(forClass.contains_Function(func.identifier)) throw new SemanticError("Duplicate Declaration Function " + func.identifier + "in Class " + node.classIdentifier,func.getPos());
                else forClass.define_Function(func.identifier,func);
            });
            this.gScope.define_Class(node.classIdentifier,forClass);
        }
    }

    @Override
    public void visit(FuncDefNode node) {
        if(this.gScope.contains_Function(node.identifier) || this.gScope.contains_Class(node.identifier)) throw new SemanticError("Duplicate Declaration Function " + node.identifier,node.getPos());
        else if(node.funcType == null) throw new SemanticError("Constructor Declaration out of Class with " + node.identifier,node.getPos());
        else this.gScope.define_Function(node.identifier,node);
    }

    @Override public void visit(ArrayAccessExprNode node) {}
    @Override public void visit(ArrayTypeNode node) {}
    @Override public void visit(BinaryExprNode node) {}
    @Override public void visit(BlockStmtNode node) {}
    @Override public void visit(BoolConstantExprNode node) {}
    @Override public void visit(BreakStmtNode Node) {}
    @Override public void visit(ClassTypeNode node) {}
    @Override public void visit(ContinueStmtNode node) {}
    @Override public void visit(ExprStmtNode node) {}
    @Override public void visit(ForStmtNode node) {}
    @Override public void visit(FuncCallExprNode node) {}
    @Override public void visit(IdentifierExprNode node) {}
    @Override public void visit(IfStmtNode node) {}
    @Override public void visit(IntConstantExprNode node) {}
    @Override public void visit(MonoExprNode node) {}
    @Override public void visit(NewExprNode node) {}
    @Override public void visit(NullConstantExprNode node) {}
    @Override public void visit(ObjectMemberExprNode node) {}
    @Override public void visit(ReturnStmtNode node) {}
    @Override public void visit(StringConstantExprNode node) {}
    @Override public void visit(ThisExprNode node) {}
    @Override public void visit(VarDefStmtNode node) {}
    @Override public void visit(VarDefNode node) {}
    @Override public void visit(VoidTypeNode node) {}
    @Override public void visit(WhileStmtNode node) {}
}