package com.mckinsey.lime.utils;

import com.mckinsey.lime.listeners.CustomTestMethodResult;
import com.mckinsey.lime.testDataBeans.FilePaths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class SeleniumUtilities {


    public static void getScreenshot(ITestResult result, TakesScreenshot driver) {
        try {
            File screenshotFileObj = driver.getScreenshotAs(OutputType.FILE);


            FileUtils.copyFile(screenshotFileObj, new File(FilePaths.SCREENSHOTS_FOLDER + new CustomTestMethodResult(null, result).getTestMethodUniqueNameForScreenShot() + "_testFailure.png"));

        } catch (UnsupportedCommandException | NoSuchSessionException | IOException e) {
            //TODO:
            CustomLogging.writeToReport(CustomLogStrings.ERROR_LOG + "Couldn't take screenshot, must be because the driver object is terminated already.");
        }
    }

    public static void getScreenshot(CustomClass obj) {
        try {
            File screenshotFileObj = ((TakesScreenshot) obj.getDriver()).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshotFileObj, new File(FilePaths.SCREENSHOTS_FOLDER + TestNGUtils.getTestMethodUniqueName(obj.getObject(), obj.getDriver()) + "_" + obj.getFailedAssertionCount() + ".png"));
            obj.setFailedAssertionCount(obj.getFailedAssertionCount() + 1);
        } catch (UnsupportedCommandException | NoSuchSessionException | IOException e) {
            //TODO:
            CustomLogging.writeToReport(CustomLogStrings.ERROR_LOG + "Couldn't take screenshot, must be because the driver object is terminated already.");
        }
    }

    public static String getSessionID(WebDriver driver) {
        return ((RemoteWebDriver) driver).getSessionId().toString();
    }
}
