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
package org.tvl.goworks.editor.go.completion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.tvl.goworks.editor.go.parser.GoLexerBase;

/**
 *
 * @author Sam Harwell
 */
public class KeywordCompletionItem extends GoCompletionItem {
    public static final Collection<Integer> KEYWORD_TYPES =
        new ArrayList<Integer>() {{
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
            Collections.sort(this);
        }};

    public static final Collection<String> KEYWORDS =
        new ArrayList<String>() {{
            for (int i : KEYWORD_TYPES) {
                add(GoLexerBase.tokenNames[i]);
            }

            Collections.sort(this);
        }};

    public static final Collection<KeywordCompletionItem> KEYWORD_ITEMS =
        new ArrayList<KeywordCompletionItem>() {{
            for (String keyword : KEYWORDS) {
                add(new KeywordCompletionItem(keyword));
            }
        }};

    private final String keyword;
    private String leftText;

    public KeywordCompletionItem(String keyword) {
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
