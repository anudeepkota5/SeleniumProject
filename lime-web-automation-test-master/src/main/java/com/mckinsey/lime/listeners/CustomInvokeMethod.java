package com.mckinsey.lime.listeners;

import com.mckinsey.lime.init.BaseTest;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class CustomInvokeMethod implements IInvokedMethodListener2 {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (method.isTestMethod()) {
//          String status = TestNGUtils.getAssertionsStatusForTestMethod(testResult.getTestContext());
            boolean failedAssertion = ((BaseTest) testResult.getInstance()).isAnyAssertionFailed();
            if (failedAssertion) {
                if (testResult.getThrowable() == null) {
                    testResult.setStatus(ITestResult.FAILURE);
                    testResult.setThrowable(new Exception(TestNgCustomStrings.EXCEPTION_ASSERTIONS));
//                    System.out.println("Assertions Failed: Config method" + testResult.getMethod().getMethodName() + "_" + testResult.getTestClass().getName());
                }
            }
        }
    }
}
