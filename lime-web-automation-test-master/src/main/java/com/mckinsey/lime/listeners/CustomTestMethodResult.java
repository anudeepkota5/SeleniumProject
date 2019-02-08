package com.mckinsey.lime.listeners;

import com.mckinsey.lime.utils.CustomLogStrings;
import com.mckinsey.lime.utils.JavaUtils;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import com.mckinsey.lime.utils.Utilities;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

enum TestMethodStatus {
    PASS(1), FAIL(2), SKIP(3), PARTIALSUCCESS(4);

    public int value;

    TestMethodStatus(int i) {
        this.value = i;
    }

    public static TestMethodStatus valueOf(int i) {
        TestMethodStatus returnObj = null;
        for (TestMethodStatus obj : TestMethodStatus.values()) {
            if (obj.value == i)
                returnObj = obj;
            else
                continue;
        }
        return returnObj;
    }
}

public class CustomTestMethodResult {

    private ITestResult currentTestMethodResult;
    private ISuiteResult currentTestResult;

    public CustomTestMethodResult(ISuiteResult currentTestResult, ITestResult currentTestMethodResult) {
        this.currentTestResult = currentTestResult;
        this.currentTestMethodResult = currentTestMethodResult;
    }

    public String getTestName() {
        return currentTestResult.getTestContext().getName();
    }

    public String getTestDuration() {
        return Utilities.timeConversion(currentTestResult.getTestContext().getEndDate().getTime() - currentTestResult.getTestContext().getStartDate().getTime());
    }

    public String getTestMethodPackage() {
        return currentTestMethodResult.getTestClass().getRealClass().getPackage().getName();
    }

    public String getTestMethodClass() {
        return currentTestMethodResult.getTestClass().getRealClass().getSimpleName();
    }

    public String getTestMethodName() {
        return currentTestMethodResult.getMethod().getMethodName();
    }

    public String getTestMethodDescription() {
        String description = currentTestMethodResult.getMethod().getDescription();
        if (description != null)
            return description;
        else
            return "missing Description for test";
    }

    public TestMethodStatus getTestMethodStatus() {
        return TestMethodStatus.valueOf(currentTestMethodResult.getStatus());
    }

    public String getTestMethodUniqueName() {
        //TODO: Need to improve
        String sessionID = getSessionID();
//        return getTestMethodPackage().replace(".", "_") + "_" + getTestMethodClass() + "_" + getTestMethodName() + "_" + sessionID;
        return getTestMethodPackage().replace(".", "_") + "_" + getTestMethodClass() + "_" + getTestMethodName() + "_" + JavaUtils.generateRandomIntIntRange();
    }

    public String getTestMethodUniqueNameForScreenShot() {
        //TODO: Need to improve
        String sessionID = getSessionID();
        return getTestMethodPackage().replace(".", "_") + "_" + getTestMethodClass() + "_" + getTestMethodName() + "_" + sessionID;
//        return getTestMethodPackage().replace(".", "_") + "_" + getTestMethodClass() + "_" + getTestMethodName() + "_" + JavaUtils.generateRandomIntIntRange();
    }

    private String getSessionID() {
        Object jobName = currentTestMethodResult.getAttribute(TestNgCustomStrings.JOB_NAME);
        return ((jobName == null)) ? "" : jobName.toString();
    }

    public String getMethodRowClass() {
        if (getTestMethodStatus() == TestMethodStatus.PASS) {
            return "passedTest";
        } else if (getTestMethodStatus() == TestMethodStatus.FAIL) {
            return "failedTest";
        } else if (getTestMethodStatus() == TestMethodStatus.SKIP) {
            return "skippedTest";
        } else if (getTestMethodStatus() == TestMethodStatus.PARTIALSUCCESS) {
            return "partialSuccessTest";
        }
        return "";
    }

