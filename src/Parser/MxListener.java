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
	 * Enter a parse tree produced by {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void enterSubProgram(MxParser.SubProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void exitSubProgram(MxParser.SubProgramContext ctx);
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
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MxParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MxParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MxParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MxParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MxParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MxParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStatment}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatment(MxParser.JumpStatmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStatment}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatment(MxParser.JumpStatmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MxParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableDeclStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclStmt(MxParser.VariableDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableDeclStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclStmt(MxParser.VariableDeclStmtContext ctx);
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
	 * Enter a parse tree produced by the {@code aftermonocularOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAftermonocularOp(MxParser.AftermonocularOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aftermonocularOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAftermonocularOp(MxParser.AftermonocularOpContext ctx);
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
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxParser.BinaryExprContext ctx);
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
	 * Enter a parse tree produced by the {@code allocErrorType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 */
	void enterAllocErrorType(MxParser.AllocErrorTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allocErrorType}
	 * labeled alternative in {@link MxParser#allocFormat}.
	 * @param ctx the parse tree
	 */
	void exitAllocErrorType(MxParser.AllocErrorTypeContext ctx);
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
	 * Enter a parse tree produced by {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(MxParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(MxParser.BaseTypeContext ctx);
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
}