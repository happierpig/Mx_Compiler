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
	 * Visit a parse tree produced by {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubProgram(MxParser.SubProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code codeBlock}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBlock(MxParser.CodeBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MxParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MxParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MxParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpStatment}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatment(MxParser.JumpStatmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableDeclStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclStmt(MxParser.VariableDeclStmtContext ctx);
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
	 * Visit a parse tree produced by the {@code objPortion}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjPortion(MxParser.ObjPortionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MxParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aftermonocularOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAftermonocularOp(MxParser.AftermonocularOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constant}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MxParser.ConstantContext ctx);
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
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAccess}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(MxParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPointer}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPointer(MxParser.ObjectPointerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocErrorType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocErrorType(MxParser.AllocErrorTypeContext ctx);
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
	/**
	 * Visit a parse tree produced by {@link MxParser#constantValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantValue(MxParser.ConstantValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(MxParser.BaseTypeContext ctx);
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
}