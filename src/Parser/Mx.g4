grammar Mx;

@header{
package MxCompiler.Parser;
}
program:STRING_CONSTANT*;

// reserved words
INT: 'int';
BOOL: 'bool';
STRING: 'string';
fragment NULL: 'null';
VOID: 'void';
fragment TRUE: 'true';
fragment FALSE: 'false';
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
BOOL_CONSTANT: TRUE | FALSE;
INTERGER_CONSTANT: [1-9] [0-9]+ | [0-9];
STRING_CONSTANT: '"' (ESC | .)*? '"';
fragment ESC: '\\"' | '\\\\' | '\\n';
NULL_CONSTANT: NULL;

// identifier
IDENTIFIER: [A-Za-z][A-Za-z0-9_]*;

//blank char and comment
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;