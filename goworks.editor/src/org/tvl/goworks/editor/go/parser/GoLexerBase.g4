/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
lexer grammar GoLexerBase;

@header {/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;
}

@members {
protected int getMultilineCommentType() {
    return _modeStack.peek()==DEFAULT_MODE ? ML_COMMENT : ML_COMMENT;
}
}

Break       : 'break';
Case        : 'case';
Chan        : 'chan';
Const       : 'const';
Continue    : 'continue';
Default     : 'default';
Defer       : 'defer';
Else        : 'else';
Fallthrough : 'fallthrough';
For         : 'for';
Func        : 'func';
Go          : 'go';
Goto        : 'goto';
If          : 'if';
Import      : 'import';
Interface   : 'interface';
Map         : 'map';
Package     : 'package';
Range       : 'range';
Return      : 'return';
Select      : 'select';
Struct      : 'struct';
Switch      : 'switch';
Type        : 'type';
Var         : 'var';

Plus : '+';
Minus : '-';
Star : '*';
Slash : '/';
Percent : '%';
Amp : '&';
Pipe : '|';
Caret : '^';
LeftShift : '<<';
RightShift : '>>';
AmpCaret : '&^';
PlusEqual : '+=';
MinusEqual : '-=';
StarEqual : '*=';
SlashEqual : '/=';
PercentEqual : '%=';
AmpEqual : '&=';
PipeEqual : '|=';
CaretEqual : '^=';
LeftShiftEqual : '<<=';
RightShiftEqual : '>>=';
AmpCaretEqual : '&^=';
And : '&&';
Or : '||';
LeftArrow : '<-';
Inc : '++';
Dec : '--';
EqualEqual : '==';
LessThan : '<';
GreaterThan : '>';
Equal : '=';
Bang : '!';
BangEqual : '!=';
LessEqual : '<=';
GreaterEqual : '>=';
ColonEqual : ':=';
Ellipsis : '...';
LeftParen : '(';
RightParen : ')';
LeftBrack : '[';
RightBrack : ']';
LeftBrace : '{';
RightBrace : '}';
Comma : ',';
Dot : '.';
Semi : ';';
Colon : ':';

IDENTIFIER
	:	LETTER_CHAR (LETTER_CHAR | UNICODE_DIGIT_CHAR)*
	;

WS
	:	(	' '
		|	'\t'
		)+                      {$channel = HIDDEN;}
	;

NEWLINE
	:	'\r'? '\n'              {$channel = HIDDEN;}
	;

COMMENT
	:	'//' (~('\r' | '\n'))*  {$channel = HIDDEN;}
	;

ML_COMMENT
    :   '/*' .* '*/'            {$channel = HIDDEN;}
    ;

INT_LITERAL
    :   DecimalLiteral
    |   OctalLiteral
    |   HexLiteral
    ;

fragment
DecimalLiteral
    :   '1'..'9' DECIMAL_DIGIT_CHAR*
    ;

fragment
OctalLiteral
    :   '0' OCTAL_DIGIT_CHAR*
    ;

fragment
HexLiteral
    :   '0' ('x' | 'X') HEX_DIGIT_CHAR+
    ;

IMAGINARY_LITERAL
    :   (Decimals | FLOAT_LITERAL) 'i'
    ;

FLOAT_LITERAL
    :   Decimals '.' Decimals? Exponent?
    |   Decimals Exponent
    |   '.' Decimals Exponent?
    ;

fragment
Decimals
    :   DECIMAL_DIGIT_CHAR+
    ;

fragment
Exponent
    :   ('e' | 'E') ('+' | '-')? Decimals
    ;

CharLiteral
    :   '\'' (UNICODE_VALUE_NOSQUOTE | BYTE_VALUE) '\''
    ;

fragment
UNICODE_VALUE_NOSQUOTE
    :   UNICODE_CHAR_NOSQUOTE | LittleUValue | BigUValue | EscapedChar
    ;

fragment
UNICODE_VALUE_NODQUOTE
    :   UNICODE_CHAR_NODQUOTE | LittleUValue | BigUValue | EscapedChar
    ;

fragment
BYTE_VALUE
    :   OCTAL_BYTE_VALUE | HexByteValue
    ;

fragment
OCTAL_BYTE_VALUE
    :   '\\' OCTAL_DIGIT_CHAR OCTAL_DIGIT_CHAR OCTAL_DIGIT_CHAR
    ;

fragment
HexByteValue
    :   '\\x' HEX_DIGIT_CHAR HEX_DIGIT_CHAR
    ;

fragment
LittleUValue
    :   '\\u' HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR
    ;

fragment
BigUValue
    :   '\\U' HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR HEX_DIGIT_CHAR
    ;

fragment
EscapedChar
    :   '\\' ('a' | 'b' | 'f' | 'n' | 'r' | 't' | 'v' | '\\' | '\'' | '"')
    ;

StringLiteral
    :   RawStringLiteral
    |   InterpretedStringLiteral
    ;

fragment
RawStringLiteral
    :   '`' (UNICODE_CHAR_NOBTICK | NEWLINE_CHAR)* '`'
    ;

fragment
InterpretedStringLiteral
    :   '"' (UNICODE_VALUE_NODQUOTE | BYTE_VALUE)* '"'
    ;

fragment
NEWLINE_CHAR
    :   '\n'
    ;

fragment
UNICODE_CHAR_NOSQUOTE
    :   ~('\n' | '\'')
    ;

fragment
UNICODE_CHAR_NODQUOTE
    :   ~('\n' | '"')
    ;

fragment
UNICODE_CHAR_NOBTICK
    :   ~('\n' | '`')
    ;

fragment
UNICODE_LETTER_CHAR
    :   'a'..'z' | 'A'..'Z'
    ;

fragment
UNICODE_DIGIT_CHAR
    :   '0'..'9'
    ;

fragment
LETTER_CHAR
    :   UNICODE_LETTER_CHAR | '_'
    ;

fragment
DECIMAL_DIGIT_CHAR
    :   '0'..'9'
    ;

fragment
OCTAL_DIGIT_CHAR
    :   '0'..'7'
    ;

fragment
HEX_DIGIT_CHAR
    :   '0'..'9' | 'a'..'f' | 'A'..'F'
    ;

ANYCHAR
    :   .
    ;
