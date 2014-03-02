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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.antlr.netbeans.util.NotificationIcons;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.editor.mimelookup.MimeLookup;
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
import org.openide.util.ImageUtilities;
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

    @StaticResource
    private static final String STOP_IMAGE = "org/tvl/goworks/project/ui/resources/stop.png";

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

    private final List<ExecutionListener> listeners = new CopyOnWriteArrayList<>();

    private WeakReference<Future<Integer>> _running;

    public GoActionProvider(final GoProject project) {
        this._project = project;
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

        ProgressHandle progressHandle = ProgressHandleFactory.createHandle(projectName + " (build)", new Cancellable() {

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

        execute("build", ioTab, progressHandle);
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

        ProgressHandle progressHandle = ProgressHandleFactory.createHandle(projectName + " (compile-single)", new Cancellable() {

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

        execute("build", ioTab, relativePath.replace(File.separatorChar, '/'), progressHandle);
   }

    public void execute(String commandName, InputOutput io, ProgressHandle progressHandle) {
        execute(commandName, io, "./...", progressHandle);
    }

    public void execute(final String commandName, final InputOutput io, final String packageName, final ProgressHandle progressHandle) {
        final ExecutionEventListener standardListener = new ExecutionEventListener(progressHandle, io);
        final ExecutionListener listener = new ExecutionListener() {

            @Override
            public void executionStarted(int pid) {
                standardListener.executionStarted(pid);
                for (ExecutionListener listener : listeners) {
                    listener.executionStarted(pid);
                }
            }

            @Override
            public void executionFinished(int rc) {
                standardListener.executionFinished(rc);
                for (ExecutionListener listener : listeners) {
                    listener.executionFinished(rc);
                }
            }

        };

        final ExecutionListener firstListener = new ExecutionListener() {

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
                    switch (commandName) {
                    case COMMAND_REBUILD:
                        {
                            Future<Integer> result;
                            result = executeImpl(COMMAND_CLEAN, packageName, io, firstListener);
                            if (result.get() != 0) {
                                return;
                            }   executeImpl(COMMAND_BUILD, packageName, io, listener);
                            break;
                        }

                    case COMMAND_RUN:
                        {
                            Future<Integer> result;
                            result = executeImpl(COMMAND_BUILD, packageName, io, firstListener);
                            if (result.get() != 0) {
                                return;
                            }   executeImpl(COMMAND_RUN, packageName, io, listener);
                            break;
                        }

                    default:
                        executeImpl(commandName, packageName, io, listener);
                        break;
                    }
                } catch (InterruptedException | ExecutionException throwable) {
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

        List<Writer> outputHandlers = new ArrayList<>();
        if ("test".equals(commandName)) {
            outputHandlers.add(new GoTestOutputWriter(_project));
        }

        Writer outputListener = null;
        if (!outputHandlers.isEmpty()) {
            outputListener = new WriterRedirector(outputHandlers);
        }

        final ProcessChangeListener processChangeListener = new ProcessChangeListener(listener, outputListener, convertor, io);

        FileObject executable;
        List<String> args = new ArrayList<>();
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

            String binaryName = null;
            String[] projectBinaries = getProjectBinaries();
            if (projectBinaries.length == 0) {
                displayError(commandName, "Couldn't identify a binary produced by this project.");
                throw new UnsupportedOperationException("Couldn't identify a binary produced by this project.");
            }

            binaryName = projectBinaries[0];
            if (projectBinaries.length > 1) {
                displayWarning(commandName, String.format("Found multiple binaries produced by this project; using '%s'. All binaries: %s", binaryName, Arrays.toString(projectBinaries)));
            }

            executable = binFolder.getFileObject(binaryName, "");
            if (executable == null && Utilities.isWindows()) {
                executable = binFolder.getFileObject(binaryName, "exe");
            }

            if (executable == null || !executable.isData()) {
                String extension = Utilities.isWindows() ? ".exe" : "";
                String expected = projectDirectory.getPath() + "/bin/" + binaryName + extension;
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

            switch (commandName) {
            case COMMAND_BUILD:
            case COMMAND_COMPILE_SINGLE:
                args.add("install");
                args.add("-v");
                args.add(packageName);
                break;

            case COMMAND_CLEAN:
                args.add("clean");
                args.add("-i");
                args.add("-x");
                args.add(packageName);
                break;

            case COMMAND_TEST:
                args.add("test");
                args.add("-v");
                args.add(packageName);
                break;
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
            .showProgress(false)
            .postMessageDisplayer(new PostMessageDisplayer.Default(commandName))
            .postExecution(processChangeListener)
            .errConvertorFactory(processChangeListener)
            .outConvertorFactory(processChangeListener)
            .keepInputOutputOnFinish();

        descriptor.noReset(true);

        NativeExecutionService es = NativeExecutionService.newService(nativeProcessBuilder, descriptor, commandName);
        Future<Integer> future = es.run();
        _running = new WeakReference<>(future);
        return future;
    }

    private String[] getProjectBinaries() {
        Lookup lookup = MimeLookup.getLookup("text/x-go");
        ProjectBinaryResolver resolver = lookup.lookup(ProjectBinaryResolver.class);
        String[] result = resolver.findProjectBinaries(_project);
        if (result == null) {
            return new String[0];
        }

        return result;
    }

    private void displayWarning(String command, String message) {
        String title;
        if (COMMAND_RUN.equals(command)) {
            title = "Running the project";
        } else {
            title = String.format("Executing \"go %s\"", command);
        }

        NotificationDisplayer.getDefault().notify(title, NotificationIcons.WARNING, message, null);
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

    private static class ExecutionEventListener implements ExecutionListener {
        private final ProgressHandle progressHandle;
        private final InputOutput io;

        public ExecutionEventListener(ProgressHandle progressHandle, InputOutput io) {
            this.progressHandle = progressHandle;
            this.io = io;
        }

        @Override
        public void executionStarted(int pid) {
        }

        @Override
        public void executionFinished(int rc) {
            if (progressHandle != null) {
                progressHandle.finish();
            }

            if (io != null) {
                io.getOut().close();
            }
        }

    }

    private static class ProcessChangeListener implements ChangeListener, Runnable, ExecutionDescriptor.LineConvertorFactory {
        private final AtomicReference<NativeProcess> processRef = new AtomicReference<>();
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

        ProgressHandle progressHandle = ProgressHandleFactory.createHandle(projectName + " (rebuild)", new Cancellable() {

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

        execute(COMMAND_REBUILD, ioTab, progressHandle);
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

        ProgressHandle progressHandle = ProgressHandleFactory.createHandle(projectName + " (clean)", new Cancellable() {

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

        execute(COMMAND_CLEAN, ioTab, progressHandle);
    }

    private void handleRunAction(Lookup lookup) throws IllegalArgumentException {
        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (run)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
            new AbstractAction("Stop", new ImageIcon(ImageUtilities.loadImage(STOP_IMAGE, false))) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WeakReference<Future<Integer>> weakFuture = _running;
                    Future<Integer> future = weakFuture != null ? weakFuture.get() : null;
                    if (future != null && !future.isCancelled() && !future.isDone()) {
                        future.cancel(true);
                    }
                }
            }
        };

        tab = IOProvider.getDefault().getIO(projectName + " (run)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        ProgressHandle progressHandle = ProgressHandleFactory.createHandle(projectName + " (run)", new Cancellable() {

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

        execute(COMMAND_RUN, ioTab, progressHandle);
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

        ProgressHandle progressHandle = ProgressHandleFactory.createHandle(projectName + " (test)", new Cancellable() {

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

        execute(COMMAND_TEST, ioTab, progressHandle);
    }

    @Override
    public boolean isActionEnabled(String command, Lookup lookup) throws IllegalArgumentException {
        if (_project == null) {
            return false;
        }

        switch (command) {
        case ActionProvider.COMMAND_BUILD:
            return !_project.isStandardLibrary();

        case ActionProvider.COMMAND_COMPILE_SINGLE:
            if (_project.isStandardLibrary()) {
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

        case ActionProvider.COMMAND_REBUILD:
            return !_project.isStandardLibrary();

        case ActionProvider.COMMAND_CLEAN:
            return !_project.isStandardLibrary();

        case ActionProvider.COMMAND_RUN:
            return !_project.isStandardLibrary();

        case ActionProvider.COMMAND_DEBUG:
            return false;

        case ActionProvider.COMMAND_PROFILE:
            return false;

        case ActionProvider.COMMAND_TEST:
            return !_project.isStandardLibrary();

        case ActionProvider.COMMAND_DELETE:
            return !_project.isStandardLibrary();

        case ActionProvider.COMMAND_COPY:
            return !_project.isStandardLibrary();

        default:
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
            _writers = new ArrayList<>(writers);
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
