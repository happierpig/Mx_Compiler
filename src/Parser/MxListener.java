// Generated from /Users/dreamer/Desktop/Programm/大二 上/计算机系统/Mx Compiler/src/Parser/Mx.g4 by ANTLR 4.9.1
package Parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(MxParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(MxParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MxParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MxParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(MxParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(MxParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#constantValue}.
	 * @param ctx the parse tree
	 */
	void enterConstantValue(MxParser.ConstantValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#constantValue}.
	 * @param ctx the parse tree
	 */
	void exitConstantValue(MxParser.ConstantValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseType_Int}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType_Int(MxParser.BaseType_IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseType_Int}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType_Int(MxParser.BaseType_IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseType_Bool}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType_Bool(MxParser.BaseType_BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseType_Bool}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType_Bool(MxParser.BaseType_BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseType_String}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType_String(MxParser.BaseType_StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseType_String}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType_String(MxParser.BaseType_StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code customizedVariableType}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterCustomizedVariableType(MxParser.CustomizedVariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code customizedVariableType}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitCustomizedVariableType(MxParser.CustomizedVariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseVariableType}
	 * labeled alternative in {@link MxParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterBaseVariableType(MxParser.BaseVariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseVariableType}
	 * labeled alternative in {@link MxParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitBaseVariableType(MxParser.BaseVariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code array_Type}
	 * labeled alternative in {@link MxParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterArray_Type(MxParser.Array_TypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code array_Type}
	 * labeled alternative in {@link MxParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitArray_Type(MxParser.Array_TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(MxParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(MxParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code codeBlock}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCodeBlock(MxParser.CodeBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code codeBlock}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCodeBlock(MxParser.CodeBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterUnitStat(MxParser.UnitStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitUnitStat(MxParser.UnitStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStat(MxParser.ExprStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStat(MxParser.ExprStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableDeclStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclStat(MxParser.VariableDeclStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableDeclStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclStat(MxParser.VariableDeclStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blankStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlankStmt(MxParser.BlankStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blankStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlankStmt(MxParser.BlankStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allocExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAllocExp(MxParser.AllocExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allocExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAllocExp(MxParser.AllocExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MxParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MxParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constant}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MxParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constant}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MxParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignOp(MxParser.AssignOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignOp(MxParser.AssignOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pmOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPmOp(MxParser.PmOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pmOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPmOp(MxParser.PmOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegOp(MxParser.NegOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegOp(MxParser.NegOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectPointer}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterObjectPointer(MxParser.ObjectPointerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectPointer}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitObjectPointer(MxParser.ObjectPointerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompareOp(MxParser.CompareOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompareOp(MxParser.CompareOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objPortion}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterObjPortion(MxParser.ObjPortionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objPortion}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitObjPortion(MxParser.ObjPortionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compoundExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompoundExp(MxParser.CompoundExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compoundExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompoundExp(MxParser.CompoundExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExp(MxParser.LambdaExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExp(MxParser.LambdaExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(MxParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(MxParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code monocularOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMonocularOp(MxParser.MonocularOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code monocularOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMonocularOp(MxParser.MonocularOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mdmOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMdmOp(MxParser.MdmOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mdmOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMdmOp(MxParser.MdmOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicOp(MxParser.LogicOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicOp(MxParser.LogicOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAccess}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(MxParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAccess}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(MxParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitwiseOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseOp(MxParser.BitwiseOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitwiseOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseOp(MxParser.BitwiseOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shiftOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShiftOp(MxParser.ShiftOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shiftOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShiftOp(MxParser.ShiftOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allocArrayType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 */
	void enterAllocArrayType(MxParser.AllocArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allocArrayType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 */
	void exitAllocArrayType(MxParser.AllocArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allocBaseType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 */
	void enterAllocBaseType(MxParser.AllocBaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allocBaseType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 */
	void exitAllocBaseType(MxParser.AllocBaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link MxParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(MxParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link MxParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(MxParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link MxParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(MxParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link MxParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(MxParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableDecl(MxParser.VariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableDecl(MxParser.VariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#baseVariableDecl}.
	 * @param ctx the parse tree
	 */
	void enterBaseVariableDecl(MxParser.BaseVariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#baseVariableDecl}.
	 * @param ctx the parse tree
	 */
	void exitBaseVariableDecl(MxParser.BaseVariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(MxParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(MxParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lambdaParameterList}.
	 * @param ctx the parse tree
	 */
	void enterLambdaParameterList(MxParser.LambdaParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lambdaParameterList}.
	 * @param ctx the parse tree
	 */
	void exitLambdaParameterList(MxParser.LambdaParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parameterListForCall}.
	 * @param ctx the parse tree
	 */
	void enterParameterListForCall(MxParser.ParameterListForCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parameterListForCall}.
	 * @param ctx the parse tree
	 */
	void exitParameterListForCall(MxParser.ParameterListForCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassDecl(MxParser.ClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassDecl(MxParser.ClassDeclContext ctx);
}