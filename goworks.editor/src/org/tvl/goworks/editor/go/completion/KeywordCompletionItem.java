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

/**
 *
 * @author Sam Harwell
 */
public class KeywordCompletionItem extends GoCompletionItem {

    public static final Collection<KeywordCompletionItem> KEYWORD_ITEMS =
        new ArrayList<KeywordCompletionItem>() {{
            add(new KeywordCompletionItem("break"));
            add(new KeywordCompletionItem("case"));
            add(new KeywordCompletionItem("chan"));
            add(new KeywordCompletionItem("const"));
            add(new KeywordCompletionItem("continue"));
            add(new KeywordCompletionItem("default"));
            add(new KeywordCompletionItem("defer"));
            add(new KeywordCompletionItem("else"));
            add(new KeywordCompletionItem("fallthrough"));
            add(new KeywordCompletionItem("for"));
            add(new KeywordCompletionItem("func"));
            add(new KeywordCompletionItem("go"));
            add(new KeywordCompletionItem("goto"));
            add(new KeywordCompletionItem("if"));
            add(new KeywordCompletionItem("import"));
            add(new KeywordCompletionItem("interface"));
            add(new KeywordCompletionItem("map"));
            add(new KeywordCompletionItem("package"));
            add(new KeywordCompletionItem("range"));
            add(new KeywordCompletionItem("return"));
            add(new KeywordCompletionItem("select"));
            add(new KeywordCompletionItem("struct"));
            add(new KeywordCompletionItem("switch"));
            add(new KeywordCompletionItem("type"));
            add(new KeywordCompletionItem("var"));
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
