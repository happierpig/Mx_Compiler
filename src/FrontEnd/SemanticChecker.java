package FrontEnd;

import AST.*;
import Utils.*;

import java.util.ArrayList;
import java.util.Stack;


public class SemanticChecker implements ASTVisitor{
    public Scope cScope;
    public GlobalScope gScope;
    private final TypeNode TypeNull,TypeVoid,TypeInt,TypeBool,TypeString;
    private String nowClass;
    public FuncDefNode FuncSize;
    public Stack<ASTNode> FuncStation;
    public int loops;
    public SemanticChecker(GlobalScope _gScope){
        this.gScope = _gScope;
        this.cScope = this.gScope;
        Position defaultPosition = new Position(-1, -1);
        TypeNull = new ClassTypeNode("null", defaultPosition);
        TypeVoid = new VoidTypeNode(defaultPosition);
        TypeInt = new ClassTypeNode("int", defaultPosition);
        TypeBool = new ClassTypeNode("bool", defaultPosition);
        TypeString = new ClassTypeNode("string", defaultPosition);
        nowClass = null;
        FuncStation = new Stack<>();
        loops = 0;
        FuncSize = new FuncDefNode(new ClassTypeNode("int", defaultPosition),"size",null,null, defaultPosition);
    }

    @Override
    public void visit(RootNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(VarDefNode node) {
        if(cScope.contains_Variable(node.identifier) || gScope.contains_Class(node.identifier)) throw new SemanticError("Duplicate Variable Declaration " + node.identifier,node.getPos());
        else if(!gScope.contains_Class(node.varType.typeId)) throw new SemanticError("Undefined Class " + node.varType.typeId,node.getPos());
        else {
            if(node.initValue != null){
                node.initValue.accept(this);
                if(!node.initValue.exprType.isEqual(TypeNull) && !node.initValue.exprType.isEqual(node.varType)) throw new SemanticError("Mismatched Class Type",node.getPos());
            }
            cScope.define_Variable(node.identifier,node.varType);
        }
    }

    @Override
    public void visit(VarDefStmtNode node) {
        node.elements.forEach(tmp->tmp.accept(this));
    }

    @Override
    public void visit(ClassDefNode node) {
        cScope = gScope.Class_Table.get(node.classIdentifier);
        nowClass = node.classIdentifier;
        for(VarDefStmtNode _list : node.memberVariable){
            for(VarDefNode _tmp : _list.elements){
                if(!gScope.contains_Class(_tmp.varType.typeId)) throw new SemanticError("Undefined Class " + _tmp.varType.typeId,_tmp.getPos());
                if(_tmp.initValue != null){
                    _tmp.initValue.accept(this);
                    if(!_tmp.initValue.exprType.isEqual(TypeNull) && !_tmp.initValue.exprType.isEqual(_tmp.varType)) throw new SemanticError("Mismatched Class Type",_tmp.getPos());
                }
            }
        }
        node.memberFunction.forEach(tmp->tmp.accept(this));
        nowClass = null;
        cScope = cScope.parent;
    }

    @Override
    public void visit(FuncDefNode node) {
        cScope = new Scope(cScope);
        FuncStation.push(node);
        if(node.funcType != null && !node.funcType.isEqual(TypeVoid) && !gScope.contains_Class(node.funcType.typeId)) throw new SemanticError("Undefined Function Return Type" + node.funcType.typeId,node.getPos());
        if(node.parameterList != null) node.parameterList.forEach(tmp->tmp.accept(this));
        if(node.funcBody.stmtList != null) node.funcBody.stmtList.forEach(tmp->tmp.accept(this));
        if(node.funcType != null && !node.funcType.isEqual(TypeVoid) && !node.identifier.equals("main") && !node.hasReturn) throw new SemanticError("Lack of Return Statement in " + node.identifier,node.getPos());
        if(node.identifier.equals("main") && !node.hasReturn){
            if(node.funcBody.stmtList == null) node.funcBody.stmtList = new ArrayList<>();
            node.funcBody.stmtList.add(new ReturnStmtNode(new IntConstantExprNode(0,new Position(-1,-1)),new Position(-1,-1)));
        }
        FuncStation.pop();
        cScope = cScope.parent;
    }

    @Override
    public void visit(LambdaExprNode node) {
        cScope = new Scope(cScope);
        FuncStation.push(node);
        if(node.parameterList != null) node.parameterList.forEach(tmp->tmp.accept(this));
        if(node.AryList != null) node.AryList.forEach(tmp->tmp.accept(this));
        if(node.parameterList == null || node.AryList == null){
            if(!(node.parameterList == null && node.AryList == null)) throw new SemanticError("Wrong parameter in Lambda ",node.getPos());
        }else{
            if(node.parameterList.size() != node.AryList.size()) throw new SemanticError("Wrong parameter in Lambda ",node.getPos());
            for (int i = 0; i < node.parameterList.size(); i++) {
                if(!node.parameterList.get(i).varType.isEqual(node.AryList.get(i).exprType)) throw new SemanticError("Wrong parameter in Lambda ",node.getPos());
            }
        }
        node.funcBody.stmtList.forEach(tmp->tmp.accept(this));
        if(node.ReturnType == null) throw new SemanticError("Lambda Expression has at least one Return Statement",node.getPos());
        node.exprType = node.ReturnType;;
        node.isAssignable = false;
        FuncStation.pop();
        cScope = cScope.parent;
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if(FuncStation.empty()) throw new SemanticError("Return Statement must be in Function or Lambda",node.getPos());
        if(FuncStation.peek() instanceof FuncDefNode) {
            FuncDefNode nowFunc = (FuncDefNode) FuncStation.peek();
            if (node.returnVal == null) {
                if (nowFunc.funcType != null && !nowFunc.funcType.isEqual(TypeVoid)) throw new SemanticError("Function Return Type Dismatched1 in " + nowFunc.identifier, node.getPos());
            } else {
                node.returnVal.accept(this);
                if (nowFunc.funcType == null || (!nowFunc.funcType.isEqual(node.returnVal.exprType) && !node.returnVal.exprType.isEqual(TypeNull))) throw new SemanticError("Function Return Type Dismatched2 in " + nowFunc.identifier, node.getPos());
            }
            nowFunc.hasReturn = true;
        }else{
            LambdaExprNode nowFunc = (LambdaExprNode) FuncStation.peek();
            if(node.returnVal == null) throw new SemanticError("Lambda Expression need return type",node.getPos());
            node.returnVal.accept(this);
            if(nowFunc.ReturnType == null) nowFunc.ReturnType = node.returnVal.exprType;
            else if(!nowFunc.ReturnType.isEqual(node.returnVal.exprType)) throw new SemanticError("Lambda Expression has only one return type",node.getPos());
        }
    }

    @Override
    public void visit(IdentifierExprNode node) {
        TypeNode idType = cScope.fetch_Variable_Type(node.identifier);
        if(idType == null) throw new SemanticError("Undefined Variable " + node.identifier,node.getPos());
        node.exprType = idType; node.isAssignable = true;
    }

    @Override
    public void visit(NewExprNode node) {
        if(!gScope.contains_Class(node.newType.typeId)) throw new SemanticError("Undefined Class Type",node.getPos());
        if(node.SizeList != null) {
            node.SizeList.forEach(tmp -> {
                tmp.accept(this);
                if (!tmp.exprType.isEqual(TypeInt)) throw new SemanticError("Array size should be int", node.getPos());
            });
        }
        if(node.DimSize > 0) node.exprType = new ArrayTypeNode(node.newType.typeId,node.DimSize,node.getPos());
        else node.exprType = new ClassTypeNode(node.newType.typeId,node.getPos());
        node.isAssignable = false;
    }

    @Override
    public void visit(ObjectMemberExprNode node) {
        node.base.accept(this);
        if(node.base.exprType instanceof ArrayTypeNode){
            if(!node.forFunc) throw new SemanticError("Array has no members",node.getPos());
            if(!node.member.equals("size")) throw new SemanticError("Array has only size() built-in function.",node.getPos());
            node.funcInfo = FuncSize;
        }else{
            if(node.forFunc){
                node.funcInfo = gScope.Class_Table.get(node.base.exprType.typeId).fetch_Function(node.member);
                if(node.funcInfo == null) throw new SemanticError("Class " + node.base.exprType.typeId + "has no function called " + node.member,node.getPos());
            }else{
                node.exprType = gScope.Class_Table.get(node.base.exprType.typeId).Variable_Table.get(node.member);
                if(node.exprType == null) throw new SemanticError("Class " + node.base.exprType.typeId + "has no variable called " + node.member,node.getPos());
                node.isAssignable = true;
            }
        }
    }

    @Override
    public void visit(FuncCallExprNode node) {
        FuncDefNode checkBase;
        if(node.Func instanceof ObjectMemberExprNode){
            ((ObjectMemberExprNode) (node.Func)).forFunc = true;
            node.Func.accept(this);
            checkBase = ((ObjectMemberExprNode) (node.Func)).funcInfo;
        }else{
            String funcName = ((IdentifierExprNode) node.Func).identifier;
            if(nowClass == null) {
                checkBase = gScope.fetch_Function(funcName);
            }else{
                checkBase = gScope.Class_Table.get(nowClass).fetch_Function(funcName);
                if(checkBase == null) checkBase = gScope.fetch_Function(funcName);
            }
            if (checkBase == null) throw new SemanticError("We don't have function named " + funcName, node.getPos());
        }
        if(node.AryList != null) node.AryList.forEach(tmp->tmp.accept(this));
        if(checkBase.parameterList == null || node.AryList == null){
            if(!(checkBase.parameterList == null && node.AryList == null)) throw new SemanticError("Wrong parameter in function call "+ checkBase.identifier,node.getPos());
        }else{
            if(checkBase.parameterList.size() != node.AryList.size()) throw new SemanticError("Wrong parameter in function call "+ checkBase.identifier,node.getPos());
            for (int i = 0; i < checkBase.parameterList.size(); i++) {
                if(!checkBase.parameterList.get(i).varType.isEqual(node.AryList.get(i).exprType) && !node.AryList.get(i).exprType.isEqual(TypeNull)) throw new SemanticError("Wrong parameter in function call "+ checkBase.identifier,node.getPos());
            }
        }
        node.exprType = checkBase.funcType;
        node.isAssignable = false;
    }

    @Override
    public void visit(ArrayAccessExprNode node) {
        node.array.accept(this);
        if(!(node.array.exprType instanceof ArrayTypeNode)) throw new SemanticError("Try to index not Array Type",node.getPos());
        node.index.accept(this);
        if(!node.index.exprType.isEqual(TypeInt)) throw new SemanticError("Array Index is not int",node.getPos());
        if(((ArrayTypeNode) node.array.exprType).dimSize == 1) node.exprType = new ClassTypeNode(node.array.exprType.typeId,node.getPos());
        else node.exprType = new ArrayTypeNode(node.array.exprType.typeId,((ArrayTypeNode) node.array.exprType).dimSize-1,node.getPos());
        node.isAssignable = true;
    }

    @Override
    public void visit(MonoExprNode node) {
        node.operand.accept(this);
        if(node.operator != MonoExprNode.Op.NEG && node.operator != MonoExprNode.Op.POS && node.operator != MonoExprNode.Op.BITNOT && node.operator != MonoExprNode.Op.LNOT  && !node.operand.isAssignable) throw new SemanticError("Right Value can't operate",node.getPos());
        switch(node.operator){
            case PREINC,PREDEC,NEG,POS,AFTINC,AFTDEC,BITNOT->{
                if(!node.operand.exprType.isEqual(TypeInt)) throw new SemanticError("Operand should be int",node.getPos());
            }
            case LNOT -> {
                if(!node.operand.exprType.isEqual(TypeBool)) throw new SemanticError("Operand should be bool",node.getPos());
            }
        }
        node.exprType = node.operand.exprType;
        node.isAssignable = (node.operator == MonoExprNode.Op.PREINC || node.operator == MonoExprNode.Op.PREDEC);
    }

    @Override
    public void visit(BinaryExprNode node) {
        node.LOperand.accept(this); node.ROperand.accept(this);
        if(!node.LOperand.exprType.isEqual(node.ROperand.exprType) && node.operator != BinaryExprNode.Op.ASSIGN && node.operator != BinaryExprNode.Op.EQ && node.operator != BinaryExprNode.Op.NE) throw new SemanticError("Type Dismatched in Binary Operation1",node.getPos());
        TypeNode nodeType = node.LOperand.exprType;
        node.isAssignable = false;
        switch(node.operator){
            case ADD,GT,LT,GE,LE -> {
                if(!nodeType.isEqual(TypeInt) && !nodeType.isEqual(TypeString)) throw new SemanticError("This operator requires specific type.1",node.getPos());
            }
            case SUB,MUL,DIV,MOD,SHL,SHR,AND,XOR,OR -> {
                if(!nodeType.isEqual(TypeInt)) throw new SemanticError("This operator requires specific type.2",node.getPos());
            }
            case LAND,LOR -> {
                if(!nodeType.isEqual(TypeBool)) throw new SemanticError("This operator requires specific type.3",node.getPos());
            }
            case ASSIGN -> {
                if(!node.LOperand.isAssignable) throw new SemanticError("Left value is required",node.getPos());
                if(!nodeType.isEqual(node.ROperand.exprType) && !node.ROperand.exprType.isEqual(TypeNull)) throw new SemanticError("Type Dismatched in Binary Operation2",node.getPos());
                if(node.ROperand.exprType.isEqual(TypeNull)){
                    if(nodeType.isEqual(TypeInt) || nodeType.isEqual(TypeBool) || nodeType.isEqual(TypeString)) throw new SemanticError("Null cannot be assigned to primitive type variable",node.getPos());
                }
                node.isAssignable = true;
            }
            case EQ,NE -> {
                if(!node.LOperand.exprType.isEqual(node.ROperand.exprType) && !node.ROperand.exprType.isEqual(TypeNull)) throw new SemanticError("Type Dismatched in Binary Operation3",node.getPos());
            }
        }
        switch (node.operator){
            case ADD,SUB,MUL,DIV,MOD,SHL,SHR,AND,XOR,OR,ASSIGN-> node.exprType = nodeType;
            case GT,LT,GE,LE,EQ,NE,LAND,LOR -> node.exprType = TypeBool;
        }
    }

    @Override
    public void visit(ThisExprNode node) {
        if(nowClass == null) throw new SemanticError("Pointer this can only be used in Class Definition.",node.getPos());
        node.exprType = new ClassTypeNode(nowClass,node.getPos());
        node.isAssignable = false;
    }

    @Override
    public void visit(ExprStmtNode node) {node.expr.accept(this);}

    @Override
    public void visit(BlockStmtNode node) {
        cScope = new Scope(cScope);
        if(node.stmtList != null) node.stmtList.forEach(tmp->tmp.accept(this));
        cScope = cScope.parent;
    }

    @Override
    public void visit(BreakStmtNode node) {
        if(loops == 0) throw new SemanticError("Break Statement should be in loop.",node.getPos());
    }

    @Override
    public void visit(ContinueStmtNode node) {
        if(loops == 0) throw new SemanticError("Break Statement should be in loop.",node.getPos());
    }

    @Override
    public void visit(ForStmtNode node) {
        cScope = new Scope(cScope);
        loops++;
        if(node.init != null) {
            if (!(node.init instanceof ExprStmtNode) && !(node.init instanceof VarDefStmtNode)) throw new SemanticError("Statement Invalid in For Loop", node.getPos());
            node.init.accept(this);
        }
        if(node.condition != null){
            node.condition.accept(this);
            if(!node.condition.exprType.isEqual(TypeBool)) throw new SemanticError("Condition in For loop Must be Boolean",node.getPos());
        }
        if(node.iteration != null) node.iteration.accept(this);
        if(node.loopBody != null){
            if(node.loopBody instanceof BlockStmtNode) ((BlockStmtNode) node.loopBody).stmtList.forEach(tmp->tmp.accept(this)); // avoid new scope
            else node.loopBody.accept(this);
        }
        loops--;
        cScope = cScope.parent;
    }

    @Override
    public void visit(WhileStmtNode node) {
        loops++;
        if(node.condition == null) throw new SemanticError("Condition in While can't be null",node.getPos());
        node.condition.accept(this);
        if(!node.condition.exprType.isEqual(TypeBool)) throw new SemanticError("Condition in While Must be Boolean",node.getPos());
        if(node.loopBody != null){
            cScope = new Scope(cScope);
            node.loopBody.accept(this);
            cScope = cScope.parent;
        }
        loops--;
    }

    @Override
    public void visit(IfStmtNode node) {
        if(node.condition == null) throw new SemanticError("Condition in IF can't be null ",node.getPos());
        node.condition.accept(this);
        if(!node.condition.exprType.isEqual(TypeBool)) throw new SemanticError("Condition in IF Must be Boolean",node.getPos());
        cScope = new Scope(cScope);
        if(node.thenCode != null) node.thenCode.accept(this);
        cScope = cScope.parent;
        if(node.elseCode != null){
            cScope = new Scope(cScope);
            node.elseCode.accept(this);
            cScope = cScope.parent;
        }
    }

    @Override public void visit(ArrayTypeNode node) {}
    @Override public void visit(BoolConstantExprNode node) {}
    @Override public void visit(ClassTypeNode node) {}
    @Override public void visit(IntConstantExprNode node) {}
    @Override public void visit(NullConstantExprNode node) {}
    @Override public void visit(StringConstantExprNode node) {}
    @Override public void visit(VoidTypeNode node) {}

}