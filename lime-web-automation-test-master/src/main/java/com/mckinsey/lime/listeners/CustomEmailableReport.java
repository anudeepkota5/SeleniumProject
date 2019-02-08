package com.mckinsey.lime.listeners;

import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.testDataBeans.FilePaths;
import com.mckinsey.lime.testDataBeans.HtmlReportStringsBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.JavaUtils;
import com.mckinsey.lime.utils.ReportUtils;
import com.mckinsey.lime.utils.Utilities;
import org.apache.commons.io.FileUtils;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Reporter that generates a single-page HTML report of the test results.
 */
public class CustomEmailableReport implements IReporter {
    private final HtmlReportStringsBean htmlReportReplaceStrings = TestData.getHtmlReportStrings();

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String reportPath = outputDirectory + "/CustomizedReport2.html";
        int sNo = 0;
        try {
            Path htmlOutPutDirPath = new File(outputDirectory).toPath();
            JavaUtils.deleteDirectoryStream(htmlOutPutDirPath);
            FileUtils.copyFile(new File(FilePaths.CUSTOMREPORT2_TEMPLATE_TOTALREPORT), new File(reportPath));
            FileUtils.copyDirectory(new File(FilePaths.CUSTOMREPORT2_TEMPLATE_SCRIPTSFOLDER), new File(outputDirectory, "Scripts"));
            FileUtils.copyDirectory(new File(FilePaths.CUSTOMREPORT2_TEMPLATE_STYLESFOLDER), new File(outputDirectory, "Styles"));
            FilePaths.SCREENSHOTS_FOLDER_REPORT = outputDirectory + "/screenshots/";
            File screenshotsFolder = new File(FilePaths.SCREENSHOTS_FOLDER_REPORT);
            Path source = new File(FilePaths.SCREENSHOTS_FOLDER).toPath();
            if (source.toFile().exists()) {
                screenshotsFolder.mkdirs();
                Files.move(source, screenshotsFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String totalReport = Utilities.readFile(reportPath);
        String totalRowTemplate = Utilities.readFile(FilePaths.CUSTOMREPORT2_TEMPLATE_TOTALROWTEMPLATE);

        //iterate through multiple testng xml files (Currently we have only 1)
        for (ISuite indSuite : suites) {

            Map<String, ISuiteResult> indSuiteResults = indSuite.getResults();

            //iterate through test tags in testng xml
            for (Map.Entry<String, ISuiteResult> indTestResultMapEntry : indSuiteResults.entrySet()) {
                ISuiteResult indTestResult = indTestResultMapEntry.getValue();

                List<ITestResult> allTestMethodResults = ReportUtils.getAllTestMethodResults(indTestResult);

                String testTagTemplate = Utilities.readFile(FilePaths.CUSTOMREPORT2_TEMPLATE_TESTTAGTEMPLATE);

                CustomTestTagResult testTagResult = new CustomTestTagResult(indTestResult);
                testTagTemplate = testTagTemplate.replace(htmlReportReplaceStrings.getTestTagName(), testTagResult.getTestName());
                testTagTemplate = testTagTemplate.replace(htmlReportReplaceStrings.getTestTagTimeTaken(), testTagResult.getTestDuration());
                testTagTemplate = testTagTemplate.replace(htmlReportReplaceStrings.getTestTagStatus(), testTagResult.getTestStatus());
                testTagTemplate = testTagTemplate.replace(htmlReportReplaceStrings.getTestTagUniqueName(), testTagResult.getUniqueTestTagName());

                for (ITestResult indTestMethodResult : allTestMethodResults) {

                    CustomTestMethodResult customTestMethodResultObj = new CustomTestMethodResult(indTestResult, indTestMethodResult);

                    sNo++;
                    String rowTemplate = Utilities.readFile(FilePaths.CUSTOMREPORT2_TEMPLATE_ROWTEMPLATE);

                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodStatusClass(), customTestMethodResultObj.getMethodRowClass());
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodSno(), String.valueOf(sNo));
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodPackageName(), customTestMethodResultObj.getTestMethodPackage());
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodClassName(), customTestMethodResultObj.getTestMethodClass());
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodMethodName(), customTestMethodResultObj.getTestMethodName());
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodDescription(), customTestMethodResultObj.getTestMethodDescription());
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodStartTime(), customTestMethodResultObj.getTestMethodStartTime());
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodTimeTaken(), customTestMethodResultObj.getTestMethodDuration());

                    String screenShotLink = "";
                    if (customTestMethodResultObj.getTestMethodStatus() == TestMethodStatus.FAIL) {
                        screenShotLink = ReportUtils.prepareScreenshotPopupHtml(customTestMethodResultObj);
                    }
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodScreenShotLink(), screenShotLink);

                    String testMethodRequest = "";
                    String testMethodResponse = "";
                    String testMethodLogs = "";
                    String sauceURL = "";

                    if (customTestMethodResultObj.getTestMethodStatus() != TestMethodStatus.SKIP) {

                        testMethodRequest = ReportUtils.prepareRequestPopupHtml(customTestMethodResultObj);
                        testMethodResponse = ReportUtils.prepareResponsePopupHtml(customTestMethodResultObj);
                        testMethodLogs = ReportUtils.prepareLogsPopupHtml(customTestMethodResultObj);
                        sauceURL = customTestMethodResultObj.getSauceURL();
                    }

                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodRequest(), testMethodRequest);
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodResponse(), testMethodResponse);
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodData(), customTestMethodResultObj.getTestData());
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getTestMethodLogs(), testMethodLogs);
                    rowTemplate = rowTemplate.replace(htmlReportReplaceStrings.getSauceJobURL(), sauceURL);

                    testTagTemplate = testTagTemplate.replace(htmlReportReplaceStrings.getTestMethodResultRow(), rowTemplate);

                }
                totalReport = totalReport.replace(htmlReportReplaceStrings.getTestTagResult(), testTagTemplate);
            }

            CustomTotalSuiteResultsSummary summary = new CustomTotalSuiteResultsSummary(indSuiteResults);
            totalRowTemplate = totalRowTemplate.replace(htmlReportReplaceStrings.getSummaryTotalTestMethods(), String.valueOf(summary.getTotalTestMethods()));
            totalRowTemplate = totalRowTemplate.replace(htmlReportReplaceStrings.getSummaryTotalPassedtestMethods(), String.valueOf(summary.getTotalPassedtestMethods()));
            totalRowTemplate = totalRowTemplate.replace(htmlReportReplaceStrings.getSummaryTotalFailedTestMethods(), String.valueOf(summary.getTotalFailedTestMethods()));
            totalRowTemplate = totalRowTemplate.replace(htmlReportReplaceStrings.getSummaryTotalSkippedTestMethods(), String.valueOf(summary.getTotalSkippedTestMethods()));
            totalRowTemplate = totalRowTemplate.replace(htmlReportReplaceStrings.getSummaryTotalPartialSuccessTestMethods(), String.valueOf(summary.getTotalPartialSuccessTestMethods()));
            totalRowTemplate = totalRowTemplate.replace(htmlReportReplaceStrings.getSummaryTotalDurationSequential(), summary.getTotalDurationSequential());

            totalReport = totalReport.replace(htmlReportReplaceStrings.getTotalSuiteSummary(), totalRowTemplate);
        }

        Utilities.writeFile(reportPath, totalReport);

        /**
         * Sending the report to slack channel
         */
        if (TestData.getConfigData().getSlackReport()) {
            try {
//            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
                String timeStamp = LocalDateTime.now().toString();
                File reportZip = Utilities.zipDirectory(outputDirectory, "ApiTestReport_" + timeStamp + ".zip");
                ApiRequests.sendAutomationReportToSlack(reportZip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}