/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import org.openide.util.Cancellable;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NotImplementedException;
import org.openide.util.RequestProcessor;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Sam Harwell
 */
public final class GoActionProvider implements ActionProvider {
    public static final String COMMAND_INSTALL = "build.install";

    private static final String[] supported = new String[] {
        ActionProvider.COMMAND_BUILD,
        ActionProvider.COMMAND_REBUILD,
        ActionProvider.COMMAND_CLEAN,
        COMMAND_INSTALL,
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
    private volatile Future<Integer> executorTask;

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
        else if (string.equals(COMMAND_REBUILD)) {
            handleRebuildAction(lookup);
        }
        else if (string.equals(COMMAND_CLEAN)) {
            handleCleanAction(lookup);
        }
        else if (string.equals(COMMAND_INSTALL)) {
            handleInstallAction(lookup);
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

    public void execute(final String commandName, final InputOutput io) {
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
                    executeImpl(commandName, io, listener);
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

        if (SwingUtilities.isEventDispatchThread()) {
            RequestProcessor.getDefault().post(executor);
        } else {
            executor.run();
        }
    }

    private static final String PACKAGE_NAME_PATTERN_STRING = "\\w+(?:/\\w+)*";
    private static final Pattern PACKAGE_NAME_PATTERN = Pattern.compile(PACKAGE_NAME_PATTERN_STRING);
    private static final Pattern BUILD_ERROR_PATTERN = Pattern.compile("(?<file>\\w+(?:[\\\\/]\\w+)*[\\\\/]\\w+\\.go):(?<line>\\d+):\\s*(?<message>.*)");

    private void executeImpl(final String commandName, final InputOutput io, final ExecutionListener listener) {
        ExecutionEnvironmentFactoryService executionEnvironmentFactoryService =
            Lookup.getDefault().lookup(ExecutionEnvironmentFactoryService.class);

        final ExecutionEnvironment executionEnvironment = executionEnvironmentFactoryService.getLocal();

        final String workingDirectory = _project.getProjectDirectory().getPath().replace('/', File.separatorChar);
        boolean showInput = true;
        final boolean unbuffer = true;
        final boolean statusEx = false;

        LineConvertor convertor = new LineConvertor() {

            @Override
            public List<ConvertedLine> convert(String line) {
                if (COMMAND_BUILD.equals(commandName) || COMMAND_INSTALL.equals(commandName)) {
                    String action = COMMAND_BUILD.equals(commandName) ? "Building" : "Installing";
                    if (PACKAGE_NAME_PATTERN.matcher(line).matches()) {
                        return Collections.singletonList(ConvertedLine.forText(action + " package " + line, null));
                    } else {
                        Matcher matcher = BUILD_ERROR_PATTERN.matcher(line);
                        if (matcher.matches()) {
                            String filePath = workingDirectory + File.separatorChar + matcher.group("file").replace('/', File.separatorChar);
                            String result = filePath + ':' + matcher.group("line") + ": error: " + matcher.group("message");
                            return Collections.singletonList(ConvertedLine.forText(result, null));
                        }
                    }
                }

                return Collections.singletonList(ConvertedLine.forText(line, null));
            }

        };

        Writer outputListener = null;
//        if (outputHandlers != null && !outputHandlers.isEmpty()) {
//            writer = new WriterRedirector(outputHandlers);
//        }

        final ProcessChangeListener processChangeListener = new ProcessChangeListener(listener, outputListener, convertor, io);
        String executable = "C:\\Go\\bin\\go.exe";
        List<String> args = new ArrayList<String>();
        if (COMMAND_BUILD.equals(commandName) || COMMAND_REBUILD.equals(commandName)) {
            args.add("build");
            args.add("-v");

            if (COMMAND_REBUILD.equals(commandName)) {
                args.add("-a");
            }

            args.add("./...");
        } else if (COMMAND_CLEAN.equals(commandName)) {
            args.add("clean");
            args.add("-i");
            args.add("-x");
            args.add("./...");
        } else if (COMMAND_INSTALL.equals(commandName)) {
            args.add("install");
            args.add("-v");
            args.add("./...");
        } else if (COMMAND_TEST.equals(commandName)) {
            args.add("test");
            args.add("-v");
            args.add("./...");
        }

        NativeProcessBuilder nativeProcessBuilder = NativeProcessBuilder.newProcessBuilder(executionEnvironment)
            .setWorkingDirectory(workingDirectory)
            .unbufferOutput(unbuffer)
            .setStatusEx(statusEx)
            .addNativeProcessListener(processChangeListener);

        nativeProcessBuilder.setExecutable(executable).setArguments(args.toArray(new String[args.size()]));
        nativeProcessBuilder.getEnvironment().put("GOPATH", workingDirectory);

        io.getOut().print(executable);
        io.getOut().print(" ");
        io.getOut().print(args);
        io.getOut().print("\n");

        nativeProcessBuilder.redirectError();

        NativeExecutionDescriptor descriptor =
            new NativeExecutionDescriptor()
            .controllable(true)
            .frontWindow(true)
            .inputVisible(showInput)
            .inputOutput(io)
            .outLineBased(!unbuffer)
            .showProgress(true)
            .postMessageDisplayer(new PostMessageDisplayer.Default(COMMAND_BUILD))
            .postExecution(processChangeListener)
            .errConvertorFactory(processChangeListener)
            .outConvertorFactory(processChangeListener)
            .keepInputOutputOnFinish();

        descriptor.noReset(true);

        NativeExecutionService es = NativeExecutionService.newService(nativeProcessBuilder, descriptor, COMMAND_BUILD);
        executorTask = es.run();
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

    private void handleInstallAction(Lookup lookup) throws IllegalArgumentException {
        String projectName = ProjectUtils.getInformation(_project).getDisplayName();
        InputOutput tab;
        tab = IOProvider.getDefault().getIO(projectName + " (install)", false);
        tab.closeInputOutput();

        Action[] actions = new Action[] {
        };

        tab = IOProvider.getDefault().getIO(projectName + " (install)", actions);
        try {
            tab.getOut().reset();
        } catch (IOException ex) {
        }

        final InputOutput ioTab = tab;

        progressHandle = ProgressHandleFactory.createHandle(projectName + " (install)", new Cancellable() {

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

        execute(COMMAND_INSTALL, ioTab);
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
        } else if (command.equals(ActionProvider.COMMAND_REBUILD)) {
            //return project != null && !project.isStandardLibrary();
            return false;
        } else if (command.equals(ActionProvider.COMMAND_CLEAN)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(COMMAND_INSTALL)) {
            return project != null && !project.isStandardLibrary();
        } else if (command.equals(ActionProvider.COMMAND_RUN)) {
            return false;
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

}
