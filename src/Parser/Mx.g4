grammar Mx;

@header{
package MxCompiler.Parser;
}

program: subProgram*;

subProgram
    :   functionDecl    #functionDeclaration
    |   classDecl       #classDeclaration
    |   variableDecl    #variableDeclaration
    ;

// fundamental type parser
constantValue: BOOL_CONSTANT | INTERGER_CONSTANT | STRING_CONSTANT;

baseType
    :   INT         #baseType_Int
    |   BOOL        #baseType_Bool
    |   STRING      #baseType_String
    |   IDENTIFIER  #customizedVariableType
    ;

variableType
    :   baseType            #baseVariableType
    |   variableType '[' ']'  #array_Type
    ;

functionType
    : variableType
    | VOID
    ;

// statement definition
statement
    :   block                               #codeBlock
    |   (ifStmt | loopStmt | jumpStmt)      #unitStat
    |   expression ';'                      #exprStat
    |   variableDecl                        #variableDeclStat
    |   ';'                                 #blankStmt
    ;

block: '{' statement* '}';

expression
    :   IDENTIFIER                                                      #identifier
    |   constantValue                                                   #constant
    |   expression DOT IDENTIFIER                                       #objPortion
    |   NEW allocFormat                                                 #allocExp
    |   expression '(' parameterListForCall? ')'                        #functionCall
    |   '(' expression ')'                                              #compoundExp
    |   array=expression '[' index=expression ']'                       #arrayAccess
    |   <assoc=right> op=('!'|'~'|'++'|'--') operand=expression         #monocularOp
    |   operand1=expression op=('*'|'/'|'%') operand2=expression        #mdmOp
    |   operand1=expression op=('+'|'-') operand2=expression            #pmOp
    |   operand1=expression op=('>>'|'<<') operand2=expression          #shiftOp
    |   operand1=expression op=('>'|'<'|'>='|'<=') operand2=expression  #compareOp
    |   operand1=expression op=('=='|'!=') operand2=expression          #compareOp
    |   operand1=expression op='&' operand2=expression                  #bitwiseOp
    |   operand1=expression op='^' operand2=expression                  #bitwiseOp
    |   operand1=expression op='|' operand2=expression                  #bitwiseOp
    |   operand1=expression op='&&' operand2=expression                 #logicOp
    |   operand1=expression op='||' operand2=expression                 #logicOp
    |   <assoc=right> operand1=expression op='=' operand2=expression    #assignOp
    |   THIS                                                            #objectPointer
    ;

allocFormat
    :   baseType ('[' arraySize=expression ']')+ ('[' ']')*    #allocArrayType
    |   baseType ('(' ')')?                                      #allocBaseType
    ;

ifStmt: IF '(' condition=expression ')' thenStatement=statement (ELSE elseStatement=statement)?;

loopStmt
    :   WHILE '(' condition=expression ')' loopBody=statement                                                           #whileLoop
    |   FOR '(' (variableDecl | expression)? ';' condition=expression? ';' incrExp=expression? ')' loopBody=statement   #forLoop
    ;

jumpStmt
    :   RETURN expression? ';'  #returnStmt
    |   BREAK ';'               #breakStmt
    |   CONTINUE ';'            #continueStmt
    ;

// declaration definition
variableDecl: variableType baseVariableDecl (',' baseVariableDecl)* ';';

baseVariableDecl: IDENTIFIER ('=' expression)?;

functionDecl: functionType? IDENTIFIER '(' parameterList? ')' block;

parameterList: variableType IDENTIFIER (',' variableType IDENTIFIER)*;

parameterListForCall: expression (',' expression)*;

classDecl: CLASS classID=IDENTIFIER '{' (variableDecl|functionDecl)* '}' ';';




//symbols
DOT: '.';


// reserved words
INT: 'int';
BOOL: 'bool';
STRING: 'string';
VOID: 'void';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
BREAK: 'break';
CONTINUE: 'continue';
RETURN: 'return';
NEW: 'new';
CLASS: 'class';
THIS: 'this';

// constant value
BOOL_CONSTANT: 'true' | 'false';
INTERGER_CONSTANT: [1-9] [0-9]+ | [0-9];
STRING_CONSTANT: '"' (ESC | .)*? '"';
fragment ESC: '\\"' | '\\\\' | '\\n';
NULL_CONSTANT: 'null';

// identifier
IDENTIFIER: [A-Za-z][A-Za-z0-9_]*;

//blank char and comment
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;