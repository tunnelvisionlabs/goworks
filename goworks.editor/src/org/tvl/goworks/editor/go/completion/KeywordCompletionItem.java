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
import org.tvl.goworks.editor.go.parser.GoLexer;

/**
 *
 * @author Sam Harwell
 */
public class KeywordCompletionItem extends GoCompletionItem {
    public static final IntervalSet KEYWORD_TYPES =
        new IntervalSet() {{
            add(GoLexer.Break);
            add(GoLexer.Case);
            add(GoLexer.Chan);
            add(GoLexer.Const);
            add(GoLexer.Continue);
            add(GoLexer.Default);
            add(GoLexer.Defer);
            add(GoLexer.Else);
            add(GoLexer.Fallthrough);
            add(GoLexer.For);
            add(GoLexer.Func);
            add(GoLexer.Go);
            add(GoLexer.Goto);
            add(GoLexer.If);
            add(GoLexer.Import);
            add(GoLexer.Interface);
            add(GoLexer.Map);
            add(GoLexer.Package);
            add(GoLexer.Range);
            add(GoLexer.Return);
            add(GoLexer.Select);
            add(GoLexer.Struct);
            add(GoLexer.Switch);
            add(GoLexer.Type);
            add(GoLexer.Var);
        }};

    public static final Collection<String> KEYWORDS =
        new ArrayList<String>() {{
            for (int i : KEYWORD_TYPES.toArray()) {
                add(GoLexer.tokenNames[i].substring(1, GoLexer.tokenNames[i].length() - 1));
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
    public String getToolTipText() {
        return "";
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
