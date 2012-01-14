parser grammar GoParserBase;

options {
    tokenVocab=GoLexerBase;
}

@header {
package org.tvl.goworks.editor.go.parser;
}

type
    :   typeName
    |   typeLiteral
    |   '(' type ')'
    ;

typeName
    :   qualifiedIdentifier
    ;

typeLiteral
    :   arrayType
    |   structType
    |   pointerType
    |   functionType
    |   interfaceType
    |   sliceType
    |   mapType
    |   channelType
    ;

arrayType
    :   '[' arrayLength ']' elementType
    ;

arrayLength
    :   expression
    ;

elementType
    :   type
    ;

sliceType
    :   '[' ']' elementType
    ;

structType
    :   'struct' '{' (fieldDecl ';')* '}'
    ;

fieldDecl
    :   (identifierList type | anonymousField) tag?
    ;

anonymousField
    :   '*'? typeName
    ;

tag
    :   StringLiteral
    ;

pointerType
    :   '*' baseType
    ;

baseType
    :   type
    ;

functionType
    :   'func' signature
    ;

signature
    :   parameters result?
    ;

result
    :   parameters
    |   type
    ;

parameters
    :   '(' (parameterList ','?)? ')'
    ;

parameterList
    :   parameterDecl (',' parameterDecl)*
    ;

parameterDecl
    :   identifierList? '...'? type
    ;

interfaceType
    :   'interface' '{' (methodSpec ';')* '}'
    ;

methodSpec
    :   methodName signature
    |   interfaceTypeName
    ;

methodName
    :   IDENTIFIER
    ;

interfaceTypeName
    :   typeName
    ;

mapType
    :   'map' '[' keyType ']' elementType
    ;

keyType
    :   type
    ;

channelType
    :   (   'chan' '<-'?
        |   '<-' 'chan'
        )
        elementType
    ;

block
    :   '{' (statement ';')* '}'
    ;

declaration
    :   constDecl
    |   typeDecl
    |   varDecl
    ;

topLevelDecl
    :   declaration
    |   functionDecl
    |   methodDecl
    ;

constDecl
    :   'const'
        (   constSpec
        |   '(' (constSpec ';')* ')'
        )
    ;

constSpec
    :   idList=identifierList (explicitType=type? '=' valueList=expressionList)?
    ;

identifierList
    :   ids+=IDENTIFIER (',' ids+=IDENTIFIER)*
    ;

expressionList
    :   expressions+=expression (',' expressions+=expression)*
    ;

typeDecl
    :   'type'
        (   typeSpec
        |   '(' (typeSpec ';')* ')'
        )
    ;

typeSpec
    :   IDENTIFIER type
    ;

varDecl
    :   'var'
        (   varSpec
        |   '(' (varSpec ';')* ')'
        )
    ;

varSpec
    :   identifierList
        (   type ('=' expressionList)?
        |   '=' expressionList
        )
    ;

shortVarDecl
    :   identifierList ':=' expressionList
    ;

functionDecl
    :   'func' IDENTIFIER signature body?
    ;

body
    :   block
    ;

methodDecl
    :   'func' receiver methodName signature body?
    ;

receiver
    :   '(' IDENTIFIER? '*'? baseTypeName ')'
    ;

baseTypeName
    :   IDENTIFIER
    ;

operand
    :   literal
    |   qualifiedIdentifier
    |   methodExpr
    |   '(' expression ')'
    ;

literal
    :   basicLiteral
    |   compositeLiteral
    |   functionLiteral
    ;

basicLiteral
    :   INT_LITERAL
    |   FLOAT_LITERAL
    |   IMAGINARY_LITERAL
    |   CharLiteral
    |   StringLiteral
    ;

qualifiedIdentifier
    :   (packageName '.')? IDENTIFIER
    ;

methodExpr
    :   receiverType '.' methodName
    ;

receiverType
    :   typeName
    |   '(' '*' typeName ')'
    ;

compositeLiteral
    :   literalType literalValue
    ;

literalType
    :   structType
    |   arrayType
    |   '[' '...' ']' elementType
    |   sliceType
    |   mapType
    |   typeName
    ;

literalValue
    :   '{' (elementList ','?)? '}'
    ;

elementList
    :   element (',' element)*
    ;

element
    :   (key ':')? value
    ;

key
    :   fieldName
    |   elementIndex
    ;

fieldName
    :   IDENTIFIER
    ;

elementIndex
    :   expression
    ;

value
    :   expression
    |   literalValue
    ;

functionLiteral
    :   functionType body
    ;

