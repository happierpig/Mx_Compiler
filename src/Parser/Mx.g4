grammar Mx;

program: subProgram*;

subProgram
    :   functionDecl
    |   classDecl
    |   variableDecl ';'
    ;

// statement definition
statement
    :   block                                                                                                                               #codeBlock
    |   ifStmt                                                                                                                              #ifStatement
    |   WHILE '(' condition=expression ')' loopBody=statement                                                                               #whileStatement
    |   FOR '(' (initDecl=variableDecl | initExpr=expression)? ';' condition=expression? ';' incrExp=expression? ')' loopBody=statement     #forStatement
    |   jumpStmt                                                                                                                            #jumpStatment
    |   expression ';'                                                                                                                      #exprStmt
    |   variableDecl ';'                                                                                                                    #variableDeclStmt
    |   ';'                                                                                                                                 #blankStmt
    ;

block: '{' statement* '}';

expression
    :   IDENTIFIER                                                                  #identifier
    |   constantValue                                                               #constant
    |   expression DOT IDENTIFIER                                                   #objPortion
    |   NEW allocFormat                                                             #allocExp
    |   expression '(' parameterListForCall? ')'                                    #functionCall
    |   '(' expression ')'                                                          #compoundExp
    |   array=expression '[' index=expression ']'                                   #arrayAccess
    |   operand=expression op=('++'|'--')                                           #aftermonocularOp
    |   <assoc=right> op=('!'|'~'|'++'|'--') operand=expression                     #monocularOp
    |   operand1=expression op=('*'|'/'|'%') operand2=expression                    #binaryExpr
    |   operand1=expression op=('+'|'-') operand2=expression                        #binaryExpr
    |   <assoc=right> op=('-'|'+') operand=expression                               #monocularOp
    |   operand1=expression op=('>>'|'<<') operand2=expression                      #binaryExpr
    |   operand1=expression op=('>'|'<'|'>='|'<=') operand2=expression              #binaryExpr
    |   operand1=expression op=('=='|'!=') operand2=expression                      #binaryExpr
    |   operand1=expression op='&' operand2=expression                              #binaryExpr
    |   operand1=expression op='^' operand2=expression                              #binaryExpr
    |   operand1=expression op='|' operand2=expression                              #binaryExpr
    |   operand1=expression op='&&' operand2=expression                             #binaryExpr
    |   operand1=expression op='||' operand2=expression                             #binaryExpr
    |   <assoc=right> operand1=expression op='=' operand2=expression                #binaryExpr
    |   THIS                                                                        #objectPointer
    |   LAMBDAS1 lambdaParameterList? LAMBDAS2 block '(' parameterListForCall? ')'  #lambdaExp
    ;

allocFormat
    :   baseType ('[' expression ']')+ ('[' ']')+ ('[' expression ']')+ #allocErrorType
    |   baseType ('[' expression ']')+ ('[' ']')*                       #allocArrayType
    |   baseType ('(' ')')?                                             #allocBaseType
    ;

ifStmt: IF '(' condition=expression ')' thenStatement=statement (ELSE elseStatement=statement)?;

jumpStmt
    :   RETURN expression? ';'  #returnStmt
    |   BREAK ';'               #breakStmt
    |   CONTINUE ';'            #continueStmt
    ;

// declaration definition
variableDecl: variableType baseVariableDecl (',' baseVariableDecl)* ;

baseVariableDecl: IDENTIFIER ('=' expression)?;

functionDecl: functionType? IDENTIFIER '(' parameterList? ')' block;

parameterList: variableType IDENTIFIER (',' variableType IDENTIFIER)*;

lambdaParameterList: '(' parameterList? ')';

parameterListForCall: expression (',' expression)*;

classDecl: CLASS classID=IDENTIFIER '{' ((variableDecl ';')|functionDecl)* '}' ';';

constantValue: BOOL_CONSTANT | INTERGER_CONSTANT | STRING_CONSTANT | NULL_CONSTANT;

baseType
    :   INT
    |   BOOL
    |   STRING
    |   IDENTIFIER
    ;

variableType
    :   baseType                #baseVariableType
    |   variableType '[' ']'    #array_Type
    ;

functionType
    : variableType
    | VOID
    ;

//symbols
DOT: '.';
LAMBDAS1: '[&]';
LAMBDAS2: '->';

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