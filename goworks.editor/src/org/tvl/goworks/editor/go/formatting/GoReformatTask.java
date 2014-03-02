/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.util.NotificationIcons;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.ReformatTask;
import org.openide.awt.NotificationDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Utilities;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public class GoReformatTask implements ReformatTask {
    // -J-Dorg.tvl.goworks.editor.go.formatting.GoReformatTask.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoReformatTask.class.getName());  

    private final Context context;

    private GoReformatTask(Context context) {
        this.context = context;
    }

    public static String reformat(String text, GoCodeStyle style) {
        return reformat(text, style, style.getTextLimitWidth());
    }

    public static String reformat(String text, GoCodeStyle style, int rightMargin) {
        return runGofmt(text);
    }

    public static String runGofmt(String text) {
        return runGofmt(text, true, 8);
    }

    public static String runGofmt(String text, boolean useTabs, int tabWidth) {
        File goroot = new File(System.getenv("GOROOT"));
        if (!goroot.isDirectory()) {
            displayError("The GOROOT environment variable does not point to an accessible Go installation.");
            throw new UnsupportedOperationException("Couldn't determine GOROOT.");
        }

        FileObject gorootObject = FileUtil.toFileObject(goroot);
        if (gorootObject == null || !gorootObject.isFolder()) {
            throw new UnsupportedOperationException("Couldn't determine GOROOT.");
        }

        FileObject binFolder = gorootObject.getFileObject("bin");
        if (binFolder == null || !binFolder.isFolder()) {
            displayError(String.format("The Go installation directory environment variable does not contain a 'bin' directory. Expected: %s%sbin", gorootObject.getPath(), File.separatorChar));
            throw new UnsupportedOperationException("Couldn't determine Go bin directory.");
        }

        FileObject executable = binFolder.getFileObject("gofmt", "");
        if (executable == null && Utilities.isWindows()) {
            executable = binFolder.getFileObject("gofmt", "exe");
        }

        if (executable == null || !executable.isData()) {
            String extension = Utilities.isWindows() ? ".exe" : "";
            String expected = gorootObject.getPath() + File.separator + "bin" + File.separator + "gofmt" + extension;
            if (File.separatorChar != '/') {
                expected = expected.replace('/', File.separatorChar);
            }

            displayError(String.format("Couldn't find the Go tool. Expected: %s", expected));
            throw new UnsupportedOperationException("Couldn't find the Go tool.");
        }

        List<String> args = new ArrayList<>();
        args.add("-tabs=" + useTabs);
        args.add("-tabwidth=" + tabWidth);

        ExternalProcessBuilder nativeProcessBuilder = new ExternalProcessBuilder(executable.getPath());
        for (String arg : args) {
            nativeProcessBuilder = nativeProcessBuilder.addArgument(arg);
        }

        try {
            Process process = nativeProcessBuilder.call();

            final InputStream inputStream = process.getInputStream();
            final InputStream errorStream = process.getErrorStream();
            try (OutputStream outputStream = process.getOutputStream()) {
                outputStream.write(text.getBytes());
            }

            ExecutorService ioService = Executors.newFixedThreadPool(2);
            Future<String> resultFuture = ioService.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    try {
                        return new java.util.Scanner(inputStream).useDelimiter("\\A").next();
                    } catch (NoSuchElementException ex) {
                        return "";
                    }
                }

            });

            Future<String> errorFuture = ioService.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    try {
                        return new java.util.Scanner(errorStream).useDelimiter("\\A").next();
                    } catch (NoSuchElementException ex) {
                        return "";
                    }
                }

            });

            try {
                String result = resultFuture.get();
                String error = errorFuture.get();
                process.waitFor();
                if (error != null && !error.isEmpty()) {
                    return null;
                }

                return result;
            } catch (ExecutionException | InterruptedException ex) {
                return null;
            }
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, ex.getLocalizedMessage(), ex);
            return null;
        }
    }

    @Override
    public void reformat() throws BadLocationException {
        if (context.indentRegions().size() > 1) {
            throw new UnsupportedOperationException("The reformatter currently only supports one region per call.");
        }

        for (final Context.Region region : context.indentRegions()) {
            final String original = context.document().getText(region.getStartOffset(), region.getEndOffset() - region.getStartOffset());
            if (original == null || original.isEmpty()) {
                continue;
            }

            final String formatted = runGofmt(original);
            if (formatted == null || formatted.equals(original)) {
                continue;
            }

            Runnable applyer = new Runnable() {

                @Override
                public void run() {
                    try {
                        context.document().remove(region.getStartOffset(), region.getEndOffset() - region.getStartOffset());
                        context.document().insertString(region.getStartOffset(), formatted, null);
                    } catch (BadLocationException ex) {
                        LOGGER.log(Level.WARNING, ex.getLocalizedMessage(), ex);
                        throw new RuntimeException(ex);
                    }
                }

            };

            if (context.document() instanceof BaseDocument) {
                ((BaseDocument)context.document()).runAtomicAsUser(applyer);
            } else {
                applyer.run();
            }
        }
    }

    @Override
    public ExtraLock reformatLock() {
        return null;
    }

    private static void displayError(String message) {
        NotificationDisplayer.getDefault().notify("Error executing gofmt", NotificationIcons.ERROR, message, null);
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ReformatTask.Factory.class)
    public static final class FactoryImpl implements Factory {

        @Override
        public ReformatTask createTask(Context context) {
            return new GoReformatTask(context);
        }

    }

}
