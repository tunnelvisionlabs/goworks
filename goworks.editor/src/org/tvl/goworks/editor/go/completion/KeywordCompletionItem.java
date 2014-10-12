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
    public static final IntervalSet KEYWORD_TYPES = new IntervalSet();
    static {
        KEYWORD_TYPES.add(GoLexer.Break);
        KEYWORD_TYPES.add(GoLexer.Case);
        KEYWORD_TYPES.add(GoLexer.Chan);
        KEYWORD_TYPES.add(GoLexer.Const);
        KEYWORD_TYPES.add(GoLexer.Continue);
        KEYWORD_TYPES.add(GoLexer.Default);
        KEYWORD_TYPES.add(GoLexer.Defer);
        KEYWORD_TYPES.add(GoLexer.Else);
        KEYWORD_TYPES.add(GoLexer.Fallthrough);
        KEYWORD_TYPES.add(GoLexer.For);
        KEYWORD_TYPES.add(GoLexer.Func);
        KEYWORD_TYPES.add(GoLexer.Go);
        KEYWORD_TYPES.add(GoLexer.Goto);
        KEYWORD_TYPES.add(GoLexer.If);
        KEYWORD_TYPES.add(GoLexer.Import);
        KEYWORD_TYPES.add(GoLexer.Interface);
        KEYWORD_TYPES.add(GoLexer.Map);
        KEYWORD_TYPES.add(GoLexer.Package);
        KEYWORD_TYPES.add(GoLexer.Range);
        KEYWORD_TYPES.add(GoLexer.Return);
        KEYWORD_TYPES.add(GoLexer.Select);
        KEYWORD_TYPES.add(GoLexer.Struct);
        KEYWORD_TYPES.add(GoLexer.Switch);
        KEYWORD_TYPES.add(GoLexer.Type);
        KEYWORD_TYPES.add(GoLexer.Var);
    }

    public static final Collection<String> KEYWORDS;
    static {
        ArrayList<String> keywords = new ArrayList<>();
        for (int i : KEYWORD_TYPES.toArray()) {
            String literal = GoLexer.VOCABULARY.getLiteralName(i);
            if (literal == null) {
                continue;
            }

            String keyword = literal.substring(1, literal.length() - 1);
            keywords.add(keyword);
        }

        Collections.sort(keywords);
        KEYWORDS = keywords;
    }

    public static final Collection<KeywordCompletionItem> KEYWORD_ITEMS;
    static {
        ArrayList<KeywordCompletionItem> keywordItems = new ArrayList<>();
        for (String keyword : KEYWORDS) {
            keywordItems.add(new KeywordCompletionItem(keyword));
        }

        KEYWORD_ITEMS = keywordItems;
    }

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
