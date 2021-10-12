package AST;

import Parser.MxBaseVisitor;
import Parser.MxParser;
import Utils.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        MonoExprNode.Op tmpOp = null;
        switch(op){
            case "++":
                tmpOp = MonoExprNode.Op.PREINC;
                break;
            case "--":
                tmpOp = MonoExprNode.Op.PREDEC;
                break;
            case "!":
                tmpOp = MonoExprNode.Op.LNOT;
                break;
            case "~":
                tmpOp = MonoExprNode.Op.BITNOT;
                break;
            case "-":
                tmpOp = MonoExprNode.Op.NEG;
                break;
            case "+":
                tmpOp = MonoExprNode.Op.POS;
                break;
        }
        return new MonoExprNode(tmpOp,tmpNode,new Position(ctx));
    }

    @Override
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        String op = ctx.op.getText();
        ExprNode ls = (ExprNode) visit(ctx.operand1);
        ExprNode rs = (ExprNode) visit(ctx.operand2);
        BinaryExprNode.Op tmpOp = null;
        switch(op){
            case ">":
                tmpOp = BinaryExprNode.Op.GT;
                break;
            case "<":
                tmpOp = BinaryExprNode.Op.LT;
                break;
            case ">=":
                tmpOp = BinaryExprNode.Op.GE;
                break;
            case "<=":
                tmpOp = BinaryExprNode.Op.LE;
                break;
            case "==":
                tmpOp = BinaryExprNode.Op.EQ;
                break;
            case "!=":
                tmpOp = BinaryExprNode.Op.NE;
                break;
            case ">>":
                tmpOp = BinaryExprNode.Op.SHR;
                break;
            case "<<":
                tmpOp = BinaryExprNode.Op.SHL;
                break;
            case "*":
                tmpOp = BinaryExprNode.Op.MUL;
                break;
            case "/":
                tmpOp = BinaryExprNode.Op.DIV;
                break;
            case "%":
                tmpOp = BinaryExprNode.Op.MOD;
                break;
            case "+":
                tmpOp = BinaryExprNode.Op.ADD;
                break;
            case "-":
                tmpOp = BinaryExprNode.Op.SUB;
                break;
                case ""

        }
        return new BinaryExprNode(tmpOp,new Position(ctx),ls,rs);
    }
}