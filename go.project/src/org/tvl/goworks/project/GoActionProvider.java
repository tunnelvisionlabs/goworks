/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.antlr.netbeans.util.NotificationIcons;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.print.ConvertedLine;
import org.netbeans.api.extexecution.print.LineConvertor;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.modules.nativeexecution.api.ExecutionEnvironment;
import org.netbeans.modules.nativeexecution.api.ExecutionListener;
import org.netbeans.modules.nativeexecution.api.NativeProcess;
import org.netbeans.modules.nativeexecution.api.NativeProcessBuilder;
import org.netbeans.modules.nativeexecution.api.NativeProcessChangeEvent;
import org.netbeans.modules.nativeexecution.api.execution.NativeExecutionDescriptor;
import org.netbeans.modules.nativeexecution.api.execution.NativeExecutionService;
import org.netbeans.modules.nativeexecution.api.execution.PostMessageDisplayer;
import org.netbeans.modules.nativeexecution.spi.ExecutionEnvironmentFactoryService;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.openide.awt.NotificationDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;
import org.openide.util.Cancellable;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NotImplementedException;
import org.openide.util.Parameters;
import org.openide.util.RequestProcessor;
import org.openide.util.Utilities;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputEvent;
import org.openide.windows.OutputListener;
import org.tvl.goworks.project.testing.GoTestOutputWriter;

/**
 *
 * @author Sam Harwell
 */
public final class GoActionProvider implements ActionProvider {
    // -J-Dorg.tvl.goworks.project.GoActionProvider.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoActionProvider.class.getName());

    private static final RequestProcessor RP = new RequestProcessor(GoActionProvider.class);

    private static final String[] supported = new String[] {
        ActionProvider.COMMAND_BUILD,
        ActionProvider.COMMAND_COMPILE_SINGLE,
        ActionProvider.COMMAND_REBUILD,
        ActionProvider.COMMAND_CLEAN,
        ActionProvider.COMMAND_RUN,
        ActionProvider.COMMAND_DEBUG,
        ActionProvider.COMMAND_PROFILE,
        ActionProvider.COMMAND_TEST,
        ActionProvider.COMMAND_DELETE,
        ActionProvider.COMMAND_COPY
    };

    private final GoProject _project;

    private final List<ExecutionListener> listeners = new CopyOnWriteArrayList<ExecutionListener>();
    private ProgressHandle progressHandle;

    public GoActionProvider(final GoProject project) {
        this._project = project;
        this.listeners.add(new ExecutionEventListener());
    }

    @Override
    public String[] getSupportedActions() {
        return supported;
    }

    @Override
    public void invokeAction(String string, Lookup lookup) throws IllegalArgumentException {
        if (string.equals(COMMAND_BUILD)) {
            handleBuildAction(lookup);
        }
        else if (string.equals(COMMAND_COMPILE_SINGLE)) {
            handleBuildPackageAction(lookup);
        }
        else if (string.equals(COMMAND_REBUILD)) {
            handleRebuildAction(lookup);
        }
        else if (string.equals(COMMAND_CLEAN)) {
            handleCleanAction(lookup);
        }
        else if (string.equals(COMMAND_RUN)) {
            handleRunAction(lookup);
        }
        else if (string.equals(COMMAND_DEBUG)) {
            handleDebugAction(lookup);
        }
        else if (string.equals(COMMAND_PROFILE)) {
            handleProfileAction(lookup);
        }
        else if (string.equals(COMMAND_TEST)) {
            handleTestAction(lookup);
        }
        else if (string.equalsIgnoreCase(ActionProvider.COMMAND_DELETE)) {
            DefaultProjectOperations.performDefaultDeleteOperation(_project);
        }
        else if (string.equalsIgnoreCase(ActionProvider.COMMAND_COPY)) {
            DefaultProjectOperations.performDefaultCopyOperation(_project);
        }
    }

