package com.mckinsey.lime.tests;

import com.mckinsey.lime.init.BaseTest;
import com.mckinsey.lime.init.InitializeDriver;
import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.testDataBeans.LocalEmulator;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * This is core of framework wherein all the initializations are done.
 * Including browser configuration, page objects, config and yaml.
 */
public class TestSessionInitiator extends BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(groups = {}, alwaysRun = true)
//    @BeforeSuite(groups = {"mobileWeb"})
//    @BeforeTest
    public void _configureMobile(ITestContext testContext, XmlTest xmlTest, Method method, Object[] testArgs, ITestResult result) {
        System.out.println("BeforeTest Method");
        //		Define a enum in configDatabean class (Use existing enum)
        CommonEnums.TestEnvironment testEnvironment = TestData.getConfigData().getTestEnvironment();
        CommonEnums.AppType appType = TestData.getConfigData().getAppType();
        CommonEnums.AppEnvironment appEnvironment = TestData.getConfigData().getAppEnvironment();
        CommonEnums.DeviceType deviceType = TestData.getConfigData().getDeviceType();
        CommonEnums.BrowserType browserType = TestData.getConfigData().getBrowserType();
        String jobName = testContext.getName() + "_" + new Timestamp(System.currentTimeMillis()).toString();

        InitializeDriver driverType = getDriverType(deviceType, testEnvironment, appEnvironment, appType, browserType);
        DesiredCapabilities capabilities = driverType.setCapabilities(jobName);

        if (appType == CommonEnums.AppType.MOBILE
                && deviceType == CommonEnums.DeviceType.ANDROID) {

            String deviceName = capabilities.getCapability("deviceName").toString();
            TestData.getLocalEmulators().stream()
                    .filter(item -> item.getDeviceName().equalsIgnoreCase(deviceName))
                    .findFirst()
                    .ifPresent(localEmulator -> result.setAttribute(TestNgCustomStrings.LOCAL_EMULATOR, localEmulator));
        }
        driver = driverType.startDriver(capabilities, result);
        testContext.setAttribute(TestNgCustomStrings.DRIVER, getDriver());
//        result.setAttribute(TestNgCustomStrings.DRIVER, getDriver());
        testContext.setAttribute(TestNgCustomStrings.JOB_NAME, ((RemoteWebDriver) driver).getSessionId().toString());
        result.setAttribute(TestNgCustomStrings.JOB_NAME, ((RemoteWebDriver) driver).getSessionId().toString());

//        JavaUtils.hardWait(2);

    }

    private InitializeDriver getDriverType(CommonEnums.DeviceType deviceType, CommonEnums.TestEnvironment testEnvironment, CommonEnums.AppEnvironment appEnvironment, CommonEnums.AppType appType, CommonEnums.BrowserType browserType) {

        switch (appType) {
            case DESKTOP:
                switch (browserType) {
                    case CHROME:
                        switch (testEnvironment) {
                            case LOCAL:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.CHROME_LOCAL_SIT;
                                    case UAT:
                                        return InitializeDriver.CHROME_LOCAL_UAT;
                                }

                            case SAUCE:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.CHROME_SAUCE_SIT;
                                    case UAT:
                                        return InitializeDriver.CHROME_SAUCE_UAT;
                                }
                        }
                    case FIREFOX: {
                        switch (testEnvironment) {
                            case LOCAL:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.FIREFOX_LOCAL_SIT;
                                    case UAT:
                                        return InitializeDriver.FIREFOX_LOCAL_UAT;
                                }
                            case SAUCE:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.FIREFOX_SAUCE_SIT;
                                    case UAT:
                                        return InitializeDriver.FIREFOX_SAUCE_UAT;
                                }
                        }
                    }

                }
            case MOBILE:
                switch (deviceType) {
                    case IOS:
                        switch (testEnvironment) {
                            case LOCAL:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.WEB_IOS_LOCAL_SIT;
                                    case UAT:
                                        return InitializeDriver.WEB_IOS_LOCAL_UAT;
                                }

                            case SAUCE:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.WEB_IOS_SAUCE_SIT;
                                    case UAT:
                                        return InitializeDriver.WEB_IOS_SAUCE_UAT;
                                }
                        }
                    case ANDROID: {
                        switch (testEnvironment) {
                            case LOCAL:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.WEB_ANDROID_LOCAL_SIT;
                                    case UAT:
                                        return InitializeDriver.WEB_ANDROID_LOCAL_UAT;
                                }
                            case SAUCE:
                                switch (appEnvironment) {
                                    case SIT:
                                        return InitializeDriver.WEB_ANDROID_SAUCE_SIT;
                                    case UAT:
                                        return InitializeDriver.WEB_ANDROID_SAUCE_UAT;
                                }
                        }
                    }

                }
            default:
                return InitializeDriver.CHROME_LOCAL_SIT;
        }

    }

    @AfterMethod(groups = {}, alwaysRun = true)
//    @AfterSuite(alwaysRun = true)
    public void quitSession(ITestContext testContext, XmlTest xmlTest, Method method, Object[] testArgs, ITestResult result) {
        CustomLogging.witeInfo("AfterTest Method");
        DesktopPageObjects.killPageObjects();

        if (getDriver() != null)
            getDriver().quit();
        Object localEmulator = result.getAttribute(TestNgCustomStrings.LOCAL_EMULATOR);
        if (localEmulator != null) {
            ((LocalEmulator) localEmulator).setAssigned(false);
            CustomLogging.writeInfoLogToReport("Released emulator: " + ((LocalEmulator) localEmulator).getDeviceName());
        }
    }

    @AfterSuite
    public void afterSuite(ITestContext context) {
        CustomLogging.witeInfo("AfterSuite Method");
        long i = context.getEndDate().getTime() - context.getStartDate().getTime();
        CustomLogging.witeInfo("Total Min: " + ((i / 1000) / 60));

    }

}