    public String getExceptionName() {
        String exceptionName = "SuccessLogs";
        if (getTestMethodStatus() == TestMethodStatus.FAIL) {
            Throwable throwable = currentTestMethodResult.getThrowable();
            if (throwable != null)
                exceptionName = throwable.getClass().getSimpleName();
        }
        return exceptionName;
    }

    public String getStackTrace() {
        String stackTrace = "";
        List<String> reporterLogs = Reporter.getOutput(currentTestMethodResult);

        stackTrace = reporterLogs.stream().filter(item -> !item.startsWith(CustomLogStrings.API_REQUEST))
                .filter(item -> !item.startsWith(CustomLogStrings.API_REQUEST_HEADERS))
                .filter(item -> !item.startsWith(CustomLogStrings.API_RESPONSE))
                .filter(item -> !item.startsWith(CustomLogStrings.TEST_DATA))
                .map(item -> {
                    if (item.startsWith(CustomLogStrings.PASSED))
                        item = item.replace(CustomLogStrings.PASSED, "<span class='passedTestStep'>" + CustomLogStrings.PASSED + "</span>");
                    if (item.startsWith(CustomLogStrings.FAILED))
                        item = item.replace(CustomLogStrings.FAILED, "<span class='failedTestStep'>" + CustomLogStrings.FAILED + "</span>");
                    if (item.startsWith(CustomLogStrings.SKIPPED))
                        item = item.replace(CustomLogStrings.SKIPPED, "<span class='skippedTestStep'>" + CustomLogStrings.SKIPPED + "</span>");
                    if (item.startsWith(CustomLogStrings.INFO_LOG))
                        item = item.replace(CustomLogStrings.INFO_LOG, "<span class='infoLog'>" + CustomLogStrings.INFO_LOG + "</span>");
                    if (item.startsWith(CustomLogStrings.ERROR_LOG))
                        item = item.replace(CustomLogStrings.ERROR_LOG, "<span class='errorLog'>" + CustomLogStrings.ERROR_LOG + "</span>");
                    if (item.contains(CustomLogStrings.ACTUAL))
                        item = item.replace(CustomLogStrings.ACTUAL, "<span class='highlight'>" + CustomLogStrings.ACTUAL + "</span>");
                    if (item.contains(CustomLogStrings.EXPECTED))
                        item = item.replace(CustomLogStrings.EXPECTED, "<span class='highlight'>" + CustomLogStrings.EXPECTED + "</span>");
                    return item;
                })
                .collect(Collectors.joining("\n\n"));

        if (getTestMethodStatus() == TestMethodStatus.FAIL) {
            stackTrace = stackTrace + "\n" + StringEscapeUtils.escapeHtml(ExceptionUtils.getFullStackTrace(currentTestMethodResult.getThrowable()));
        }
        return stackTrace;
    }