    private void handleBuildAction(Lookup lookup) throws IllegalArgumentException {
        String buildCommand = "go";
        String args = "build";

        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (build)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
        };

        tab = IOProvider.getDefault().getIO(projectName + " (build)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        progressHandle = ProgressHandleFactory.createHandle(projectName + " (build)", new Cancellable() {

            @Override
            public boolean cancel() {
                return false;
            }

        }, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ioTab.select();
            }

        });

        progressHandle.setInitialDelay(0);
        progressHandle.start();

        execute("build", ioTab);
   }

    private void handleBuildPackageAction(Lookup lookup) throws IllegalArgumentException {
        String buildCommand = "go";
        String args = "build";

        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (compile-single)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
        };

        tab = IOProvider.getDefault().getIO(projectName + " (compile-single)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        progressHandle = ProgressHandleFactory.createHandle(projectName + " (compile-single)", new Cancellable() {

            @Override
            public boolean cancel() {
                return false;
            }

        }, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ioTab.select();
            }

        });

        progressHandle.setInitialDelay(0);
        progressHandle.start();

        DataObject dataFolder = lookup.lookup(DataObject.class);
        if (dataFolder == null) {
            return;
        }

        FileObject fileObject = dataFolder.getPrimaryFile();
        if (fileObject == null || !fileObject.isFolder()) {
            return;
        }

        FileObject sourceRoot = _project.getSourceRoot();
        String relativePath = FileUtil.getRelativePath(sourceRoot, fileObject);
        if (relativePath == null) {
            return;
        }

        execute("build", ioTab, relativePath.replace(File.separatorChar, '/'));
   }

    public void execute(String commandName, InputOutput io) {
        execute(commandName, io, "./...");
    }

    public void execute(final String commandName, final InputOutput io, final String packageName) {
        final ExecutionListener listener = new ExecutionListener() {

            @Override
            public void executionStarted(int pid) {
                for (ExecutionListener listener : listeners) {
                    listener.executionStarted(pid);
                }
            }

            @Override
            public void executionFinished(int rc) {
                for (ExecutionListener listener : listeners) {
                    listener.executionFinished(rc);
                }
            }

        };

        Runnable executor = new Runnable() {

            @Override
            public void run() {
                try {
                    if (COMMAND_REBUILD.equals(commandName)) {
                        Future<Integer> result;
                        result = executeImpl(COMMAND_CLEAN, packageName, io, listener);
                        if (result.get() != 0) {
                            return;
                        }

                        executeImpl(COMMAND_BUILD, packageName, io, listener);
                    } else if (COMMAND_RUN.equals(commandName)) {
                        Future<Integer> result;
                        result = executeImpl(COMMAND_BUILD, packageName, io, listener);
                        if (result.get() != 0) {
                            return;
                        }

                        executeImpl(COMMAND_RUN, packageName, io, listener);
                    } else {
                        executeImpl(commandName, packageName, io, listener);
                    }
                } catch (Throwable throwable) {
                    try {
                        io.getErr().println("Internal error occurred. Please report a bug.", null, true);
                    } catch (IOException ex) {
                    }

                    io.getOut().close();
                    listener.executionFinished(-1);
                    throw new RuntimeException(throwable);
                }
            }

        };

        RP.post(executor);
    }

    private static final String PACKAGE_NAME_PATTERN_STRING = "\\w+(?:/\\w+)*";
    private static final Pattern PACKAGE_NAME_PATTERN = Pattern.compile(PACKAGE_NAME_PATTERN_STRING);
    private static final Pattern BUILD_ERROR_PATTERN = Pattern.compile("(\\w+(?:[\\\\/]\\w+)*[\\\\/]\\w+\\.go):(\\d+):\\s*(.*)");

    private Future<Integer> executeImpl(final String commandName, final String packageName, final InputOutput io, final ExecutionListener listener) {
        if (COMMAND_REBUILD.equals(commandName)) {
            throw new IllegalArgumentException("Expected 'clean' and 'build' as separate commands.");
        }

        ExecutionEnvironmentFactoryService executionEnvironmentFactoryService =
            Lookup.getDefault().lookup(ExecutionEnvironmentFactoryService.class);

        final ExecutionEnvironment executionEnvironment = executionEnvironmentFactoryService.getLocal();

        final String workingDirectory = _project.getProjectDirectory().getPath().replace('/', File.separatorChar);
        boolean showInput = COMMAND_RUN.equals(commandName);
        final boolean unbuffer = true;
        final boolean statusEx = false;

        LineConvertor convertor = new LineConvertor() {

            @Override
            public List<ConvertedLine> convert(String line) {
                if (COMMAND_BUILD.equals(commandName) || COMMAND_COMPILE_SINGLE.equals(commandName)) {
                    String action = "Building";
                    if (PACKAGE_NAME_PATTERN.matcher(line).matches()) {
                        return Collections.singletonList(ConvertedLine.forText(action + " package " + line, null));
                    } else {
                        Matcher matcher = BUILD_ERROR_PATTERN.matcher(line);
                        if (matcher.matches()) {
                            return Collections.singletonList(ConvertedLine.forText(line, new BuildErrorOutputListener()));
                        }
                    }
                }

                return Collections.singletonList(ConvertedLine.forText(line, null));
            }

        };

        List<Writer> outputHandlers = new ArrayList<Writer>();
        if ("test".equals(commandName)) {
            outputHandlers.add(new GoTestOutputWriter(_project));
        }

        Writer outputListener = null;
        if (!outputHandlers.isEmpty()) {
            outputListener = new WriterRedirector(outputHandlers);
        }

        final ProcessChangeListener processChangeListener = new ProcessChangeListener(listener, outputListener, convertor, io);

        FileObject executable;
        List<String> args = new ArrayList<String>();
        if (COMMAND_RUN.equals(commandName)) {
            FileObject projectDirectory = _project.getProjectDirectory();
            if (projectDirectory == null || !projectDirectory.isFolder()) {
                throw new UnsupportedOperationException("Couldn't determine the project directory.");
            }

            FileObject binFolder = projectDirectory.getFileObject("bin");
            if (binFolder == null || !binFolder.isFolder()) {
                displayError(commandName, String.format("The build directory could not be found. Expected: %s%sbin", projectDirectory.getPath(), File.separatorChar));
                throw new UnsupportedOperationException("Couldn't determine build directory.");
            }

            executable = binFolder.getFileObject("main", "");
            if (executable == null && Utilities.isWindows()) {
                executable = binFolder.getFileObject("main", "exe");
            }

            if (executable == null || !executable.isData()) {
                String extension = Utilities.isWindows() ? ".exe" : "";
                String expected = projectDirectory.getPath() + File.separator + "bin" + File.separator + "main" + extension;
                if (File.separatorChar != '/') {
                    expected = expected.replace('/', File.separatorChar);
                }

                displayError(commandName, String.format("Couldn't find the target executable. Expected: %s", expected));
                throw new UnsupportedOperationException("Couldn't find the target executable.");
            }
        } else {
            File goroot = new File(System.getenv("GOROOT"));
            if (!goroot.isDirectory()) {
                displayError(commandName, "The GOROOT environment variable does not point to an accessible Go installation.");
                throw new UnsupportedOperationException("Couldn't determine GOROOT.");
            }

            FileObject gorootObject = FileUtil.toFileObject(goroot);
            if (gorootObject == null || !gorootObject.isFolder()) {
                throw new UnsupportedOperationException("Couldn't determine GOROOT.");
            }

            FileObject binFolder = gorootObject.getFileObject("bin");
            if (binFolder == null || !binFolder.isFolder()) {
                displayError(commandName, String.format("The Go installation directory environment variable does not contain a 'bin' directory. Expected: %s%sbin", gorootObject.getPath(), File.separatorChar));
                throw new UnsupportedOperationException("Couldn't determine Go bin directory.");
            }

            executable = binFolder.getFileObject("go", "");
            if (executable == null && Utilities.isWindows()) {
                executable = binFolder.getFileObject("go", "exe");
            }

            if (executable == null || !executable.isData()) {
                String extension = Utilities.isWindows() ? ".exe" : "";
                String expected = gorootObject.getPath() + File.separator + "bin" + File.separator + "go" + extension;
                if (File.separatorChar != '/') {
                    expected = expected.replace('/', File.separatorChar);
                }

                displayError(commandName, String.format("Couldn't find the Go tool. Expected: %s", expected));
                throw new UnsupportedOperationException("Couldn't find the Go tool.");
            }

            if (COMMAND_BUILD.equals(commandName) || COMMAND_COMPILE_SINGLE.equals(commandName)) {
                args.add("install");
                args.add("-v");
                args.add(packageName);
            } else if (COMMAND_CLEAN.equals(commandName)) {
                args.add("clean");
                args.add("-i");
                args.add("-x");
                args.add(packageName);
            } else if (COMMAND_TEST.equals(commandName)) {
                args.add("test");
                args.add("-v");
                args.add(packageName);
            }
        }

        NativeProcessBuilder nativeProcessBuilder = NativeProcessBuilder.newProcessBuilder(executionEnvironment)
            .setWorkingDirectory(workingDirectory)
            .unbufferOutput(unbuffer)
            .setStatusEx(statusEx)
            .addNativeProcessListener(processChangeListener);

        nativeProcessBuilder.setExecutable(executable.getPath()).setArguments(args.toArray(new String[args.size()]));
        nativeProcessBuilder.getEnvironment().put("GOPATH", workingDirectory);

        io.getOut().print(executable.getPath());
        for (String arg : args) {
            io.getOut().print(" " + arg);
        }
        io.getOut().print("\n");

        nativeProcessBuilder.redirectError();

        NativeExecutionDescriptor descriptor =
            new NativeExecutionDescriptor()
            .controllable(false) // don't enable the rerun or stop buttons in the IO tab
            .frontWindow(true) // select the IO tab before execution
            .inputVisible(showInput) // true to allow input from user
            .inputOutput(io)
            .outLineBased(!unbuffer)
            .showProgress(true)
            .postMessageDisplayer(new PostMessageDisplayer.Default(commandName))
            .postExecution(processChangeListener)
            .errConvertorFactory(processChangeListener)
            .outConvertorFactory(processChangeListener)
            .keepInputOutputOnFinish();

        descriptor.noReset(true);

        NativeExecutionService es = NativeExecutionService.newService(nativeProcessBuilder, descriptor, commandName);
        return es.run();
    }

    private void displayError(String command, String message) {
        String title;
        if (COMMAND_RUN.equals(command)) {
            title = "Error running the project";
        } else {
            title = String.format("Error executing \"go %s\"", command);
        }

        NotificationDisplayer.getDefault().notify(title, NotificationIcons.ERROR, message, null);
    }

    private class ExecutionEventListener implements ExecutionListener {

        @Override
        public void executionStarted(int pid) {
        }

        @Override
        public void executionFinished(int rc) {
            progressHandle.finish();
        }

    }

    private static class ProcessChangeListener implements ChangeListener, Runnable, ExecutionDescriptor.LineConvertorFactory {
        private final AtomicReference<NativeProcess> processRef = new AtomicReference<NativeProcess>();
        private final ExecutionListener listener;
        private Writer outputListener;
        private final LineConvertor lineConvertor;

        public ProcessChangeListener(ExecutionListener listener, Writer outputListener, LineConvertor lineConvertor, InputOutput tab) {
            this.listener = listener;
            this.outputListener = outputListener;
            this.lineConvertor = lineConvertor;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (!(e instanceof NativeProcessChangeEvent)) {
                return;
            }

            final NativeProcessChangeEvent event = (NativeProcessChangeEvent)e;
            processRef.compareAndSet(null, (NativeProcess)event.getSource());

            if (event.state == NativeProcess.State.RUNNING) {
                if (listener != null) {
                    listener.executionStarted(event.pid);
                }
            }
        }

        @Override
        public void run() {
            if (outputListener != null) {
                try {
                    outputListener.flush();
                    outputListener.close();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }

                outputListener = null;
            }

            NativeProcess process = processRef.get();
            if (process != null && listener != null) {
                listener.executionFinished(process.exitValue());
            }
        }

        @Override
        public LineConvertor newLineConvertor() {
            return new LineConvertor() {

                @Override
                public List<ConvertedLine> convert(String line) {
                    return ProcessChangeListener.this.convert(line);
                }

            };
        }

        private synchronized List<ConvertedLine> convert(String line) {
            if (outputListener != null) {
                try {
                    outputListener.write(line);
                    outputListener.write("\n");
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

            if (lineConvertor != null) {
                return lineConvertor.convert(line);
            }

            return null;
        }
    }

    private void handleRebuildAction(Lookup lookup) throws IllegalArgumentException {
        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (rebuild)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
        };

        tab = IOProvider.getDefault().getIO(projectName + " (rebuild)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        progressHandle = ProgressHandleFactory.createHandle(projectName + " (rebuild)", new Cancellable() {

            @Override
            public boolean cancel() {
                return false;
            }

        }, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ioTab.select();
            }

        });

        progressHandle.setInitialDelay(0);
        progressHandle.start();

        execute(COMMAND_REBUILD, ioTab);
    }

    private void handleCleanAction(Lookup lookup) throws IllegalArgumentException {
        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (clean)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
        };

        tab = IOProvider.getDefault().getIO(projectName + " (clean)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        progressHandle = ProgressHandleFactory.createHandle(projectName + " (clean)", new Cancellable() {

            @Override
            public boolean cancel() {
                return false;
            }

        }, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ioTab.select();
            }

        });

        progressHandle.setInitialDelay(0);
        progressHandle.start();

        execute(COMMAND_CLEAN, ioTab);
    }

    private void handleRunAction(Lookup lookup) throws IllegalArgumentException {
        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (run)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
        };

        tab = IOProvider.getDefault().getIO(projectName + " (run)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        progressHandle = ProgressHandleFactory.createHandle(projectName + " (run)", new Cancellable() {

            @Override
            public boolean cancel() {
                return false;
            }

        }, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ioTab.select();
            }

        });

        progressHandle.setInitialDelay(0);
        progressHandle.start();

        execute(COMMAND_RUN, ioTab);
    }

    private void handleDebugAction(Lookup lookup) throws IllegalArgumentException {
        throw new NotImplementedException();
    }

    private void handleProfileAction(Lookup lookup) throws IllegalArgumentException {
        throw new NotImplementedException();
    }

    private void handleTestAction(Lookup lookup) throws IllegalArgumentException {
        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (test)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
        };

        tab = IOProvider.getDefault().getIO(projectName + " (test)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        progressHandle = ProgressHandleFactory.createHandle(projectName + " (test)", new Cancellable() {

            @Override
            public boolean cancel() {
                return false;
            }

        }, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ioTab.select();
            }

        });

        progressHandle.setInitialDelay(0);
        progressHandle.start();

        execute(COMMAND_TEST, ioTab);
    }

    @Override
    public boolean isActionEnabled(String command, Lookup lookup) throws IllegalArgumentException {
        GoProject project = lookup.lookup(GoProject.class);
        if (command.equals(ActionProvider.COMMAND_BUILD)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(ActionProvider.COMMAND_COMPILE_SINGLE)) {
            if (project == null || project.isStandardLibrary()) {
                return false;
            }

            DataObject dataFolder = lookup.lookup(DataObject.class);
            if (dataFolder == null) {
                return false;
            }

            FileObject fileObject = dataFolder.getPrimaryFile();
            if (fileObject == null || !fileObject.isFolder()) {
                return false;
            }

            FileObject sourceRoot = _project.getSourceRoot();
            String relativePath = FileUtil.getRelativePath(sourceRoot, fileObject);
            if (relativePath == null) {
                return false;
            }

            return true;
        } else if (command.equals(ActionProvider.COMMAND_REBUILD)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(ActionProvider.COMMAND_CLEAN)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(ActionProvider.COMMAND_RUN)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(ActionProvider.COMMAND_DEBUG)) {
            return false;
        } else if (command.equals(ActionProvider.COMMAND_PROFILE)) {
            return false;
        } else if (command.equals(ActionProvider.COMMAND_TEST)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(ActionProvider.COMMAND_DELETE)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(ActionProvider.COMMAND_COPY)) {
            return project != null && !project.isStandardLibrary();
        } else {
            throw new IllegalArgumentException(command);
        }
    }

    private class BuildErrorOutputListener implements OutputListener {

        @Override
        public void outputLineSelected(OutputEvent ev) {
        }

        @Override
        public void outputLineAction(OutputEvent ev) {
            Matcher matcher = BUILD_ERROR_PATTERN.matcher(ev.getLine());
            if (!matcher.matches()) {
                return;
            }

            final String workingDirectory = _project.getProjectDirectory().getPath().replace('/', File.separatorChar);
            String filePath = workingDirectory + File.separatorChar + matcher.group(1).replace('/', File.separatorChar);
            String message = matcher.group(3);
            int line = Integer.parseInt(matcher.group(2));
            final int column = -1;

            FileObject file = FileUtil.toFileObject(new File(filePath));
            if (file == null) { // #13115
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            try {
                DataObject dob = DataObject.find(file);
                EditorCookie ed = dob.getLookup().lookup(EditorCookie.class);
                if (ed != null && /* not true e.g. for *_ja.properties */file == dob.getPrimaryFile()) {
                    if (line <= 0) {
                        // OK, just open it.
                        ed.open();
                    } else {
                        ed.openDocument(); // XXX getLineSet does not do it for you!
                        LOGGER.log(Level.FINE, "opened document for {0}", file);
                        try {
                            Line.Set lines = ed.getLineSet();
                            final Line editorLine = lines.getOriginal(line - 1);
                            if (!editorLine.isDeleted()) {
                                EventQueue.invokeLater(new Runnable() {
                                    public @Override void run() {
                                        editorLine.show(Line.ShowOpenType.REUSE, Line.ShowVisibilityType.FOCUS, column);
                                    }
                                });
                            }
                        } catch (IndexOutOfBoundsException ioobe) {
                            // Probably harmless. Bogus line number.
                            ed.open();
                        }
                    }
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            } catch (DataObjectNotFoundException donfe) {
                LOGGER.log(Level.WARNING, donfe.getLocalizedMessage(), donfe);
            }
            catch (IOException ioe) {
                // XXX see above, should not be necessary to call openDocument at all
                LOGGER.log(Level.WARNING, ioe.getLocalizedMessage(), ioe);
            }

            if (message != null) {
                // Try to do after opening the file, since opening a new file
                // clears the current status message.
                StatusDisplayer.getDefault().setStatusText(message);
            }
        }

        @Override
        public void outputLineCleared(OutputEvent ev) {
        }

    }

    protected static class WriterRedirector extends Writer {
        private final List<Writer> _writers;
        
        public WriterRedirector(@NonNull Collection<? extends Writer> writers) {
            Parameters.notNull("writers", writers);
            _writers = new ArrayList<Writer>(writers);
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for (Writer writer : _writers) {
                writer.write(cbuf, off, len);
            }
        }

        @Override
        public void flush() throws IOException {
            for (Writer writer : _writers) {
                writer.flush();
            }
        }

        @Override
        public void close() throws IOException {
            for (Writer writer : _writers) {
                writer.close();
            }
        }

    }
}
