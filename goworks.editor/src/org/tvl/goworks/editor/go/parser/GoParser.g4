/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
parser grammar GoParser;

options {
    tokenVocab=GoLexer;
    abstract=true;
}

@header {/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.HashSet;
import java.util.Set;
}

@members {
private final Set<String> packageNames = new HashSet<String>();
private boolean checkPackageNames = true;

public static String getPackageName(Token token) {
    if (token == null) {
        return null;
    }

    String name = token.getText();
    if (name != null && token.getType() == StringLiteral) {
        name = name.substring(name.lastIndexOf('/') + 1);
        if (name.startsWith("\"")) {
            name = name.substring(1);
        }

        if (name.endsWith("\"")) {
            name = name.substring(0, name.length() - 1);
        }
    }

    return name;
}

protected void addPackageName(Token token) {
    String name = getPackageName(token);
    if (name == null || name.isEmpty()) {
        return;
    }

    packageNames.add(name);
}

protected boolean isPackageName(Token token) {
    if (!checkPackageNames) {
        return true;
    }

    return token != null && packageNames.contains(token.getText());
}

public boolean isCheckPackageNames() {
    return checkPackageNames;
}

public void setCheckPackageNames(boolean checkPackageNames) {
    this.checkPackageNames = checkPackageNames;
}
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
    :   'struct' '{' (fieldDecl ';')* fieldDecl? '}'
    ;

fieldDecl
    :   (identifierList type | anonymousField) tag?
    ;

anonymousField
    :   ptr='*'? typeName
    ;

tag
    :   StringLiteral
    ;

pointerType
    :   ptr='*' baseType
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
    :   identifierList? ellip='...'? type
    ;

interfaceType
    :   'interface' '{' (methodSpec (';' methodSpec)* ';'?)? '}'
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
    :   (   'chan' send='<-'?
        |   recv='<-' 'chan'
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
    :   identifierList (type? '=' expressionList)?
    ;

identifierList
    :   IDENTIFIER (',' IDENTIFIER)*
    ;

expressionList
    :   expression (',' expression)*
    ;

typeDecl
    :   'type'
        (   typeSpec
        |   '(' (typeSpec (';' typeSpec)* ';'?)? ')'
        )
    ;

typeSpec
    :   IDENTIFIER type
    ;

varDecl
    :   'var'
        (   varSpec
        |   '(' (varSpec (';' varSpec)* ';'?)? ')'
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
    :   '(' IDENTIFIER? ptr='*'? baseTypeName ')'
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
    :   ({isPackageName(_input.LT(1))}? packageName dot='.')? IDENTIFIER
    ;

methodExpr
    :   receiverType dot='.' methodName
    ;

receiverType
    :   typeName
    |   '(' ptr='*' typeName ')'
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
        # operandExpr
    |   conversion
        # conversionOrCallExpr
    |   builtinCall
        # builtinCallExpr
    |   expression dot='.' IDENTIFIER
        # selectorExpr
    |   expression '[' expression ']'
        # indexExpr
    |   expression '[' from=expression? ':' to=expression? ']'
        # sliceExpr
    |   expression dot='.' lp='(' type rp=')'
        # typeAssertionExpr
    |   expression lp='(' (argumentList ','?)? rp=')'
        # callExpr

    |   (op='+' | op='-' | op='!' | op='^' | op='*' | op='&' | op='<-') expression
        # unaryExpr

    |   expression (op='*' | op='/' | op='%' | op='<<' | op='>>' | op='&' | op='&^') expression
        # multExpr
    |   expression (op='+' | op='-' | op='|' | op='^') expression
        # addExpr
    |   expression (op='==' | op='!=' | op='<' | op='<=' | op='>' | op='>=') expression
        # compareExpr
    |   expression '&&' expression
        # andExpr
    |   expression '||' expression
        # orExpr
    ;

//primaryExpr
//    :   operand
//    |   conversion
//    |   builtinCall
//    |   primaryExpr '.' IDENTIFIER // -> selector
//    |   primaryExpr '[' expression ']' // -> index
//    |   primaryExpr '[' expression? ':' expression? ']' // -> slice
//    |   primaryExpr '.' '(' type ')' // -> typeAssertion
//    |   primaryExpr '(' (argumentList ','?)? ')' // -> call
//    ;

argumentList
    :   expressionList ellip='...'?
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
    :   expression op=('++' | '--')
    ;

assignment
    :   targets=expressionList assignOp values=expressionList
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
    :   '*=' | '/=' | '^=' | '<<=' | '>>=' | '&=' | '&^=' | '%='
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

// this should reference primaryExpr, but the ".(type)" extension is unambig
typeSwitchGuard
    :   (IDENTIFIER defeq=':=')? expression dot='.' '(' 'type' ')'
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
    :   e1=expression (',' e2=expression)? (eq='=' | defeq=':=') 'range' e=expression
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
    :   (e1=expression (',' e2=expression)? (eq='=' | defeq=':='))? recvExpr
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
    :   (packageClause ';')? (importDecl ';')* (topLevelDecl ';')*
    ;

packageClause
    :   'package' packageName                   {addPackageName($packageName.start);}
    ;

packageName
    :   IDENTIFIER
    ;

importDecl
    :   'import' (importSpec | '(' (importSpec (';' importSpec)* ';'?)? ')')
    ;

importSpec
    :   dot='.' importPath
    |   packageName importPath              {addPackageName($packageName.start);}
    |   importPath                          {addPackageName($importPath.start);}
    ;

importPath
    :   StringLiteral
    ;
