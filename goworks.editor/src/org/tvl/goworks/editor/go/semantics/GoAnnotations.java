/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.Collection;
import org.antlr.netbeans.semantics.ObjectProperty;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.VarKind;

/**
 *
 * @author Sam Harwell
 */
public class GoAnnotations {
    public static final ObjectProperty<Boolean> ATTR_LITERAL = new ObjectProperty<Boolean>("literal", false);
    public static final ObjectProperty<Boolean> BUILTIN = new ObjectProperty<Boolean>("builtin", false);
    public static final ObjectProperty<NodeType> NODE_TYPE = new ObjectProperty<NodeType>("nodetype", NodeType.UNDEFINED);
    public static final ObjectProperty<VarKind> VAR_TYPE = new ObjectProperty<VarKind>("vartype", VarKind.UNDEFINED);
    public static final ObjectProperty<TypeKind> TYPE_KIND = new ObjectProperty<TypeKind>("typekind", TypeKind.UNDEFINED);
    public static final ObjectProperty<Boolean> GLOBAL = new ObjectProperty<Boolean>("global", false);
    public static final ObjectProperty<TerminalNode<Token>> LOCAL_TARGET = new ObjectProperty<TerminalNode<Token>>("local-target");
    public static final ObjectProperty<Boolean> RESOLVED = new ObjectProperty<Boolean>("resolved", false);
    public static final ObjectProperty<Boolean> QUALIFIED_EXPR = new ObjectProperty<Boolean>("qualified-expr", false);
    public static final ObjectProperty<ParserRuleContext<Token>> QUALIFIER = new ObjectProperty<ParserRuleContext<Token>>("qualifier");
    public static final ObjectProperty<TerminalNode<Token>> UNQUALIFIED_LINK = new ObjectProperty<TerminalNode<Token>>("unqualified-link");
    public static final ObjectProperty<Collection<? extends CodeElementModel>> MODELS = new ObjectProperty<Collection<? extends CodeElementModel>>("models");
    public static final ObjectProperty<Boolean> POINTER_RECEIVER = new ObjectProperty<Boolean>("pointer-receiver", false);
    public static final ObjectProperty<Boolean> VARIADIC = new ObjectProperty<Boolean>("variadic", false);

    public static final ObjectProperty<ParserRuleContext<Token>> EXPLICIT_TYPE = new ObjectProperty<ParserRuleContext<Token>>("explicit-type");
    public static final ObjectProperty<ParserRuleContext<Token>> IMPLICIT_TYPE = new ObjectProperty<ParserRuleContext<Token>>("implicit-type");
    public static final ObjectProperty<Integer> IMPLICIT_INDEX = new ObjectProperty<Integer>("implicit-index", -1);

    public static final ObjectProperty<String> UNEVALUATED_CONSTANT = new ObjectProperty<String>("unevaluated-constant");
    public static final ObjectProperty<Object> EVALUATED_CONSTANT = new ObjectProperty<Object>("evaluated-constant");

    public static final ObjectProperty<CodeElementReference> EXPR_TYPE = new ObjectProperty<CodeElementReference>("expression-type", CodeElementReference.MISSING);
    public static final ObjectProperty<CodeElementReference> CODE_CLASS = new ObjectProperty<CodeElementReference>("code-type", CodeElementReference.MISSING);

    public static final ObjectProperty<CodeElementReference> PROP_ELEMENT_REFERENCE =
        new ObjectProperty<CodeElementReference>("element-reference");
}