expression
    :   operand
    |   conversion
    |   builtinCall
    |   expression '.' IDENTIFIER // -> selector
    |   expression '[' expression ']' // -> index
    |   expression '[' expression? ':' expression? ']' // -> slice
    |   expression '.' '(' type ')' // -> typeAssertion
    |   expression '(' (argumentList ','?)? ')' // -> call

    |   expression '||' expression
    |   expression '&&' expression

    |   expression '==' expression
    |   expression '!=' expression
    |   expression '<' expression
    |   expression '<=' expression
    |   expression '>' expression
    |   expression '>=' expression

    |   expression '+' expression
    |   expression '-' expression
    |   expression '|' expression
    |   expression '^' expression

    |   expression '*' expression
    |   expression '/' expression
    |   expression '^' expression
    |   expression '<<' expression
    |   expression '>>' expression
    |   expression '&' expression
    |   expression '&^' expression

    |   '+' expression
    |   '-' expression
    |   '!' expression
    |   '^' expression
    |   '*' expression
    |   '&' expression
    |   '<-' expression
    ;

primaryExpr
    :   operand
    |   conversion
    |   builtinCall
    |   primaryExpr '.' IDENTIFIER // -> selector
    |   primaryExpr '[' expression ']' // -> index
    |   primaryExpr '[' expression? ':' expression? ']' // -> slice
    |   primaryExpr '.' '(' type ')' // -> typeAssertion
    |   primaryExpr '(' (argumentList ','?)? ')' // -> call
    ;

argumentList
    :   expressionList '...'?
    ;

conversion
    :   type '(' expression ')'
    ;

statement
    :   declaration
    |   labeledStmt
    |   simpleStmt
    |   goStmt
    |   returnStmt
    |   breakStmt
    |   continueStmt
    |   gotoStmt
    |   fallthroughStmt
    |   block
    |   ifStmt
    |   switchStmt
    |   selectStmt
    |   forStmt
    |   deferStmt
    ;

simpleStmt
    :   emptyStmt
    |   expressionStmt
    |   sendStmt
    |   incDecStmt
    |   assignment
    |   shortVarDecl
    ;

emptyStmt
    :
    ;

labeledStmt
    :   label ':' statement
    ;

label
    :   IDENTIFIER
    ;

expressionStmt
    :   expression
    ;

sendStmt
    :   channel '<-' expression
    ;

channel
    :   expression
    ;

incDecStmt
    :   expression ('++' | '--')
    ;

assignment
    :   expressionList assignOp expressionList
    ;

assignOp
    :   addAssignOp
    |   mulAssignOp
    |   '='
    ;

addAssignOp
    :   '+=' | '-=' | '|=' | '^='
    ;

mulAssignOp
    :   '*=' | '/=' | '^=' | '<<=' | '>>=' | '&=' | '&^='
    ;

ifStmt
    :   'if' (simpleStmt ';')? expression block ('else' (ifStmt | block))?
    ;

switchStmt
    :   exprSwitchStmt
    |   typeSwitchStmt
    ;

exprSwitchStmt
    :   'switch' (simpleStmt ';')? expression? '{' exprCaseClause* '}'
    ;

exprCaseClause
    :   exprSwitchCase ':' (statement ';')*
    ;

exprSwitchCase
    :   'case' expressionList
    |   'default'
    ;

typeSwitchStmt
    :   'switch' (simpleStmt ';')? typeSwitchGuard '{' typeCaseClause* '}'
    ;

typeSwitchGuard
    :   (IDENTIFIER ':=')? primaryExpr '.' '(' 'type' ')'
    ;

typeCaseClause
    :   typeSwitchCase ':' (statement ';')*
    ;

typeSwitchCase
    :   'case' typeList
    |   'default'
    ;

typeList
    :   type (',' type)*
    ;

forStmt
    :   'for' (condition | forClause | rangeClause)? block
    ;

condition
    :   expression
    ;

forClause
    :   initStmt? ';' condition? ';' postStmt?
    ;

initStmt
    :   simpleStmt
    ;

postStmt
    :   simpleStmt
    ;

rangeClause
    :   expression (',' expression)? ('=' | ':=') 'range' expression
    ;

goStmt
    :   'go' expression
    ;

selectStmt
    :   'select' '{' commClause* '}'
    ;

commClause
    :   commCase ':' (statement ';')*
    ;

commCase
    :   'case' (sendStmt | recvStmt)
    |   'default'
    ;

recvStmt
    :   (expression (',' expression)? ('=' | ':='))? recvExpr
    ;

recvExpr
    :   expression
    ;

returnStmt
    :   'return' expressionList?
    ;

breakStmt
    :   'break' label?
    ;

continueStmt
    :   'continue' label?
    ;

gotoStmt
    :   'goto' label
    ;

fallthroughStmt
    :   'fallthrough'
    ;

deferStmt
    :   'defer' expression
    ;

builtinCall
    :   IDENTIFIER '(' (builtinArgs ','?)? ')'
    ;

builtinArgs
    :   type (',' expressionList)?
    |   expressionList
    ;

sourceFile
    :   packageClause ';' (importDecl ';')* (topLevelDecl ';')*
    ;

packageClause
    :   'package' packageName
    ;

packageName
    :   IDENTIFIER
    ;

importDecl
    :   'import' (importSpec | '(' (importSpec ';')* ')')
    ;

importSpec
    :   ('.' | packageName)? importPath
    ;

importPath
    :   StringLiteral
    ;
