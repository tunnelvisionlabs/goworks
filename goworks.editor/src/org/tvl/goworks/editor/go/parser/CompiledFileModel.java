/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;

/**
 *
 * @author Sam Harwell
 */
public class CompiledFileModel {

    @NonNull
    private final FileObject fileObject;
    private final Token[] tokens;

    private final sourceFileContext result;
    private final List<? extends SyntaxError> syntaxErrors;

    public CompiledFileModel(@NonNull FileObject fileObject) {
        Parameters.notNull("fileObject", fileObject);

        this.fileObject = fileObject;
        this.tokens = null;
        this.result = null;
        this.syntaxErrors = null;
    }

    public CompiledFileModel(sourceFileContext result, List<? extends SyntaxError> syntaxErrors, @NonNull FileObject fileObject, @NullAllowed Token[] tokens) {
        Parameters.notNull("fileObject", fileObject);

        this.fileObject = fileObject;
        this.tokens = tokens;
        this.result = result;
        this.syntaxErrors = syntaxErrors;
    }

    public @NonNull FileObject getFileObject() {
        return fileObject;
    }

    public @CheckForNull Token[] getTokens() {
        return tokens;
    }

    public sourceFileContext getResult() {
        return result;
    }

    public List<? extends SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

}
