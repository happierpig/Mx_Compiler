// Generated from /Users/dreamer/Desktop/Programm/大二 上/计算机系统/Mx Compiler/src/Parser/Mx.g4 by ANTLR 4.9.1
package Parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		DOT=32, LAMBDAS1=33, LAMBDAS2=34, INT=35, BOOL=36, STRING=37, VOID=38, 
		IF=39, ELSE=40, FOR=41, WHILE=42, BREAK=43, CONTINUE=44, RETURN=45, NEW=46, 
		CLASS=47, THIS=48, BOOL_CONSTANT=49, INTERGER_CONSTANT=50, STRING_CONSTANT=51, 
		NULL_CONSTANT=52, IDENTIFIER=53, WS=54, LINE_COMMENT=55, BLOCK_COMMENT=56;
	public static final int
		RULE_program = 0, RULE_subProgram = 1, RULE_constantValue = 2, RULE_baseType = 3, 
		RULE_variableType = 4, RULE_functionType = 5, RULE_statement = 6, RULE_block = 7, 
		RULE_expression = 8, RULE_allocFormat = 9, RULE_ifStmt = 10, RULE_loopStmt = 11, 
		RULE_jumpStmt = 12, RULE_variableDecl = 13, RULE_baseVariableDecl = 14, 
		RULE_functionDecl = 15, RULE_parameterList = 16, RULE_lambdaParameterList = 17, 
		RULE_parameterListForCall = 18, RULE_classDecl = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "subProgram", "constantValue", "baseType", "variableType", 
			"functionType", "statement", "block", "expression", "allocFormat", "ifStmt", 
			"loopStmt", "jumpStmt", "variableDecl", "baseVariableDecl", "functionDecl", 
			"parameterList", "lambdaParameterList", "parameterListForCall", "classDecl"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "';'", "'{'", "'}'", "'('", "')'", "'++'", "'--'", 
			"'!'", "'~'", "'*'", "'/'", "'%'", "'+'", "'-'", "'>>'", "'<<'", "'>'", 
			"'<'", "'>='", "'<='", "'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", 
			"'='", "','", "'.'", "'[&]'", "'->'", "'int'", "'bool'", "'string'", 
			"'void'", "'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", 
			"'return'", "'new'", "'class'", "'this'", null, null, null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "DOT", "LAMBDAS1", "LAMBDAS2", 
			"INT", "BOOL", "STRING", "VOID", "IF", "ELSE", "FOR", "WHILE", "BREAK", 
			"CONTINUE", "RETURN", "NEW", "CLASS", "THIS", "BOOL_CONSTANT", "INTERGER_CONSTANT", 
			"STRING_CONSTANT", "NULL_CONSTANT", "IDENTIFIER", "WS", "LINE_COMMENT", 
			"BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<SubProgramContext> subProgram() {
			return getRuleContexts(SubProgramContext.class);
		}
		public SubProgramContext subProgram(int i) {
			return getRuleContext(SubProgramContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(40);
				subProgram();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubProgramContext extends ParserRuleContext {
		public SubProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subProgram; }
	 
		public SubProgramContext() { }
		public void copyFrom(SubProgramContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionDeclarationContext extends SubProgramContext {
		public FunctionDeclContext functionDecl() {
			return getRuleContext(FunctionDeclContext.class,0);
		}
		public FunctionDeclarationContext(SubProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassDeclarationContext extends SubProgramContext {
		public ClassDeclContext classDecl() {
			return getRuleContext(ClassDeclContext.class,0);
		}
		public ClassDeclarationContext(SubProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableDeclarationContext extends SubProgramContext {
		public VariableDeclContext variableDecl() {
			return getRuleContext(VariableDeclContext.class,0);
		}
		public VariableDeclarationContext(SubProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubProgramContext subProgram() throws RecognitionException {
		SubProgramContext _localctx = new SubProgramContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_subProgram);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new FunctionDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				functionDecl();
				}
				break;
			case 2:
				_localctx = new ClassDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				classDecl();
				}
				break;
			case 3:
				_localctx = new VariableDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				variableDecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantValueContext extends ParserRuleContext {
		public TerminalNode BOOL_CONSTANT() { return getToken(MxParser.BOOL_CONSTANT, 0); }
		public TerminalNode INTERGER_CONSTANT() { return getToken(MxParser.INTERGER_CONSTANT, 0); }
		public TerminalNode STRING_CONSTANT() { return getToken(MxParser.STRING_CONSTANT, 0); }
		public TerminalNode NULL_CONSTANT() { return getToken(MxParser.NULL_CONSTANT, 0); }
		public ConstantValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterConstantValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitConstantValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitConstantValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantValueContext constantValue() throws RecognitionException {
		ConstantValueContext _localctx = new ConstantValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constantValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_CONSTANT) | (1L << INTERGER_CONSTANT) | (1L << STRING_CONSTANT) | (1L << NULL_CONSTANT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
	 
		public BaseTypeContext() { }
		public void copyFrom(BaseTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseType_BoolContext extends BaseTypeContext {
		public TerminalNode BOOL() { return getToken(MxParser.BOOL, 0); }
		public BaseType_BoolContext(BaseTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBaseType_Bool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBaseType_Bool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBaseType_Bool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CustomizedVariableTypeContext extends BaseTypeContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public CustomizedVariableTypeContext(BaseTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCustomizedVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCustomizedVariableType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCustomizedVariableType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseType_StringContext extends BaseTypeContext {
		public TerminalNode STRING() { return getToken(MxParser.STRING, 0); }
		public BaseType_StringContext(BaseTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBaseType_String(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBaseType_String(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBaseType_String(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseType_IntContext extends BaseTypeContext {
		public TerminalNode INT() { return getToken(MxParser.INT, 0); }
		public BaseType_IntContext(BaseTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBaseType_Int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBaseType_Int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBaseType_Int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_baseType);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				_localctx = new BaseType_IntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(INT);
				}
				break;
			case BOOL:
				_localctx = new BaseType_BoolContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(BOOL);
				}
				break;
			case STRING:
				_localctx = new BaseType_StringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				match(STRING);
				}
				break;
			case IDENTIFIER:
				_localctx = new CustomizedVariableTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableTypeContext extends ParserRuleContext {
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
	 
		public VariableTypeContext() { }
		public void copyFrom(VariableTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseVariableTypeContext extends VariableTypeContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public BaseVariableTypeContext(VariableTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBaseVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBaseVariableType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBaseVariableType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Array_TypeContext extends VariableTypeContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public Array_TypeContext(VariableTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArray_Type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArray_Type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArray_Type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		return variableType(0);
	}

	private VariableTypeContext variableType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, _parentState);
		VariableTypeContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_variableType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new BaseVariableTypeContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(60);
			baseType();
			}
			_ctx.stop = _input.LT(-1);
			setState(67);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Array_TypeContext(new VariableTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_variableType);
					setState(62);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(63);
					match(T__0);
					setState(64);
					match(T__1);
					}
					} 
				}
				setState(69);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FunctionTypeContext extends ParserRuleContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(MxParser.VOID, 0); }
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionType);
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				variableType(0);
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BlankStmtContext extends StatementContext {
		public BlankStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlankStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlankStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlankStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnitStatContext extends StatementContext {
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public LoopStmtContext loopStmt() {
			return getRuleContext(LoopStmtContext.class,0);
		}
		public JumpStmtContext jumpStmt() {
			return getRuleContext(JumpStmtContext.class,0);
		}
		public UnitStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterUnitStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitUnitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitUnitStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CodeBlockContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CodeBlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCodeBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCodeBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCodeBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStatContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExprStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExprStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExprStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableDeclStatContext extends StatementContext {
		public VariableDeclContext variableDecl() {
			return getRuleContext(VariableDeclContext.class,0);
		}
		public VariableDeclStatContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVariableDeclStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVariableDeclStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVariableDeclStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		try {
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new CodeBlockContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				block();
				}
				break;
			case 2:
				_localctx = new UnitStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IF:
					{
					setState(75);
					ifStmt();
					}
					break;
				case FOR:
				case WHILE:
					{
					setState(76);
					loopStmt();
					}
					break;
				case BREAK:
				case CONTINUE:
				case RETURN:
					{
					setState(77);
					jumpStmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				_localctx = new ExprStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				expression(0);
				setState(81);
				match(T__2);
				}
				break;
			case 4:
				_localctx = new VariableDeclStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(83);
				variableDecl();
				}
				break;
			case 5:
				_localctx = new BlankStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(84);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__3);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__15) | (1L << LAMBDAS1) | (1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << THIS) | (1L << BOOL_CONSTANT) | (1L << INTERGER_CONSTANT) | (1L << STRING_CONSTANT) | (1L << NULL_CONSTANT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(88);
				statement();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(94);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AllocExpContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public AllocFormatContext allocFormat() {
			return getRuleContext(AllocFormatContext.class,0);
		}
		public AllocExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAllocExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAllocExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAllocExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierContext extends ExpressionContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public IdentifierContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstantContext extends ExpressionContext {
		public ConstantValueContext constantValue() {
			return getRuleContext(ConstantValueContext.class,0);
		}
		public ConstantContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignOpContext extends ExpressionContext {
		public ExpressionContext operand1;
		public Token op;
		public ExpressionContext operand2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAssignOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAssignOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PmOpContext extends ExpressionContext {
		public ExpressionContext operand1;
		public Token op;
		public ExpressionContext operand2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PmOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPmOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPmOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPmOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegOpContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNegOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNegOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNegOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectPointerContext extends ExpressionContext {
		public TerminalNode THIS() { return getToken(MxParser.THIS, 0); }
		public ObjectPointerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterObjectPointer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitObjectPointer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitObjectPointer(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareOpContext extends ExpressionContext {
		public ExpressionContext operand1;
		public Token op;
		public ExpressionContext operand2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CompareOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCompareOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCompareOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCompareOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjPortionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MxParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public ObjPortionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterObjPortion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitObjPortion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitObjPortion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompoundExpContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CompoundExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCompoundExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCompoundExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCompoundExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaExpContext extends ExpressionContext {
		public TerminalNode LAMBDAS1() { return getToken(MxParser.LAMBDAS1, 0); }
		public TerminalNode LAMBDAS2() { return getToken(MxParser.LAMBDAS2, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LambdaParameterListContext lambdaParameterList() {
			return getRuleContext(LambdaParameterListContext.class,0);
		}
		public ParameterListForCallContext parameterListForCall() {
			return getRuleContext(ParameterListForCallContext.class,0);
		}
		public LambdaExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLambdaExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLambdaExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLambdaExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterListForCallContext parameterListForCall() {
			return getRuleContext(ParameterListForCallContext.class,0);
		}
		public FunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MonocularOpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext operand;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MonocularOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMonocularOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMonocularOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMonocularOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MdmOpContext extends ExpressionContext {
		public ExpressionContext operand1;
		public Token op;
		public ExpressionContext operand2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MdmOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMdmOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMdmOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMdmOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicOpContext extends ExpressionContext {
		public ExpressionContext operand1;
		public Token op;
		public ExpressionContext operand2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLogicOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLogicOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLogicOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayAccessContext extends ExpressionContext {
		public ExpressionContext array;
		public ExpressionContext index;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitwiseOpContext extends ExpressionContext {
		public ExpressionContext operand1;
		public Token op;
		public ExpressionContext operand2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BitwiseOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBitwiseOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBitwiseOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBitwiseOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShiftOpContext extends ExpressionContext {
		public ExpressionContext operand1;
		public Token op;
		public ExpressionContext operand2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ShiftOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterShiftOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitShiftOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitShiftOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				_localctx = new IdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(97);
				match(IDENTIFIER);
				}
				break;
			case BOOL_CONSTANT:
			case INTERGER_CONSTANT:
			case STRING_CONSTANT:
			case NULL_CONSTANT:
				{
				_localctx = new ConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98);
				constantValue();
				}
				break;
			case NEW:
				{
				_localctx = new AllocExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(99);
				match(NEW);
				setState(100);
				allocFormat();
				}
				break;
			case T__5:
				{
				_localctx = new CompoundExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(101);
				match(T__5);
				setState(102);
				expression(0);
				setState(103);
				match(T__6);
				}
				break;
			case T__7:
			case T__8:
			case T__9:
			case T__10:
				{
				_localctx = new MonocularOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				((MonocularOpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
					((MonocularOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(106);
				((MonocularOpContext)_localctx).operand = expression(15);
				}
				break;
			case T__14:
			case T__15:
				{
				_localctx = new NegOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(107);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(108);
				expression(12);
				}
				break;
			case THIS:
				{
				_localctx = new ObjectPointerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				match(THIS);
				}
				break;
			case LAMBDAS1:
				{
				_localctx = new LambdaExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				match(LAMBDAS1);
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(111);
					lambdaParameterList();
					}
				}

				setState(114);
				match(LAMBDAS2);
				setState(115);
				block();
				setState(116);
				match(T__5);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__15) | (1L << LAMBDAS1) | (1L << NEW) | (1L << THIS) | (1L << BOOL_CONSTANT) | (1L << INTERGER_CONSTANT) | (1L << STRING_CONSTANT) | (1L << NULL_CONSTANT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(117);
					parameterListForCall();
					}
				}

				setState(120);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(173);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new MdmOpContext(new ExpressionContext(_parentctx, _parentState));
						((MdmOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(124);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(125);
						((MdmOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
							((MdmOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						((MdmOpContext)_localctx).operand2 = expression(15);
						}
						break;
					case 2:
						{
						_localctx = new PmOpContext(new ExpressionContext(_parentctx, _parentState));
						((PmOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(127);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(128);
						((PmOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==T__15) ) {
							((PmOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(129);
						((PmOpContext)_localctx).operand2 = expression(14);
						}
						break;
					case 3:
						{
						_localctx = new ShiftOpContext(new ExpressionContext(_parentctx, _parentState));
						((ShiftOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(130);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(131);
						((ShiftOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
							((ShiftOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(132);
						((ShiftOpContext)_localctx).operand2 = expression(12);
						}
						break;
					case 4:
						{
						_localctx = new CompareOpContext(new ExpressionContext(_parentctx, _parentState));
						((CompareOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(133);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(134);
						((CompareOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) ) {
							((CompareOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(135);
						((CompareOpContext)_localctx).operand2 = expression(11);
						}
						break;
					case 5:
						{
						_localctx = new CompareOpContext(new ExpressionContext(_parentctx, _parentState));
						((CompareOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(136);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(137);
						((CompareOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__22 || _la==T__23) ) {
							((CompareOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(138);
						((CompareOpContext)_localctx).operand2 = expression(10);
						}
						break;
					case 6:
						{
						_localctx = new BitwiseOpContext(new ExpressionContext(_parentctx, _parentState));
						((BitwiseOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(139);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(140);
						((BitwiseOpContext)_localctx).op = match(T__24);
						setState(141);
						((BitwiseOpContext)_localctx).operand2 = expression(9);
						}
						break;
					case 7:
						{
						_localctx = new BitwiseOpContext(new ExpressionContext(_parentctx, _parentState));
						((BitwiseOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(142);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(143);
						((BitwiseOpContext)_localctx).op = match(T__25);
						setState(144);
						((BitwiseOpContext)_localctx).operand2 = expression(8);
						}
						break;
					case 8:
						{
						_localctx = new BitwiseOpContext(new ExpressionContext(_parentctx, _parentState));
						((BitwiseOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(145);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(146);
						((BitwiseOpContext)_localctx).op = match(T__26);
						setState(147);
						((BitwiseOpContext)_localctx).operand2 = expression(7);
						}
						break;
					case 9:
						{
						_localctx = new LogicOpContext(new ExpressionContext(_parentctx, _parentState));
						((LogicOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(148);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(149);
						((LogicOpContext)_localctx).op = match(T__27);
						setState(150);
						((LogicOpContext)_localctx).operand2 = expression(6);
						}
						break;
					case 10:
						{
						_localctx = new LogicOpContext(new ExpressionContext(_parentctx, _parentState));
						((LogicOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(151);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(152);
						((LogicOpContext)_localctx).op = match(T__28);
						setState(153);
						((LogicOpContext)_localctx).operand2 = expression(5);
						}
						break;
					case 11:
						{
						_localctx = new AssignOpContext(new ExpressionContext(_parentctx, _parentState));
						((AssignOpContext)_localctx).operand1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(154);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(155);
						((AssignOpContext)_localctx).op = match(T__29);
						setState(156);
						((AssignOpContext)_localctx).operand2 = expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ObjPortionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(157);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(158);
						match(DOT);
						setState(159);
						match(IDENTIFIER);
						}
						break;
					case 13:
						{
						_localctx = new FunctionCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(160);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(161);
						match(T__5);
						setState(163);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__15) | (1L << LAMBDAS1) | (1L << NEW) | (1L << THIS) | (1L << BOOL_CONSTANT) | (1L << INTERGER_CONSTANT) | (1L << STRING_CONSTANT) | (1L << NULL_CONSTANT) | (1L << IDENTIFIER))) != 0)) {
							{
							setState(162);
							parameterListForCall();
							}
						}

						setState(165);
						match(T__6);
						}
						break;
					case 14:
						{
						_localctx = new ArrayAccessContext(new ExpressionContext(_parentctx, _parentState));
						((ArrayAccessContext)_localctx).array = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(166);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(167);
						match(T__0);
						setState(168);
						((ArrayAccessContext)_localctx).index = expression(0);
						setState(169);
						match(T__1);
						}
						break;
					case 15:
						{
						_localctx = new MonocularOpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(171);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(172);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AllocFormatContext extends ParserRuleContext {
		public AllocFormatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allocFormat; }
	 
		public AllocFormatContext() { }
		public void copyFrom(AllocFormatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AllocBaseTypeContext extends AllocFormatContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public AllocBaseTypeContext(AllocFormatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAllocBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAllocBaseType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAllocBaseType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllocArrayTypeContext extends AllocFormatContext {
		public ExpressionContext arraySize;
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AllocArrayTypeContext(AllocFormatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAllocArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAllocArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAllocArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllocFormatContext allocFormat() throws RecognitionException {
		AllocFormatContext _localctx = new AllocFormatContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_allocFormat);
		try {
			int _alt;
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new AllocArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				baseType();
				setState(183); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(179);
						match(T__0);
						setState(180);
						((AllocArrayTypeContext)_localctx).arraySize = expression(0);
						setState(181);
						match(T__1);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(185); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(191);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(187);
						match(T__0);
						setState(188);
						match(T__1);
						}
						} 
					}
					setState(193);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				}
				break;
			case 2:
				_localctx = new AllocBaseTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				baseType();
				setState(197);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(195);
					match(T__5);
					setState(196);
					match(T__6);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStmtContext extends ParserRuleContext {
		public ExpressionContext condition;
		public StatementContext thenStatement;
		public StatementContext elseStatement;
		public TerminalNode IF() { return getToken(MxParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MxParser.ELSE, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(IF);
			setState(202);
			match(T__5);
			setState(203);
			((IfStmtContext)_localctx).condition = expression(0);
			setState(204);
			match(T__6);
			setState(205);
			((IfStmtContext)_localctx).thenStatement = statement();
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(206);
				match(ELSE);
				setState(207);
				((IfStmtContext)_localctx).elseStatement = statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStmtContext extends ParserRuleContext {
		public LoopStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStmt; }
	 
		public LoopStmtContext() { }
		public void copyFrom(LoopStmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WhileLoopContext extends LoopStmtContext {
		public ExpressionContext condition;
		public StatementContext loopBody;
		public TerminalNode WHILE() { return getToken(MxParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileLoopContext(LoopStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForLoopContext extends LoopStmtContext {
		public ExpressionContext condition;
		public ExpressionContext incrExp;
		public StatementContext loopBody;
		public TerminalNode FOR() { return getToken(MxParser.FOR, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VariableDeclContext variableDecl() {
			return getRuleContext(VariableDeclContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForLoopContext(LoopStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopStmtContext loopStmt() throws RecognitionException {
		LoopStmtContext _localctx = new LoopStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_loopStmt);
		int _la;
		try {
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				match(WHILE);
				setState(211);
				match(T__5);
				setState(212);
				((WhileLoopContext)_localctx).condition = expression(0);
				setState(213);
				match(T__6);
				setState(214);
				((WhileLoopContext)_localctx).loopBody = statement();
				}
				break;
			case FOR:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(FOR);
				setState(217);
				match(T__5);
				setState(220);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(218);
					variableDecl();
					}
					break;
				case 2:
					{
					setState(219);
					expression(0);
					}
					break;
				}
				setState(222);
				match(T__2);
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__15) | (1L << LAMBDAS1) | (1L << NEW) | (1L << THIS) | (1L << BOOL_CONSTANT) | (1L << INTERGER_CONSTANT) | (1L << STRING_CONSTANT) | (1L << NULL_CONSTANT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(223);
					((ForLoopContext)_localctx).condition = expression(0);
					}
				}

				setState(226);
				match(T__2);
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__15) | (1L << LAMBDAS1) | (1L << NEW) | (1L << THIS) | (1L << BOOL_CONSTANT) | (1L << INTERGER_CONSTANT) | (1L << STRING_CONSTANT) | (1L << NULL_CONSTANT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(227);
					((ForLoopContext)_localctx).incrExp = expression(0);
					}
				}

				setState(230);
				match(T__6);
				setState(231);
				((ForLoopContext)_localctx).loopBody = statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStmtContext extends ParserRuleContext {
		public JumpStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStmt; }
	 
		public JumpStmtContext() { }
		public void copyFrom(JumpStmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BreakStmtContext extends JumpStmtContext {
		public TerminalNode BREAK() { return getToken(MxParser.BREAK, 0); }
		public BreakStmtContext(JumpStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends JumpStmtContext {
		public TerminalNode RETURN() { return getToken(MxParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(JumpStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends JumpStmtContext {
		public TerminalNode CONTINUE() { return getToken(MxParser.CONTINUE, 0); }
		public ContinueStmtContext(JumpStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpStmtContext jumpStmt() throws RecognitionException {
		JumpStmtContext _localctx = new JumpStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_jumpStmt);
		int _la;
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				match(RETURN);
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__15) | (1L << LAMBDAS1) | (1L << NEW) | (1L << THIS) | (1L << BOOL_CONSTANT) | (1L << INTERGER_CONSTANT) | (1L << STRING_CONSTANT) | (1L << NULL_CONSTANT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(235);
					expression(0);
					}
				}

				setState(238);
				match(T__2);
				}
				break;
			case BREAK:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(BREAK);
				setState(240);
				match(T__2);
				}
				break;
			case CONTINUE:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				match(CONTINUE);
				setState(242);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclContext extends ParserRuleContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public List<BaseVariableDeclContext> baseVariableDecl() {
			return getRuleContexts(BaseVariableDeclContext.class);
		}
		public BaseVariableDeclContext baseVariableDecl(int i) {
			return getRuleContext(BaseVariableDeclContext.class,i);
		}
		public VariableDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVariableDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVariableDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVariableDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclContext variableDecl() throws RecognitionException {
		VariableDeclContext _localctx = new VariableDeclContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_variableDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			variableType(0);
			setState(246);
			baseVariableDecl();
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(247);
				match(T__30);
				setState(248);
				baseVariableDecl();
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseVariableDeclContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BaseVariableDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseVariableDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBaseVariableDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBaseVariableDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBaseVariableDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseVariableDeclContext baseVariableDecl() throws RecognitionException {
		BaseVariableDeclContext _localctx = new BaseVariableDeclContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_baseVariableDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(IDENTIFIER);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__29) {
				{
				setState(257);
				match(T__29);
				setState(258);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FunctionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunctionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclContext functionDecl() throws RecognitionException {
		FunctionDeclContext _localctx = new FunctionDeclContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_functionDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(261);
				functionType();
				}
				break;
			}
			setState(264);
			match(IDENTIFIER);
			setState(265);
			match(T__5);
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(266);
				parameterList();
				}
			}

			setState(269);
			match(T__6);
			setState(270);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<VariableTypeContext> variableType() {
			return getRuleContexts(VariableTypeContext.class);
		}
		public VariableTypeContext variableType(int i) {
			return getRuleContext(VariableTypeContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MxParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MxParser.IDENTIFIER, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			variableType(0);
			setState(273);
			match(IDENTIFIER);
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(274);
				match(T__30);
				setState(275);
				variableType(0);
				setState(276);
				match(IDENTIFIER);
				}
				}
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaParameterListContext extends ParserRuleContext {
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public LambdaParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLambdaParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLambdaParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLambdaParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaParameterListContext lambdaParameterList() throws RecognitionException {
		LambdaParameterListContext _localctx = new LambdaParameterListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_lambdaParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(T__5);
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(284);
				parameterList();
				}
			}

			setState(287);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListForCallContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ParameterListForCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterListForCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameterListForCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameterListForCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParameterListForCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListForCallContext parameterListForCall() throws RecognitionException {
		ParameterListForCallContext _localctx = new ParameterListForCallContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_parameterListForCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			expression(0);
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(290);
				match(T__30);
				setState(291);
				expression(0);
				}
				}
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclContext extends ParserRuleContext {
		public Token classID;
		public TerminalNode CLASS() { return getToken(MxParser.CLASS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxParser.IDENTIFIER, 0); }
		public List<VariableDeclContext> variableDecl() {
			return getRuleContexts(VariableDeclContext.class);
		}
		public VariableDeclContext variableDecl(int i) {
			return getRuleContext(VariableDeclContext.class,i);
		}
		public List<FunctionDeclContext> functionDecl() {
			return getRuleContexts(FunctionDeclContext.class);
		}
		public FunctionDeclContext functionDecl(int i) {
			return getRuleContext(FunctionDeclContext.class,i);
		}
		public ClassDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclContext classDecl() throws RecognitionException {
		ClassDeclContext _localctx = new ClassDeclContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_classDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(CLASS);
			setState(298);
			((ClassDeclContext)_localctx).classID = match(IDENTIFIER);
			setState(299);
			match(T__3);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << STRING) | (1L << VOID) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(302);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(300);
					variableDecl();
					}
					break;
				case 2:
					{
					setState(301);
					functionDecl();
					}
					break;
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(307);
			match(T__4);
			setState(308);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return variableType_sempred((VariableTypeContext)_localctx, predIndex);
		case 8:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean variableType_sempred(VariableTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 7);
		case 8:
			return precpred(_ctx, 6);
		case 9:
			return precpred(_ctx, 5);
		case 10:
			return precpred(_ctx, 4);
		case 11:
			return precpred(_ctx, 3);
		case 12:
			return precpred(_ctx, 21);
		case 13:
			return precpred(_ctx, 19);
		case 14:
			return precpred(_ctx, 17);
		case 15:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0139\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\3\3\3\3\3"+
		"\5\3\64\n\3\3\4\3\4\3\5\3\5\3\5\3\5\5\5<\n\5\3\6\3\6\3\6\3\6\3\6\3\6\7"+
		"\6D\n\6\f\6\16\6G\13\6\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\5\bQ\n\b\3\b\3"+
		"\b\3\b\3\b\3\b\5\bX\n\b\3\t\3\t\7\t\\\n\t\f\t\16\t_\13\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\ns\n\n\3"+
		"\n\3\n\3\n\3\n\5\ny\n\n\3\n\3\n\5\n}\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a6\n\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00b0\n\n\f\n\16\n\u00b3\13\n\3\13"+
		"\3\13\3\13\3\13\3\13\6\13\u00ba\n\13\r\13\16\13\u00bb\3\13\3\13\7\13\u00c0"+
		"\n\13\f\13\16\13\u00c3\13\13\3\13\3\13\3\13\5\13\u00c8\n\13\5\13\u00ca"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d3\n\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\5\r\u00df\n\r\3\r\3\r\5\r\u00e3\n\r\3\r\3\r\5\r\u00e7"+
		"\n\r\3\r\3\r\5\r\u00eb\n\r\3\16\3\16\5\16\u00ef\n\16\3\16\3\16\3\16\3"+
		"\16\3\16\5\16\u00f6\n\16\3\17\3\17\3\17\3\17\7\17\u00fc\n\17\f\17\16\17"+
		"\u00ff\13\17\3\17\3\17\3\20\3\20\3\20\5\20\u0106\n\20\3\21\5\21\u0109"+
		"\n\21\3\21\3\21\3\21\5\21\u010e\n\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\7\22\u0119\n\22\f\22\16\22\u011c\13\22\3\23\3\23\5\23\u0120"+
		"\n\23\3\23\3\23\3\24\3\24\3\24\7\24\u0127\n\24\f\24\16\24\u012a\13\24"+
		"\3\25\3\25\3\25\3\25\3\25\7\25\u0131\n\25\f\25\16\25\u0134\13\25\3\25"+
		"\3\25\3\25\3\25\2\4\n\22\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(\2\n\3\2\63\66\3\2\n\r\3\2\21\22\3\2\16\20\3\2\23\24\3\2\25\30\3\2"+
		"\31\32\3\2\n\13\2\u0162\2-\3\2\2\2\4\63\3\2\2\2\6\65\3\2\2\2\b;\3\2\2"+
		"\2\n=\3\2\2\2\fJ\3\2\2\2\16W\3\2\2\2\20Y\3\2\2\2\22|\3\2\2\2\24\u00c9"+
		"\3\2\2\2\26\u00cb\3\2\2\2\30\u00ea\3\2\2\2\32\u00f5\3\2\2\2\34\u00f7\3"+
		"\2\2\2\36\u0102\3\2\2\2 \u0108\3\2\2\2\"\u0112\3\2\2\2$\u011d\3\2\2\2"+
		"&\u0123\3\2\2\2(\u012b\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2"+
		"-.\3\2\2\2.\3\3\2\2\2/-\3\2\2\2\60\64\5 \21\2\61\64\5(\25\2\62\64\5\34"+
		"\17\2\63\60\3\2\2\2\63\61\3\2\2\2\63\62\3\2\2\2\64\5\3\2\2\2\65\66\t\2"+
		"\2\2\66\7\3\2\2\2\67<\7%\2\28<\7&\2\29<\7\'\2\2:<\7\67\2\2;\67\3\2\2\2"+
		";8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<\t\3\2\2\2=>\b\6\1\2>?\5\b\5\2?E\3\2\2"+
		"\2@A\f\3\2\2AB\7\3\2\2BD\7\4\2\2C@\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2"+
		"\2F\13\3\2\2\2GE\3\2\2\2HK\5\n\6\2IK\7(\2\2JH\3\2\2\2JI\3\2\2\2K\r\3\2"+
		"\2\2LX\5\20\t\2MQ\5\26\f\2NQ\5\30\r\2OQ\5\32\16\2PM\3\2\2\2PN\3\2\2\2"+
		"PO\3\2\2\2QX\3\2\2\2RS\5\22\n\2ST\7\5\2\2TX\3\2\2\2UX\5\34\17\2VX\7\5"+
		"\2\2WL\3\2\2\2WP\3\2\2\2WR\3\2\2\2WU\3\2\2\2WV\3\2\2\2X\17\3\2\2\2Y]\7"+
		"\6\2\2Z\\\5\16\b\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^`\3\2\2\2"+
		"_]\3\2\2\2`a\7\7\2\2a\21\3\2\2\2bc\b\n\1\2c}\7\67\2\2d}\5\6\4\2ef\7\60"+
		"\2\2f}\5\24\13\2gh\7\b\2\2hi\5\22\n\2ij\7\t\2\2j}\3\2\2\2kl\t\3\2\2l}"+
		"\5\22\n\21mn\t\4\2\2n}\5\22\n\16o}\7\62\2\2pr\7#\2\2qs\5$\23\2rq\3\2\2"+
		"\2rs\3\2\2\2st\3\2\2\2tu\7$\2\2uv\5\20\t\2vx\7\b\2\2wy\5&\24\2xw\3\2\2"+
		"\2xy\3\2\2\2yz\3\2\2\2z{\7\t\2\2{}\3\2\2\2|b\3\2\2\2|d\3\2\2\2|e\3\2\2"+
		"\2|g\3\2\2\2|k\3\2\2\2|m\3\2\2\2|o\3\2\2\2|p\3\2\2\2}\u00b1\3\2\2\2~\177"+
		"\f\20\2\2\177\u0080\t\5\2\2\u0080\u00b0\5\22\n\21\u0081\u0082\f\17\2\2"+
		"\u0082\u0083\t\4\2\2\u0083\u00b0\5\22\n\20\u0084\u0085\f\r\2\2\u0085\u0086"+
		"\t\6\2\2\u0086\u00b0\5\22\n\16\u0087\u0088\f\f\2\2\u0088\u0089\t\7\2\2"+
		"\u0089\u00b0\5\22\n\r\u008a\u008b\f\13\2\2\u008b\u008c\t\b\2\2\u008c\u00b0"+
		"\5\22\n\f\u008d\u008e\f\n\2\2\u008e\u008f\7\33\2\2\u008f\u00b0\5\22\n"+
		"\13\u0090\u0091\f\t\2\2\u0091\u0092\7\34\2\2\u0092\u00b0\5\22\n\n\u0093"+
		"\u0094\f\b\2\2\u0094\u0095\7\35\2\2\u0095\u00b0\5\22\n\t\u0096\u0097\f"+
		"\7\2\2\u0097\u0098\7\36\2\2\u0098\u00b0\5\22\n\b\u0099\u009a\f\6\2\2\u009a"+
		"\u009b\7\37\2\2\u009b\u00b0\5\22\n\7\u009c\u009d\f\5\2\2\u009d\u009e\7"+
		" \2\2\u009e\u00b0\5\22\n\5\u009f\u00a0\f\27\2\2\u00a0\u00a1\7\"\2\2\u00a1"+
		"\u00b0\7\67\2\2\u00a2\u00a3\f\25\2\2\u00a3\u00a5\7\b\2\2\u00a4\u00a6\5"+
		"&\24\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00b0\7\t\2\2\u00a8\u00a9\f\23\2\2\u00a9\u00aa\7\3\2\2\u00aa\u00ab\5"+
		"\22\n\2\u00ab\u00ac\7\4\2\2\u00ac\u00b0\3\2\2\2\u00ad\u00ae\f\22\2\2\u00ae"+
		"\u00b0\t\t\2\2\u00af~\3\2\2\2\u00af\u0081\3\2\2\2\u00af\u0084\3\2\2\2"+
		"\u00af\u0087\3\2\2\2\u00af\u008a\3\2\2\2\u00af\u008d\3\2\2\2\u00af\u0090"+
		"\3\2\2\2\u00af\u0093\3\2\2\2\u00af\u0096\3\2\2\2\u00af\u0099\3\2\2\2\u00af"+
		"\u009c\3\2\2\2\u00af\u009f\3\2\2\2\u00af\u00a2\3\2\2\2\u00af\u00a8\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\23\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b9\5\b\5"+
		"\2\u00b5\u00b6\7\3\2\2\u00b6\u00b7\5\22\n\2\u00b7\u00b8\7\4\2\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u00b5\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00c1\3\2\2\2\u00bd\u00be\7\3\2\2\u00be"+
		"\u00c0\7\4\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00ca\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		"\u00c7\5\b\5\2\u00c5\u00c6\7\b\2\2\u00c6\u00c8\7\t\2\2\u00c7\u00c5\3\2"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00b4\3\2\2\2\u00c9"+
		"\u00c4\3\2\2\2\u00ca\25\3\2\2\2\u00cb\u00cc\7)\2\2\u00cc\u00cd\7\b\2\2"+
		"\u00cd\u00ce\5\22\n\2\u00ce\u00cf\7\t\2\2\u00cf\u00d2\5\16\b\2\u00d0\u00d1"+
		"\7*\2\2\u00d1\u00d3\5\16\b\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\27\3\2\2\2\u00d4\u00d5\7,\2\2\u00d5\u00d6\7\b\2\2\u00d6\u00d7\5\22\n"+
		"\2\u00d7\u00d8\7\t\2\2\u00d8\u00d9\5\16\b\2\u00d9\u00eb\3\2\2\2\u00da"+
		"\u00db\7+\2\2\u00db\u00de\7\b\2\2\u00dc\u00df\5\34\17\2\u00dd\u00df\5"+
		"\22\n\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00de\u00df\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\u00e2\7\5\2\2\u00e1\u00e3\5\22\n\2\u00e2\u00e1\3"+
		"\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\7\5\2\2\u00e5"+
		"\u00e7\5\22\n\2\u00e6\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3"+
		"\2\2\2\u00e8\u00e9\7\t\2\2\u00e9\u00eb\5\16\b\2\u00ea\u00d4\3\2\2\2\u00ea"+
		"\u00da\3\2\2\2\u00eb\31\3\2\2\2\u00ec\u00ee\7/\2\2\u00ed\u00ef\5\22\n"+
		"\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f6"+
		"\7\5\2\2\u00f1\u00f2\7-\2\2\u00f2\u00f6\7\5\2\2\u00f3\u00f4\7.\2\2\u00f4"+
		"\u00f6\7\5\2\2\u00f5\u00ec\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f5\u00f3\3\2"+
		"\2\2\u00f6\33\3\2\2\2\u00f7\u00f8\5\n\6\2\u00f8\u00fd\5\36\20\2\u00f9"+
		"\u00fa\7!\2\2\u00fa\u00fc\5\36\20\2\u00fb\u00f9\3\2\2\2\u00fc\u00ff\3"+
		"\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\u0101\7\5\2\2\u0101\35\3\2\2\2\u0102\u0105\7\67\2"+
		"\2\u0103\u0104\7 \2\2\u0104\u0106\5\22\n\2\u0105\u0103\3\2\2\2\u0105\u0106"+
		"\3\2\2\2\u0106\37\3\2\2\2\u0107\u0109\5\f\7\2\u0108\u0107\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\7\67\2\2\u010b\u010d\7"+
		"\b\2\2\u010c\u010e\5\"\22\2\u010d\u010c\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"\u010f\3\2\2\2\u010f\u0110\7\t\2\2\u0110\u0111\5\20\t\2\u0111!\3\2\2\2"+
		"\u0112\u0113\5\n\6\2\u0113\u011a\7\67\2\2\u0114\u0115\7!\2\2\u0115\u0116"+
		"\5\n\6\2\u0116\u0117\7\67\2\2\u0117\u0119\3\2\2\2\u0118\u0114\3\2\2\2"+
		"\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b#\3"+
		"\2\2\2\u011c\u011a\3\2\2\2\u011d\u011f\7\b\2\2\u011e\u0120\5\"\22\2\u011f"+
		"\u011e\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\7\t"+
		"\2\2\u0122%\3\2\2\2\u0123\u0128\5\22\n\2\u0124\u0125\7!\2\2\u0125\u0127"+
		"\5\22\n\2\u0126\u0124\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2"+
		"\u0128\u0129\3\2\2\2\u0129\'\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c\7"+
		"\61\2\2\u012c\u012d\7\67\2\2\u012d\u0132\7\6\2\2\u012e\u0131\5\34\17\2"+
		"\u012f\u0131\5 \21\2\u0130\u012e\3\2\2\2\u0130\u012f\3\2\2\2\u0131\u0134"+
		"\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0135\3\2\2\2\u0134"+
		"\u0132\3\2\2\2\u0135\u0136\7\7\2\2\u0136\u0137\7\5\2\2\u0137)\3\2\2\2"+
		"$-\63;EJPW]rx|\u00a5\u00af\u00b1\u00bb\u00c1\u00c7\u00c9\u00d2\u00de\u00e2"+
		"\u00e6\u00ea\u00ee\u00f5\u00fd\u0105\u0108\u010d\u011a\u011f\u0128\u0130"+
		"\u0132";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}