/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project.testing;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.modules.gsf.testrunner.api.Manager;
import org.netbeans.modules.gsf.testrunner.api.Report;
import org.netbeans.modules.gsf.testrunner.api.Status;
import org.netbeans.modules.gsf.testrunner.api.TestSession;
import org.netbeans.modules.gsf.testrunner.api.TestSuite;
import org.netbeans.modules.gsf.testrunner.api.Testcase;
import org.openide.util.Parameters;
import org.tvl.goworks.project.GoProject;
import org.tvl.goworks.project.testing.nodes.GoTestRunnerNodeFactory;

/**
 *
 * @author Sam Harwell
 */
public class GoTestOutputWriter extends Writer {
    // -J-Dorg.tvl.goworks.project.testing.GoTestOutputWriter.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoTestOutputWriter.class.getName());

    private final GoProject _project;
    private final StringBuilder _buffer = new StringBuilder();
    private final List<Testcase> _cases = new ArrayList<Testcase>();

    private float _totalTime;
    private TestSession _session;

    public GoTestOutputWriter(@NonNull GoProject project) {
        Parameters.notNull("project", project);
        this._project = project;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        _buffer.append(cbuf, off, len);
        processBufferLines(false);
    }

    @Override
    public void flush() throws IOException {
        processBufferLines(true);
    }

    @Override
    public void close() throws IOException {
        if (_session != null) {
            Manager.getInstance().sessionFinished(_session);
            _session = null;
        }
    }

    private void processBufferLines(boolean flush) {
        if (_buffer.length() == 0) {
            return;
        }

        while (true) {
            int newline = _buffer.length();
            for (int i = 0; i < _buffer.length(); i++) {
                if (_buffer.charAt(i) == '\r' || _buffer.charAt(i) == '\n') {
                    newline = i;
                    break;
                }
            }

            if (newline == _buffer.length() && !flush) {
                break;
            }

            String line = _buffer.substring(0, newline);
            if (!line.isEmpty()) {
                processLine(line);
            }

            _buffer.delete(0, Math.min(_buffer.length(), newline + 1));
        }
    }

    private static final Pattern RUN_PATTERN = Pattern.compile("^===\\s*RUN\\s+([a-zA-Z0-9_]+)\\s*$");
    private static final Pattern TEST_RESULT_PATTERN = Pattern.compile("^---\\s*(PASS|FAIL):\\s*([a-zA-Z0-9_]+)\\s+\\(([0-9+](\\.[0-9]+)?)\\s+seconds\\)\\s*$");
    private static final Pattern PACKAGE_RESULT_PATTERN = Pattern.compile(
        "^" +
        "(\\?|ok|FAIL)" + // result
        "\\s+" +
        "([a-zA-Z0-9_]+)" + // package
        "\\s+" +
        "(?:" +
            "([0-9+](\\.[0-9]+)?)s" + // time
            "|" +
            "\\[(.*?)\\]" + // message
        ")\\s*$");

    private void processLine(String line) {
        Matcher runMatcher = RUN_PATTERN.matcher(line);
        if (runMatcher.matches()) {
            createSession();
            String test = runMatcher.group(1);
            processRun(test);
            return;
        }

        Matcher testResultMatcher = TEST_RESULT_PATTERN.matcher(line);
        if (testResultMatcher.matches()) {
            createSession();
            String result = testResultMatcher.group(1);
            String test = testResultMatcher.group(2);
            String time = testResultMatcher.group(3);
            processTestResult(result, test, time);
            return;
        }

        Matcher packageResultMatcher = PACKAGE_RESULT_PATTERN.matcher(line);
        if (packageResultMatcher.matches()) {
            createSession();
            String result = packageResultMatcher.group(1);
            String packageName = packageResultMatcher.group(2);
            String time = packageResultMatcher.group(3);
            String message = packageResultMatcher.group(4);
            processPackageResult(result, packageName, time, message);
            return;
        }

        if (_session != null) {
            Manager.getInstance().displayOutput(_session, line, true);
        }

        LOGGER.log(Level.FINE, "Unknown output line: {0}", line);
    }

    private void createSession() {
        if (_session != null) {
            return;
        }

        TestSession.SessionType sessionType = TestSession.SessionType.TEST;
        _session = new TestSession(_project.getProjectDirectory().getName(), _project, sessionType, new GoTestRunnerNodeFactory());
        Manager.getInstance().testStarted(_session);
    }

    private void processRun(String test) {
        LOGGER.log(Level.FINE, "Running test: {0}", test);
    }

    private void processTestResult(String result, String test, String time) {
        Testcase testcase = new Testcase(test, null, _session);
        if ("PASS".equals(result)) {
            testcase.setStatus(Status.PASSED);
        } else {
            testcase.setStatus(Status.FAILED);
        }

        try {
            Float timeInSeconds = Float.valueOf(time);
            testcase.setTimeMillis(Math.round(timeInSeconds * 1000));
        } catch (NumberFormatException ex) {
        }

        _cases.add(testcase);
        LOGGER.log(Level.FINE, "Test result: result={0}, test={1}, time={2}", new Object[] { result, test, time });
    }

    private void processPackageResult(String result, String packageName, String time, String message) {
        float timeInSeconds = 0;
        if (time != null) {
            try {
                timeInSeconds = Float.valueOf(time);
            } catch (NumberFormatException ex) {
                timeInSeconds = 0;
            }
        }

        TestSuite suite = new TestSuite(packageName);
        _session.addSuite(suite);
        Manager.getInstance().displaySuiteRunning(_session, suite.getName());

        for (Testcase testcase : _cases) {
            testcase.setClassName(packageName);
            _session.addTestCase(testcase);
        }

        _cases.clear();
        _totalTime += timeInSeconds;
        Report report = _session.getReport(Math.round(timeInSeconds * 1000));
        Manager.getInstance().displayReport(_session, report);
        LOGGER.log(Level.FINE, "Package result: result={0}, test={1}, time={2}, message={3}", new Object[] { result, packageName, time, message });
    }
}
