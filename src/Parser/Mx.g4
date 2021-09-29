grammar Mx;

@header{
package MxCompiler.Parser;
}

program: execCode*;

subProgram
    :   functionDecl
    |   classDecl
    |   variableDecl
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
    |   variableType '[]'   #array_Type
    ;

functionType
    : variableType
    | VOID
    ;

// statement definition
statement
    :   (ifStmt | loopStmt | jumpStmt)      #unitStat
    |   expression ';'                      #exprStat
    |   variableDecl                        #variableDeclStat
    |   ';'                                 #blankStmt
    ;

block: '{' execCode* '}';

execCode
    :   statement   #singleStatement
    |   block       #codeBlock
    ;

expression
    :   IDENTIFIER                                                      #idnetifier
    |   constantValue                                                   #constant
    |   IDENTIFIER DOT IDENTIFIER                                       #objPortion
    |   NEW allocFormat                                                 #allocExp
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
    ;

allocFormat
    :   baseType ('[' arraySize=expression ']')+ ('[]')*    #allocArrayType
    |   baseType '()'?                                      #allocBaseType
    ;

ifStmt: IF '(' condition=expression ')' thenStatement=execCode (ELSE elseStatement=execCode)?;

loopStmt
    :   WHILE '(' condition=expression ')' loopBody=execCode                                                           #whileLoop
    |   FOR '(' (variableDecl | expression)? ';' condition=expression? ';' incrExp=expression? ')' loopBody=execCode   #forLoop
    ;

jumpStmt
    :   RETURN expression? ';'  #returnStmt
    |   BREAK ';'               #breakStmt
    |   CONTINUE ';'            #continueStmt
    ;

// declaration definition
variableDecl: variableType baseVariableDecl (',' baseVariableDecl)* ';';

baseVariableDecl: IDENTIFIER ('=' expression)?;

functionDecl:IDENTIFIER;

classDecl:IDENTIFIER;





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