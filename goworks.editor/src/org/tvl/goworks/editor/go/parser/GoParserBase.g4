/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
parser grammar GoParserBase;

options {
    tokenVocab=GoLexerBase;
}

@header {
/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.tvl.goworks.editor.go.parser;
}

type
    :   typeName
    |   typeLiteral
    |   '(' type ')'
    ;

typeName
    :   qid=qualifiedIdentifier
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
    :   '[' len=arrayLength ']' elemType=elementType
    ;

arrayLength
    :   expr=expression
    ;

elementType
    :   typ=type
    ;

sliceType
    :   '[' ']' elemType=elementType
    ;

structType
    :   'struct' '{' (fields+=fieldDecl ';')* fields+=fieldDecl? '}'
    ;

fieldDecl
    :   (idList=identifierList fieldType=type | anonField=anonymousField) fieldTag=tag?
    ;

anonymousField
    :   ptr='*'? fieldType=typeName
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
    :   idList=identifierList? '...'? type
    ;

interfaceType
    :   'interface' '{' (methodSpec (';' methodSpec)* ';'?)? '}'
    ;

methodSpec
    :   name=methodName sig=signature
    |   interfaceTypeName
    ;

methodName
    :   name=IDENTIFIER
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
    :   '{' (statement (';' statement)* ';'?)? '}'
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
        |   '(' (constSpec (';' constSpec)* ';'?)? ')'
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
        |   '(' (typeSpec (';' typeSpec)* ';'?)? ')'
        )
    ;

typeSpec
    :   name=IDENTIFIER type
    ;

varDecl
    :   'var'
        (   varSpec
        |   '(' (varSpec (';' varSpec)* ';'?)? ')'
        )
    ;

varSpec
    :   idList=identifierList
        (   varType=type ('=' exprList=expressionList)?
        |   '=' exprList=expressionList
        )
    ;

shortVarDecl
    :   idList=identifierList ':=' exprList=expressionList
    ;

functionDecl
    :   'func' name=IDENTIFIER sig=signature bdy=body?
    ;

body
    :   block
    ;

methodDecl
    :   'func' recv=receiver name=methodName sig=signature bdy=body?
    ;

receiver
    :   '(' name=IDENTIFIER? '*'? typ=baseTypeName ')'
    ;

baseTypeName
    :   name=IDENTIFIER
    ;

operand
    :   lit=literal
    |   qid=qualifiedIdentifier
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
    :   (pkg=packageName '.')? id=IDENTIFIER
    ;

methodExpr
    :   recvType=receiverType '.' name=methodName
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
    :   typ=functionType body
    ;

expression
    :   operand
    |   conversion
    |   builtinCall
    |   expression '.' IDENTIFIER
        -> selectorExpr
    |   expression '[' expression ']'
        -> indexExpr
    |   expression '[' expression? ':' expression? ']'
        -> sliceExpr
    |   expression '.' '(' type ')'
        -> typeAssertionExpr
    |   expression '(' (argumentList ','?)? ')'
        -> callExpr

    |   ('+' | '-' | '!' | '^' | '*' | '&' | '<-') expression
        -> unaryExpr

    |   expression ('*' | '/' | '%' | '<<' | '>>' | '&' | '&^') expression
        -> multExpr
    |   expression ('+' | '-' | '|' | '^') expression
        -> addExpr
    |   expression ('==' | '!=' | '<' | '<=' | '>' | '>=') expression
        -> compareExpr
    |   expression '&&' expression
        -> andExpr
    |   expression '||' expression
        -> orExpr
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
    :   lbl=label ':' stmt=statement
    ;

label
    :   name=IDENTIFIER
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
    :   exprSwitchCase ':' (statement (';' statement)* ';'?)?
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
    :   typeSwitchCase ':' (statement (';' statement)* ';'?)?
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
    :   commCase ':' (statement (';' statement)* ';'?)?
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
    :   name=IDENTIFIER '(' (args=builtinArgs ','?)? ')'
    ;

builtinArgs
    :   typeArg=type (',' args=expressionList)?
    |   args=expressionList
    ;

sourceFile
    :   pkg=packageClause ';' (importDecls+=importDecl ';')* (decls+=topLevelDecl ';')*
    ;

packageClause
    :   'package' name=packageName
    ;

packageName
    :   name=IDENTIFIER
    ;

importDecl
    :   'import' (importSpecs+=importSpec | '(' (importSpecs+=importSpec (';' importSpecs+=importSpec)* ';'?)? ')')
    ;

importSpec
    :   (dot='.' | name=packageName)? path=importPath
    ;

importPath
    :   path=StringLiteral
    ;
