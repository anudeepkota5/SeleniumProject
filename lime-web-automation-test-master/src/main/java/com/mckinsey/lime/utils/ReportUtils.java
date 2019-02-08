package com.mckinsey.lime.utils;

import com.mckinsey.lime.listeners.CustomTestMethodResult;
import com.mckinsey.lime.testDataBeans.FilePaths;
import com.mckinsey.lime.testDataBeans.HtmlReportStringsBean;
import com.mckinsey.lime.testDataBeans.TestData;
import org.testng.ISuiteResult;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportUtils {

    public static List<ITestResult> getAllTestMethodResults(ISuiteResult indTestResult) {
        Set<ITestResult> allPassedResults = indTestResult.getTestContext().getPassedTests().getAllResults();
        Set<ITestResult> allFailedResults = indTestResult.getTestContext().getFailedTests().getAllResults();
        Set<ITestResult> allSkippedResults = indTestResult.getTestContext().getSkippedTests().getAllResults();
        Set<ITestResult> allPartialSuccessResults = indTestResult.getTestContext().getFailedButWithinSuccessPercentageTests().getAllResults();
        Set<ITestResult> allFailedConfigurationResults = indTestResult.getTestContext().getFailedConfigurations().getAllResults();

        List<ITestResult> allResults = new ArrayList<>();
        allResults.addAll(allPassedResults);
        allResults.addAll(allFailedResults);
        allResults.addAll(allSkippedResults);
        allResults.addAll(allPartialSuccessResults);
        allResults.addAll(allFailedConfigurationResults);
        return allResults;
    }

    public static String prepareLogsPopupHtml(CustomTestMethodResult currentRow) {
        HtmlReportStringsBean htmlReportReplaceStrings = TestData.getHtmlReportStrings();

        String exceptionPopup;
        exceptionPopup = Utilities.readFile(FilePaths.CUSTOMREPORT2_TEMPLATE_POPUP);

        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupUniqueName(), currentRow.getTestMethodUniqueName());
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupBodyText(), currentRow.getStackTrace());
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupLinkName(), currentRow.getExceptionName());
        return exceptionPopup;
    }

    public static String prepareScreenshotPopupHtml(CustomTestMethodResult currentRow) {
        HtmlReportStringsBean htmlReportReplaceStrings = TestData.getHtmlReportStrings();

        String exceptionPopup;
        exceptionPopup = Utilities.readFile(FilePaths.CUSTOMREPORT2_TEMPLATE_POPUP_SCREENSHOT);
        String screenshotLinkTemplate = "<img src=\"${ScreenShotLink}\" class=\"imagepreview\" style=\"width: 100%;\"><br/>";
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupUniqueName(), currentRow.getTestMethodUniqueNameForScreenShot());

        String collect = "";
        try {
            collect = Files.list(Paths.get(FilePaths.SCREENSHOTS_FOLDER_REPORT))
                    .filter(Files::isRegularFile)
                    .map(item -> item.toFile().getName())
                    .filter(item -> item.startsWith(currentRow.getTestMethodUniqueNameForScreenShot()))
                    .map(item -> screenshotLinkTemplate.replace("${ScreenShotLink}", FilePaths.SCREENSHOTS_FOLDER_NAME + item))
                    .collect(Collectors.joining());

        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = FilePaths.SCREENSHOTS_FOLDER_NAME + currentRow.getTestMethodUniqueNameForScreenShot() + ".png";
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getTestMethodScreenShotLink(), collect);
        //exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupLinkName(), currentRow.getExceptionName());

        return exceptionPopup;
    }

    public static String prepareRequestPopupHtml(CustomTestMethodResult currentRow) {
        HtmlReportStringsBean htmlReportReplaceStrings = TestData.getHtmlReportStrings();

        String exceptionPopup;
        exceptionPopup = Utilities.readFile(FilePaths.CUSTOMREPORT2_TEMPLATE_REQUEST_POPUP);

        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupUniqueName(), currentRow.getTestMethodUniqueName());
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupBodyText(), currentRow.getRequest());
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupLinkName(), "Request");
        return exceptionPopup;
    }

    public static String prepareResponsePopupHtml(CustomTestMethodResult currentRow) {
        HtmlReportStringsBean htmlReportReplaceStrings = TestData.getHtmlReportStrings();

        String exceptionPopup;
        exceptionPopup = Utilities.readFile(FilePaths.CUSTOMREPORT2_TEMPLATE_RESPONSE_POPUP);

        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupUniqueName(), currentRow.getTestMethodUniqueName());
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupBodyText(), currentRow.getResponse());
        exceptionPopup = exceptionPopup.replace(htmlReportReplaceStrings.getPopupLinkName(), "Response");
        return exceptionPopup;
    }
}
