package com.mckinsey.lime.testUtils;

import com.mckinsey.lime.utils.*;
import org.testng.Assert;

public class CustomAssertions {
    public static void assertText(CustomClass obj, String act, String exp, String logPreFix) {
        if (act.equalsIgnoreCase(exp))
            CustomLogging.writeToReport(CustomLogStrings.PASSED + logPreFix + " : \n\tActual: " + act + " \n\tExpected: " + exp);
        else {
            CustomLogging.writeToReport(CustomLogStrings.FAILED + logPreFix + " : \n\tActual: " + act + " \n\tExpected: " + exp);
//            TestNGUtils.setAssertionsStatusForTestMethod(obj.getContext());
            obj.getTestObject().setAnyAssertionFailed(true);
            SeleniumUtilities.getScreenshot(obj);

        }
    }

    public static void assertText(CustomClass obj, Object act, Object exp, String logPreFix) {
        if (act.equals(exp))
            CustomLogging.writeToReport(CustomLogStrings.PASSED + logPreFix + " : \n\tActual: " + act + " \n\tExpected: " + exp);
        else {
            CustomLogging.writeToReport(CustomLogStrings.FAILED + logPreFix + " : \n\tActual: " + act + " \n\tExpected: " + exp);
//            TestNGUtils.setAssertionsStatusForTestMethod(obj.getContext());
            obj.getTestObject().setAnyAssertionFailed(true);
            SeleniumUtilities.getScreenshot(obj);

        }
    }

    public static boolean assertText(CustomClass obj, int act, int exp, String logPreFix) {
        if (act == exp) {
            CustomLogging.writeToReport(CustomLogStrings.PASSED + logPreFix + " : \n\tActual: " + act + " \n\tExpected: " + exp);
            return true;
        } else {
            CustomLogging.writeToReport(CustomLogStrings.FAILED + logPreFix + " : \n\tActual: " + act + " \n\tExpected: " + exp);
//            TestNGUtils.setAssertionsStatusForTestMethod(obj.getContext());
            obj.getTestObject().setAnyAssertionFailed(true);
            SeleniumUtilities.getScreenshot(obj);
            return false;
        }
    }

    public static void assertText(String act, String exp) {
        Assert.assertEquals(act, exp);
        CustomLogging.writeToReport("", exp);
    }

    public static void assertTrue(CustomClass obj, boolean act) {
        assertTrue(obj, act, "");
    }

    public static void assertTrue(CustomClass obj, boolean act, String message) {
        if (act)
            CustomLogging.writeToReport(CustomLogStrings.PASSED + message);
        else {
            CustomLogging.writeToReport(CustomLogStrings.FAILED + message);
//            TestNGUtils.setAssertionsStatusForTestMethod(obj.getContext());
            obj.getTestObject().setAnyAssertionFailed(true);
            SeleniumUtilities.getScreenshot(obj);
        }

    }

    public static void assertFalse(CustomClass obj, boolean act) {
        assertFalse(obj, act, "");
    }

    public static void assertFalse(CustomClass obj, boolean act, String message) {
        if (!act)
            CustomLogging.writeToReport(CustomLogStrings.PASSED + message);
        else {
            CustomLogging.writeToReport(CustomLogStrings.FAILED + message);
//            TestNGUtils.setAssertionsStatusForTestMethod(obj.getContext());
            obj.getTestObject().setAnyAssertionFailed(true);
            SeleniumUtilities.getScreenshot(obj);
        }

    }

    public static void assertResponseCodeAs200(int responseCode, String message) {
        if (responseCode == 200) {
            CustomLogging.writeToReport(CustomLogStrings.PASSED + message + " : 200");
        } else {
            CustomLogging.writeToReport(CustomLogStrings.FAILED + message + " : " + responseCode);
            throw new AssertionError("ASSERTION FAILED: Expected response code: 200, Actual response code: " + responseCode);
        }

    }
}
