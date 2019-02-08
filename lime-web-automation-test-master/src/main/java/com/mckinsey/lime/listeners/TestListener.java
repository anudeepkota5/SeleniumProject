package com.mckinsey.lime.listeners;

import com.mckinsey.lime.utils.SeleniumUtilities;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, IConfigurationListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Inside On Test Success method");
        long timeTaken = (result.getEndMillis() - result.getStartMillis()) / 1000;
        String str;
        if (timeTaken > 60) {
            timeTaken = (timeTaken / 60);
            str = timeTaken + " m";
        } else {
            str = timeTaken + " s";
        }
        System.out.println("Method " + result.getName() + " completed executing : " + str);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Inside On Test Failure method");

//        Object parameter = result.getParameters()[0];
        Object parameter = result.getTestContext().getAttribute(TestNgCustomStrings.DRIVER);
        if (!(result.getThrowable().getMessage().equalsIgnoreCase(TestNgCustomStrings.EXCEPTION_ASSERTIONS)))
            getScreenShot(result, parameter);

        System.out.println("Completed Test Failure method");

    }

    private void getScreenShot(ITestResult result, Object parameter) {
/*
        if (parameter instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) parameter;
            SeleniumUtilities.getScreenshot(result, driver);
        }*/
        SeleniumUtilities.getScreenshot(result, (TakesScreenshot) parameter);


    }


    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Method started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Method finished: " + context.getName());
    }

    @Override
    public void onConfigurationSuccess(ITestResult itr) {

    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        System.out.println("Inside Configuration Method Failure");

        Object parameter = result.getTestContext().getAttribute(TestNgCustomStrings.DRIVER);
        getScreenShot(result, parameter);

        System.out.println("Completed Test Failure method");
    }

    @Override
    public void onConfigurationSkip(ITestResult itr) {

    }
}
