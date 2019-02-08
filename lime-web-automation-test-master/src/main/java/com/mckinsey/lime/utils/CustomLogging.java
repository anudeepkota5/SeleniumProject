package com.mckinsey.lime.utils;

import org.apache.logging.log4j.LogManager;
import org.testng.Reporter;

public class CustomLogging {
    public static void writeToReport(String prefix, String testData) {
        Reporter.log(prefix + " <span class='testdata'>" + testData + "</span>");
        witeInfo(prefix + ": " + testData);
    }

    public static void writeTestDataToReport(String testData) {
        Reporter.log(CustomLogStrings.TEST_DATA + testData);
        witeInfo(CustomLogStrings.TEST_DATA + testData);
    }

    public static void writeToReport(String prefix, String infoMessage, String[] testDataToHighlight) {
        for (String str : testDataToHighlight) {
            infoMessage = infoMessage.replace(str, " <span class='testdata'>" + str + "</span>");
        }
        witeInfo(prefix + ": " + infoMessage);
    }

    public static void writeToReport(String prefix, String testData, boolean result) {
        Reporter.log(prefix + " <span class='testdata'>" + testData + "</span>");
        witeInfo(prefix + ": " + testData);
    }

    public static void writeToReport(String prefix) {
        Reporter.log(prefix);
        witeInfo(prefix);
    }

    public static void writeInfoLogToReport(String prefix) {
        Reporter.log(CustomLogStrings.INFO_LOG + prefix);
        witeInfo(prefix);
    }

    public static void witeInfo(Object str) {
        LogManager.getLogger(JavaUtils.getCallerClassName()).info(str.toString());
    }

}
