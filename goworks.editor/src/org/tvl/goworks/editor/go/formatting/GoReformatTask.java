/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import javax.swing.text.BadLocationException;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.ReformatTask;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public class GoReformatTask implements ReformatTask {
    private final Context context;

    private GoReformatTask(Context context) {
        this.context = context;
    }

    public static String reformat(String text, CodeStyle style) {
        return reformat(text, style, style.getRightMargin());
    }

    public static String reformat(String text, CodeStyle style, int rightMargin) {
        return text;
    }

    @Override
    public void reformat() throws BadLocationException {
    }

    @Override
    public ExtraLock reformatLock() {
        return null;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ReformatTask.Factory.class)
    public static final class FactoryImpl implements Factory {

        @Override
        public ReformatTask createTask(Context context) {
            return new GoReformatTask(context);
        }

    }

}
