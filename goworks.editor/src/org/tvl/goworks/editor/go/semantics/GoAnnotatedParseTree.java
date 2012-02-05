/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import org.antlr.netbeans.semantics.ObjectProperty;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;

/**
 *
 * @author Sam Harwell
 */
public class GoAnnotatedParseTree extends AnnotatedParseTree {

    private static final ObjectProperty<CodeElementReference> PROP_ELEMENT_REFERENCE =
        new ObjectProperty<CodeElementReference>("element-reference", CodeElementReference.MISSING);

    private final CodeElementModel context;

    public GoAnnotatedParseTree(@NonNull CodeElementModel context, @NonNull ParserRuleContext<Token> parseTree) {
        super(parseTree);
        Parameters.notNull("context", context);

        this.context = context;
    }

    @NonNull
    public CodeElementModel getContext() {
        return context;
    }

    @NonNull
    public CodeElementReference getTarget(ParserRuleContext<Token> parseTree) {
        return getAnnotations().getProperty(parseTree, PROP_ELEMENT_REFERENCE);
    }

}
