/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go;

import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.lib.editor.codetemplates.spi.CodeTemplateInsertRequest;
import org.netbeans.lib.editor.codetemplates.spi.CodeTemplateParameter;
import org.netbeans.lib.editor.codetemplates.spi.CodeTemplateProcessor;
import org.netbeans.lib.editor.codetemplates.spi.CodeTemplateProcessorFactory;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public class GoCodeTemplateProcessor implements CodeTemplateProcessor {

    private final CodeTemplateInsertRequest _request;

    private GoCodeTemplateProcessor(CodeTemplateInsertRequest request) {
        this._request = request;
    }

    @Override
    public void updateDefaultValues() {
    }

    @Override
    public void parameterValueChanged(CodeTemplateParameter masterParameter, boolean typingChange) {
    }

    @Override
    public void release() {
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=CodeTemplateProcessorFactory.class)
    public static final class Factory implements CodeTemplateProcessorFactory {

        @Override
        public CodeTemplateProcessor createProcessor(CodeTemplateInsertRequest request) {
            return new GoCodeTemplateProcessor(request);
        }

    }

}
