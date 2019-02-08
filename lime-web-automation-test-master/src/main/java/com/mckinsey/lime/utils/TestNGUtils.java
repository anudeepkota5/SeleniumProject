package com.mckinsey.lime.utils;

import com.mckinsey.lime.apiUtils.ApiCallDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class TestNGUtils {
    public static String getTestMethodUniqueName(Object o, WebDriver driver) {
        //TODO: Need to improve
        String sessionID = ((RemoteWebDriver) driver).getSessionId().toString();
        return o.getClass().getName().substring(0, o.getClass().getName().lastIndexOf("$")).replace(".", "_") + "_" + o.getClass().getEnclosingMethod().getName() + "_" + sessionID;
    }

    public static void addApiCallDetailsObj(ITestContext context, ApiCallDetails apiCallDetailsObj) {
        Object apiCallDetails1 = context.getAttribute(TestNgCustomStrings.API_CALL_DETAILS);
        List<ApiCallDetails> apiCallDetails = new ArrayList<>();
        if (apiCallDetails1 != null)
            apiCallDetails = (ArrayList<ApiCallDetails>) apiCallDetails1;

        apiCallDetailsObj.setApiCallCount(apiCallDetails.size() + 1);
        apiCallDetails.add(apiCallDetailsObj);

        context.setAttribute(TestNgCustomStrings.API_CALL_DETAILS, apiCallDetails);
    }

    public static List<ApiCallDetails> getApiCallDetailsObj(ITestContext context) {
        Object apiCallDetails1 = context.getAttribute(TestNgCustomStrings.API_CALL_DETAILS);
        List<ApiCallDetails> apiCallDetails = new ArrayList<>();
        if (apiCallDetails1 != null)
            apiCallDetails = (ArrayList<ApiCallDetails>) apiCallDetails1;
        return apiCallDetails;
    }

    public String getTestMethodUniqueName(ITestContext context) {
        //TODO: Need to improve
//        String sessionID = getSessionID();
//        return getTestMethodPackage().replace(".", "_") + "_" + getTestMethodClass() + "_" + getTestMethodName() + "_" + sessionID;
        return "";
    }

    public String getTestMethodPackage(ITestContext context) {
//        return context.getTestClass().getRealClass().getPackage().getPlanTitle();
        return "";
    }

    public String getTestMethodPackage(ITestResult result) {
        return result.getTestClass().getRealClass().getPackage().getName();
    }

    public static void setAssertionsStatusForTestMethod(ITestContext context) {
        System.out.println("Setting Assertion status to FAIL");
        if (getAssertionsStatusForTestMethod(context).isEmpty())
            context.setAttribute(TestNgCustomStrings.TEST_METHOD_STATUS, "fail");
    }

    public static String getAssertionsStatusForTestMethod(ITestContext context) {
        Object attribute = context.getAttribute(TestNgCustomStrings.TEST_METHOD_STATUS);

        if (attribute == null) return "";
        else return attribute.toString();
    }
}
