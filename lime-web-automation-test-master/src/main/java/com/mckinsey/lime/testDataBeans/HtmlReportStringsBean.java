package com.mckinsey.lime.testDataBeans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HtmlReportStringsBean {
    private String testTagName;
    private String testTagTimeTaken;
    private String testTagStatus;
    private String testTagUniqueName;
    private String sauceJobURL;

    private String totalSuiteSummary;
    private String testMethodResultRow;

    private String testMethodSno;
    private String testMethodPackageName;
    private String testMethodClassName;
    private String testMethodMethodName;
    private String testMethodDescription;
    private String testMethodStartTime;
    private String testMethodTimeTaken;
    private String testMethodStatusClass;
    private String testMethodStatus;
    private String testMethodScreenShotLink;
    private String testMethodLogs;
    private String testMethodRequest;
    private String testMethodResponse;
    private String testMethodData;

    private String summaryTotalTestMethods;
    private String summaryTotalPassedtestMethods;
    private String summaryTotalFailedTestMethods;
    private String summaryTotalSkippedTestMethods;
    private String summaryTotalPartialSuccessTestMethods;
    private String summaryTotalDurationSequential;
    private String summaryTotalDurationParallel;

    private String testTagResult;

    private String popupUniqueName;
    private String popupBodyText;
    private String popupLinkName;
}
