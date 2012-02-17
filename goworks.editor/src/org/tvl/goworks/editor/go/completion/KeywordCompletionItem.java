/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.ImageIcon;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.ImageUtilities;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.parser.GoLexerBase;

/**
 *
 * @author Sam Harwell
 */
public class KeywordCompletionItem extends GoCompletionItem {
    public static final IntervalSet KEYWORD_TYPES =
        new IntervalSet() {{
            add(GoLexerBase.Break);
            add(GoLexerBase.Case);
            add(GoLexerBase.Chan);
            add(GoLexerBase.Const);
            add(GoLexerBase.Continue);
            add(GoLexerBase.Default);
            add(GoLexerBase.Defer);
            add(GoLexerBase.Else);
            add(GoLexerBase.Fallthrough);
            add(GoLexerBase.For);
            add(GoLexerBase.Func);
            add(GoLexerBase.Go);
            add(GoLexerBase.Goto);
            add(GoLexerBase.If);
            add(GoLexerBase.Import);
            add(GoLexerBase.Interface);
            add(GoLexerBase.Map);
            add(GoLexerBase.Package);
            add(GoLexerBase.Range);
            add(GoLexerBase.Return);
            add(GoLexerBase.Select);
            add(GoLexerBase.Struct);
            add(GoLexerBase.Switch);
            add(GoLexerBase.Type);
            add(GoLexerBase.Var);
        }};

    public static final Collection<String> KEYWORDS =
        new ArrayList<String>() {{
            for (int i : KEYWORD_TYPES.toArray()) {
                add(GoLexerBase.tokenNames[i].substring(1, GoLexerBase.tokenNames[i].length() - 1));
            }

            Collections.sort(this);
        }};

    public static final Collection<KeywordCompletionItem> KEYWORD_ITEMS =
        new ArrayList<KeywordCompletionItem>() {{
            for (String keyword : KEYWORDS) {
                add(new KeywordCompletionItem(keyword));
            }
        }};

    private static ImageIcon ICON;
    private final String keyword;
    private String leftText;

    public KeywordCompletionItem(@NonNull String keyword) {
        Parameters.notNull("keyword", keyword);
        this.keyword = keyword;
    }

    @Override
    public int getSortPriority() {
        return KEYWORD_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return keyword;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return keyword;
    }

    @Override
    protected ImageIcon getIcon() {
        if (ICON == null) {
            ICON = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/duke.png"));
        }

        return ICON;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(KEYWORD_COLOR);
            builder.append(BOLD);
            builder.append(keyword);
            builder.append(BOLD_END);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

}
