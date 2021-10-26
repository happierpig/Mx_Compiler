package AST;

import Parser.MxBaseVisitor;
import Parser.MxParser;
import Utils.Position;
import Utils.SyntaxError;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.swing.text.html.parser.Parser;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends MxBaseVisitor<ASTNode>{

    // type node

    @Override
    public ASTNode visitBaseType(MxParser.BaseTypeContext ctx) {
        return new ClassTypeNode(ctx.getText(),new Position(ctx));
    }

    @Override
    public ASTNode visitBaseVariableType(MxParser.BaseVariableTypeContext ctx) {
        return visit(ctx.baseType());
    }

    @Override
    public ASTNode visitArray_Type(MxParser.Array_TypeContext ctx) {
        return new ArrayTypeNode((TypeNode) visit(ctx.variableType()),new Position(ctx));
    }

    @Override
    public ASTNode visitFunctionType(MxParser.FunctionTypeContext ctx) {
        if(ctx.VOID() == null){ //return variableType
            return visit(ctx.variableType());
        }else{ // void function
            return new VoidTypeNode(new Position(ctx));
        }
    }

    @Override
    public ASTNode visitConstantValue(MxParser.ConstantValueContext ctx) {
        if(ctx.BOOL_CONSTANT() != null){
            boolean value = ctx.getText().equals("true");
            return new BoolConstantExprNode(value,new Position(ctx));
        }else if(ctx.INTERGER_CONSTANT() != null){
            int value = Integer.parseInt(ctx.getText());
            return new IntConstantExprNode(value,new Position(ctx));
        }else if(ctx.STRING_CONSTANT() != null){
            return new StringConstantExprNode(ctx.getText(),new Position(ctx));
        }else if(ctx.NULL_CONSTANT() != null){
            return new NullConstantExprNode(new Position(ctx));
        }
        throw new RuntimeException("[debug] 1");
    }

    @Override
    public ASTNode visitAllocBaseType(MxParser.AllocBaseTypeContext ctx) {
        return new NewExprNode((TypeNode) visit(ctx.baseType()),0,null,new Position(ctx));
    }

    @Override
    public ASTNode visitAllocArrayType(MxParser.AllocArrayTypeContext ctx) {
        ArrayList<ExprNode> arraySize = new ArrayList<ExprNode>();
        ctx.expression().forEach(tmp->arraySize.add((ExprNode) visit(tmp)));
        int dimensions = (ctx.getChildCount() - arraySize.size() - 1) / 2;
        return new NewExprNode((TypeNode) visit(ctx.baseType()),dimensions,arraySize,new Position(ctx));
    }

    @Override
    public ASTNode visitAllocErrorType(MxParser.AllocErrorTypeContext ctx) {
        throw new SyntaxError("Wrong Syntax for array create",new Position(ctx));
    }

    //Expression node

    @Override
    public ASTNode visitIdentifier(MxParser.IdentifierContext ctx) {
        return new IdentifierExprNode(ctx.getText(),new Position(ctx));
    }

    @Override
    public ASTNode visitConstant(MxParser.ConstantContext ctx) {
        return visit(ctx.constantValue());
    }

    @Override
    public ASTNode visitObjPortion(MxParser.ObjPortionContext ctx) {
        return new ObjectMemberExprNode((ExprNode) visit(ctx.expression()),ctx.IDENTIFIER().getText(),new Position(ctx));
    }

    @Override
    public ASTNode visitAllocExp(MxParser.AllocExpContext ctx) {
        return visit(ctx.allocFormat());
    }

    @Override
    public ASTNode visitFunctionCall(MxParser.FunctionCallContext ctx) {
        ArrayList<ExprNode> _List = new ArrayList<ExprNode>();
        if(ctx.parameterListForCall() != null){
            ctx.parameterListForCall().expression().forEach(tmp->_List.add((ExprNode) visit(tmp)));
            return new FuncCallExprNode((ExprNode) visit(ctx.expression()),_List,new Position(ctx));
        }else{
            return new FuncCallExprNode((ExprNode) visit(ctx.expression()),null,new Position(ctx));
        }
    }

    @Override
    public ASTNode visitCompoundExp(MxParser.CompoundExpContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitArrayAccess(MxParser.ArrayAccessContext ctx) {
        return new ArrayAccessExprNode((ExprNode) visit(ctx.array),(ExprNode) visit(ctx.index),new Position(ctx));
    }

    @Override
    public ASTNode visitAftermonocularOp(MxParser.AftermonocularOpContext ctx) {
        String op = ctx.op.getText();
        if(op.equals("++")){
            return new MonoExprNode(MonoExprNode.Op.AFTINC,(ExprNode) visit(ctx.operand),new Position(ctx));
        }else return new MonoExprNode(MonoExprNode.Op.AFTDEC,(ExprNode) visit(ctx.operand),new Position(ctx));
    }

    @Override
    public ASTNode visitMonocularOp(MxParser.MonocularOpContext ctx) {
        String op = ctx.op.getText();
        ExprNode tmpNode = (ExprNode) visit(ctx.operand);
        MonoExprNode.Op tmpOp = switch (op) {
            case "++" -> MonoExprNode.Op.PREINC;
            case "--" -> MonoExprNode.Op.PREDEC;
            case "!" -> MonoExprNode.Op.LNOT;
            case "~" -> MonoExprNode.Op.BITNOT;
            case "-" -> MonoExprNode.Op.NEG;
            case "+" -> MonoExprNode.Op.POS;
            default -> throw new RuntimeException("[debug] 3");
        };
        return new MonoExprNode(tmpOp,tmpNode,new Position(ctx));
    }

    @Override
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        String op = ctx.op.getText();
        ExprNode ls = (ExprNode) visit(ctx.operand1);
        ExprNode rs = (ExprNode) visit(ctx.operand2);
        BinaryExprNode.Op tmpOp = switch (op) {
            case ">" -> BinaryExprNode.Op.GT;
            case "<" -> BinaryExprNode.Op.LT;
            case ">=" -> BinaryExprNode.Op.GE;
            case "<=" -> BinaryExprNode.Op.LE;
            case "==" -> BinaryExprNode.Op.EQ;
            case "!=" -> BinaryExprNode.Op.NE;
            case ">>" -> BinaryExprNode.Op.SHR;
            case "<<" -> BinaryExprNode.Op.SHL;
            case "*" -> BinaryExprNode.Op.MUL;
            case "/" -> BinaryExprNode.Op.DIV;
            case "%" -> BinaryExprNode.Op.MOD;
            case "+" -> BinaryExprNode.Op.ADD;
            case "-" -> BinaryExprNode.Op.SUB;
            case "&" -> BinaryExprNode.Op.AND;
            case "^" -> BinaryExprNode.Op.XOR;
            case "|" -> BinaryExprNode.Op.OR;
            case "&&" -> BinaryExprNode.Op.LAND;
            case "||" -> BinaryExprNode.Op.LOR;
            case "=" -> BinaryExprNode.Op.ASSIGN;
            default -> throw new RuntimeException("[debug] 2");
        };
        return new BinaryExprNode(tmpOp,new Position(ctx),ls,rs);
    }

    //statement node

    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        ArrayList<StmtNode> _List = new ArrayList<>();
        if(ctx.statement() != null){
            ctx.statement().forEach(tmp->_List.add((StmtNode) visit(tmp)));
            return new BlockStmtNode(_List,new Position(ctx));
        }else return new BlockStmtNode(null,new Position(ctx));
    }

    @Override
    public ASTNode visitCodeBlock(MxParser.CodeBlockContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        ExprNode cdt = (ExprNode) visit(ctx.condition);
        StmtNode thenStmt = (StmtNode) visit(ctx.thenStatement);
        StmtNode elseStmt = (ctx.elseStatement == null) ? null : (StmtNode) visit(ctx.elseStatement);
        return new IfStmtNode(cdt,thenStmt,elseStmt,new Position(ctx));
    }

    @Override
    public ASTNode visitIfStatement(MxParser.IfStatementContext ctx) {
        return visit(ctx.ifStmt());
    }

    @Override
    public ASTNode visitWhileStatement(MxParser.WhileStatementContext ctx) {
        ExprNode cdt = (ExprNode) visit(ctx.condition);
        StmtNode body = (StmtNode) visit(ctx.loopBody);
        return new WhileStmtNode(cdt,body,new Position(ctx));
    }

    @Override
    public ASTNode visitForStatement(MxParser.ForStatementContext ctx) {
        StmtNode init = null;
        if(ctx.initDecl != null) init = (StmtNode) visit(ctx.initDecl);
        else if(ctx.initExpr != null) init = new ExprStmtNode((ExprNode) visit(ctx.initExpr),new Position(ctx.initExpr));
        StmtNode loopBody = (StmtNode) visit(ctx.loopBody);
        ExprNode cdt = (ctx.condition == null) ? null : (ExprNode) visit(ctx.condition);
        ExprNode incr = (ctx.incrExp == null) ? null : (ExprNode) visit(ctx.incrExp);
        return new ForStmtNode(init,cdt,incr,loopBody,new Position(ctx));
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ExprNode returnVal = (ctx.expression() == null) ? null : (ExprNode) visit(ctx.expression());
        return new ReturnStmtNode(returnVal,new Position(ctx));
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return new BreakStmtNode(new Position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return new ContinueStmtNode(new Position(ctx));
    }

    @Override
    public ASTNode visitJumpStatment(MxParser.JumpStatmentContext ctx) {
        return visit(ctx.jumpStmt());
    }

    @Override
    public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
        return new ExprStmtNode((ExprNode) visit(ctx.expression()),new Position(ctx));
    }

    @Override
    public ASTNode visitVariableDeclStmt(MxParser.VariableDeclStmtContext ctx) {
        return visit(ctx.variableDecl());
    }

    //  declaration

    @Override
    public ASTNode visitVariableDecl(MxParser.VariableDeclContext ctx) {
        TypeNode varType = (TypeNode) visit(ctx.variableType());
        ArrayList<VarDefNode> _List = new ArrayList<VarDefNode>();
        for(MxParser.BaseVariableDeclContext element : ctx.baseVariableDecl()){
            String id = element.IDENTIFIER().getText();
            ExprNode initVal = (element.expression() == null) ? null : (ExprNode) visit(element.expression());
            _List.add(new VarDefNode(varType,id,initVal,new Position(element)));
        }
        return new VarDefStmtNode(_List,new Position(ctx));
    }

    @Override
    public ASTNode visitFunctionDecl(MxParser.FunctionDeclContext ctx) {
        TypeNode funcType = (ctx.functionType() == null) ? null : (TypeNode) visit(ctx.functionType());
        String funcName = ctx.IDENTIFIER().getText();
        BlockStmtNode funcBody = (BlockStmtNode) visit(ctx.block());
        ArrayList<VarDefNode> aryList = new ArrayList<VarDefNode>();
        if(ctx.parameterList() == null) return new FuncDefNode(funcType,funcName,null,funcBody,new Position(ctx));
        else{
            List<MxParser.VariableTypeContext> vtl = ctx.parameterList().variableType();
            List<TerminalNode> vil = ctx.parameterList().IDENTIFIER();
            for(int i = 0;i < vtl.size();i++){
                aryList.add(new VarDefNode((TypeNode) visit(vtl.get(i)),vil.get(i).getText(),null,new Position(vtl.get(i))));
            }
            return new FuncDefNode(funcType,funcName,aryList,funcBody,new Position(ctx));
        }
    }

    @Override
    public ASTNode visitClassDecl(MxParser.ClassDeclContext ctx) {
        String classId = ctx.classID.getText();
        ArrayList<VarDefStmtNode> memberList = new ArrayList<VarDefStmtNode>();
        ArrayList<FuncDefNode> funcList = new ArrayList<FuncDefNode>();
        if(ctx.variableDecl() != null) ctx.variableDecl().forEach(tmp->memberList.add((VarDefStmtNode) visit(tmp)));
        if(ctx.functionDecl() != null) ctx.functionDecl().forEach(tmp->funcList.add((FuncDefNode) visit(tmp)));
        return new ClassDefNode(classId,ctx.variableDecl() == null ? null : memberList,ctx.functionDecl() == null ? null : funcList,new Position(ctx));
    }

    // program body


    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        ArrayList<ASTNode> _ele = new ArrayList<>();
        for(MxParser.SubProgramContext elements : ctx.subProgram()){
            if(elements.functionDecl() != null) _ele.add(visit(elements.functionDecl()));
            if(elements.classDecl() != null) _ele.add(visit(elements.classDecl()));
            if(elements.variableDecl() != null) _ele.add(visit(elements.variableDecl()));
        }
        return new RootNode(_ele,new Position(ctx));
    }
}