    public String getRequest() {
        List<String> reporterLogs = Reporter.getOutput(currentTestMethodResult);

        AtomicReference<Integer> i = new AtomicReference<>(1);

        List<String> request = reporterLogs.stream()
                .filter(item -> item.startsWith(CustomLogStrings.API_REQUEST))
                .map(item -> item.replaceAll(CustomLogStrings.API_REQUEST, "<b>" + i.getAndSet(i.get() + 1) + ". " + CustomLogStrings.API_REQUEST + "</b>")
                        .replace(CustomLogStrings.API_REQUEST_BODY, "<b>" + CustomLogStrings.API_REQUEST_BODY + "</b>"))
                .collect(Collectors.toList());
//                .collect(Collectors.joining("\n<hr>\n"));
//                .replaceAll("Request:", "<b>Request:</b>");
        List<String> requestHeaders = reporterLogs.stream()
                .filter(item -> item.startsWith(CustomLogStrings.API_REQUEST_HEADERS))
                .map(item -> item.replaceAll(CustomLogStrings.API_REQUEST_HEADERS, "<b>" + CustomLogStrings.API_REQUEST_HEADERS + "</b>\n\t"))
                .map(item -> item.replace("{", "")
                        .replace("}", "")
                        .replace("=", " : \t\t")
                        .replace(", ", "\n\t"))
                .collect(Collectors.toList());

        ArrayList<String> objects = new ArrayList<>();

        for (int j = 0; j < request.size(); j++) {
            objects.add(request.get(j) + "\n" + requestHeaders.get(j));
        }
        String collect = objects.stream()
                .collect(Collectors.joining("\n<hr>\n"));
        return collect;

        /*List<ApiCallDetails> apiCallDetailsObj = TestNGUtils.getApiCallDetailsObj(currentTestMethodResult.getTestContext());
        String finalRequestString = apiCallDetailsObj.stream()
                .map(item -> {
                    String headerString = item.getRequestHeaders().entrySet().stream()
                            .map(item1 -> item1.getKey() + "\t: " + item1.getValue())
                            .collect(Collectors.joining("\n\t"));

                    String requestBodyString = "";
                    if (!item.getRequestBody().isEmpty())
                        requestBodyString = "\nRequest Body: " + item.getRequestBody();

                    return item.getApiCallCount() + ". Request: " + item.getRequestURL() + "\nRequest Headers: " + headerString + requestBodyString;
                })
                .map(item -> item.replace("Request: ", "<b>Request: </b>")
                        .replace("Request Headers: ", "<b>Request Headers: </b>")
                        .replace("Request Body: ", "<b>Request Body: </b>"))
                .collect(Collectors.joining("\n<hr>\n"));


        return finalRequestString;*/
    }

    public String getResponse() {
        List<String> reporterLogs = Reporter.getOutput(currentTestMethodResult);

        AtomicReference<Integer> i = new AtomicReference<>(1);

        String response = reporterLogs.stream()
                .filter(item -> item.startsWith(CustomLogStrings.API_RESPONSE))
                .map(item -> item.replaceAll(CustomLogStrings.API_RESPONSE, "<b>" + i.getAndSet(i.get() + 1) + ". " + CustomLogStrings.API_RESPONSE + "</b>"))
                .collect(Collectors.joining("\n<hr>\n"));
        return response;
        /*List<ApiCallDetails> apiCallDetailsObj = TestNGUtils.getApiCallDetailsObj(currentTestMethodResult.getTestContext());
        String finalResponseString = apiCallDetailsObj.stream()
                .map(item -> {
                    String jsonObject = null;
                    try {
                        jsonObject = new JSONObject(item.getResponse()).toString(4);
                    } catch (JSONException e) {
                        jsonObject = item.getResponse();
                    }
                    return item.getApiCallCount() + ". Response: " + jsonObject;
                })
                .map(item -> item.replace("Response: ", "<b>Response: </b>"))
                .collect(Collectors.joining("\n<hr>\n"));

        return finalResponseString;*/

    }

    public String getTestMethodDuration() {
        return Utilities.timeConversion(currentTestMethodResult.getEndMillis() - currentTestMethodResult.getStartMillis());
    }

    public String getTestMethodStartTime() {
        return Utilities.dateConversion(currentTestMethodResult.getStartMillis());
    }

    public String getSauceURL() {

        String jobURL = (getSessionID().isEmpty()) ? "" : "https://app.saucelabs.com/tests/" + getSessionID();
        return (jobURL.isEmpty()) ? "" : "<a href=\"" + jobURL + "\">sauce job link</a>";
    }

    public String getTestData() {
//        Object testData = currentTestMethodResult.getTestContext().getAttribute(TestNgCustomStrings.TEST_DATA);
//        return ((testData == null)) ? "" : testData.toString();
        List<String> reporterLogs = Reporter.getOutput(currentTestMethodResult);


        String first = reporterLogs.stream()
                .filter(item -> item.startsWith(CustomLogStrings.TEST_DATA))
                .map(item -> item.substring(item.lastIndexOf(CustomLogStrings.TEST_DATA) + CustomLogStrings.TEST_DATA.length()))
                .collect(Collectors.joining("<br/>"));
        return first;
    }
}