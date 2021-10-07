// Generated from /Users/dreamer/Desktop/Programm/大二 上/计算机系统/Mx Compiler/src/Parser/Mx.g4 by ANTLR 4.9.1
package Parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(MxParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MxParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableDeclaration}
	 * labeled alternative in {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(MxParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#constantValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantValue(MxParser.ConstantValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseType_Int}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType_Int(MxParser.BaseType_IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseType_Bool}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType_Bool(MxParser.BaseType_BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseType_String}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType_String(MxParser.BaseType_StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code customizedVariableType}
	 * labeled alternative in {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCustomizedVariableType(MxParser.CustomizedVariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseVariableType}
	 * labeled alternative in {@link MxParser#variableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseVariableType(MxParser.BaseVariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array_Type}
	 * labeled alternative in {@link MxParser#variableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_Type(MxParser.Array_TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#functionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionType(MxParser.FunctionTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code codeBlock}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBlock(MxParser.CodeBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitStat(MxParser.UnitStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStat(MxParser.ExprStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableDeclStat}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclStat(MxParser.VariableDeclStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blankStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlankStmt(MxParser.BlankStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MxParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocExp(MxParser.AllocExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MxParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constant}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MxParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOp(MxParser.AssignOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pmOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPmOp(MxParser.PmOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegOp(MxParser.NegOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPointer}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPointer(MxParser.ObjectPointerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareOp(MxParser.CompareOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objPortion}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjPortion(MxParser.ObjPortionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compoundExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundExp(MxParser.CompoundExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambdaExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExp(MxParser.LambdaExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(MxParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code monocularOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonocularOp(MxParser.MonocularOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mdmOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMdmOp(MxParser.MdmOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicOp(MxParser.LogicOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAccess}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(MxParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseOp(MxParser.BitwiseOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shiftOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftOp(MxParser.ShiftOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocArrayType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocArrayType(MxParser.AllocArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocBaseType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocBaseType(MxParser.AllocBaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link MxParser#loopStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(MxParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link MxParser#loopStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(MxParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxParser#jumpStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#variableDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDecl(MxParser.VariableDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#baseVariableDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseVariableDecl(MxParser.BaseVariableDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(MxParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lambdaParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaParameterList(MxParser.LambdaParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parameterListForCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterListForCall(MxParser.ParameterListForCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecl(MxParser.ClassDeclContext ctx);
}