package com.mckinsey.lime.init;

import com.mckinsey.lime.mobilePages.LandingPage;
import com.mckinsey.lime.mobilePages.SideMenu;
import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.testDataBeans.FilePaths;
import com.mckinsey.lime.testDataBeans.LocalEmulator;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.CustomLogStrings;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.JavaUtils;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

//TODO: Yet to refactor a bit more
public enum InitializeDriver implements LaunchApp {

    WEB_IOS_LOCAL_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getIosLocalSit();

            return getDesiredCapabilities(capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            IOSDriver driver = null;
            try {
                driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                JavaUtils.hardWait(1);
//                driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

                JavaUtils.hardWait(2);
                new LandingPage(driver).launchApp();

                new SideMenu(driver).switchToEnglishLocale();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    WEB_ANDROID_LOCAL_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getAndroidLocalSit();

            return getDesiredCapabilities1(capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            AndroidDriver driver = null;
            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                JavaUtils.hardWait(1);
//                driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

                JavaUtils.hardWait(2);
                DesktopPageObjects.commonPage(driver).waitForGlobalLoader();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    WEB_IOS_SAUCE_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getIosSauceSit();

            return getDesiredCapabilities_Sauce(jobName, capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            IOSDriver driver = null;

            try {
                //                driver = new AndroidDriver(new URL(TestData.getConfigData().getTestObjectURL()), capabilities);
                driver = new IOSDriver(new URL(TestData.getConfigData().getSauceLabURL()), capabilities);
                JavaUtils.hardWait(1);
//                driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

                JavaUtils.hardWait(1);
                new LandingPage(driver).launchApp();

                new SideMenu(driver).switchToEnglishLocale();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    WEB_ANDROID_SAUCE_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getAndroidSauceSit();

            return getDesiredCapabilities_Sauce(jobName, capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            AndroidDriver driver = null;

            try {
//                driver = new AndroidDriver(new URL(TestData.getConfigData().getTestObjectURL()), capabilities);
                driver = new AndroidDriver(new URL(TestData.getConfigData().getSauceLabURL()), capabilities);
                JavaUtils.hardWait(1);
//                driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

                JavaUtils.hardWait(1);
                new LandingPage(driver).launchApp();

                new SideMenu(driver).switchToEnglishLocale();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    WEB_IOS_LOCAL_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getIosLocalUat();

            return getDesiredCapabilities(capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            IOSDriver driver = null;
            try {
                driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    WEB_ANDROID_LOCAL_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getAndroidLocalUat();

            return getDesiredCapabilities1(capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            AndroidDriver driver = null;
            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                JavaUtils.hardWait(10);
//                driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    WEB_IOS_SAUCE_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getIosSauceUat();

            return getDesiredCapabilities_Sauce(jobName, capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            IOSDriver driver = null;

            try {
                //                driver = new AndroidDriver(new URL(TestData.getConfigData().getTestObjectURL()), capabilities);
                driver = new IOSDriver(new URL(TestData.getConfigData().getSauceLabURL()), capabilities);
                JavaUtils.hardWait(1);
//                driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

                JavaUtils.hardWait(1);
                new LandingPage(driver).launchApp();

                new SideMenu(driver).switchToEnglishLocale();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    WEB_ANDROID_SAUCE_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {

            Map<String, String> capabilities = TestData.getCapabilitiesData().getAndroidLocalUat();

            return getDesiredCapabilities_Sauce(jobName, capabilities);
        }

        @Override
        public AppiumDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            AndroidDriver driver = null;

            try {
//                driver = new AndroidDriver(new URL(TestData.getConfigData().getTestObjectURL()), capabilities);
                driver = new AndroidDriver(new URL(TestData.getConfigData().getSauceLabURL()), capabilities);
                JavaUtils.hardWait(10);
//                driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
                driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

                driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    },

    CHROME_LOCAL_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getChromeLocalSit();

            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            System.setProperty("webdriver.chrome.driver", FilePaths.DRIVER_CHROME);

            //TODO: Need to add Options/Capabilities that added from capabilities.yaml
            WebDriver driver = new ChromeDriver();
            JavaUtils.hardWait(1);
//          driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
            driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(-1, TimeUnit.SECONDS);

            driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            JavaUtils.hardWait(2);
            DesktopPageObjects.commonPage(driver).waitForGlobalLoader();
//          new LandingPage(driver).launchApp();

            return driver;
        }
    },

    CHROME_LOCAL_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getChromeLocalUat();

            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            System.setProperty("webdriver.chrome.driver", "/Users/hemasundarpenugonda/xebia/projects/mck/iservice-app-automation/src/main/resources/drivers/chromedriver");

            //TODO: Need to add Options/Capabilities that added from capabilities.yaml
            ChromeDriver driver = new ChromeDriver();
            JavaUtils.hardWait(1);
//          driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
            driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

            driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            DesktopPageObjects.commonPage(driver).waitForLoaders();
//            JavaUtils.hardWait(2);
//          new LandingPage(driver).launchApp();

            return driver;
        }
    },

    CHROME_SAUCE_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getChromeSauceSit();
            //TODO
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            ChromeOptions chromeOptions = new ChromeOptions().addArguments("--no-sandbox");
            caps.setCapability("chrome.switches", chromeOptions);
            return caps;
//            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {

//            System.setProperty("webdriver.chrome.driver", FilePaths.DRIVER_CHROME);

            //TODO: Need to add Options/Capabilities that added from capabilities.yaml
            WebDriver driver = null;
            try {
                driver = new RemoteWebDriver(new URL(TestData.getConfigData().getSauceLabURL()), capabilities);
                result.setAttribute(TestNgCustomStrings.DRIVER, driver);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
//            driver.manage().window().maximize();

            JavaUtils.hardWait(1);
//          driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
            driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(-1, TimeUnit.SECONDS);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(screenSize.width, screenSize.height));

            driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            JavaUtils.hardWait(2);
            DesktopPageObjects.commonPage(driver).waitForGlobalLoader();

            return driver;

        }
    },

    CHROME_SAUCE_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getChromeSauceUat();
            return DesiredCapabilities.chrome();
//            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {

//            System.setProperty("webdriver.chrome.driver", FilePaths.DRIVER_CHROME);

            //TODO: Need to add Options/Capabilities that added from capabilities.yaml
            WebDriver driver = null;
            try {
                driver = new RemoteWebDriver(new URL(TestData.getConfigData().getSauceLabURL()), capabilities);
                result.setAttribute(TestNgCustomStrings.DRIVER, driver);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
//            driver.manage().window().maximize();

            JavaUtils.hardWait(1);
//          driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
            driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(-1, TimeUnit.SECONDS);
            driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            JavaUtils.hardWait(2);
            DesktopPageObjects.commonPage(driver).waitForGlobalLoader();

            return driver;
        }
    },
    //TODO:
    FIREFOX_LOCAL_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getFirefoxLocalSit();

            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            System.setProperty("webdriver.gecko.driver", FilePaths.DRIVER_FIREFOX);

            //TODO: Need to add Options/Capabilities that added from capabilities.yaml
            WebDriver driver = new FirefoxDriver();
            JavaUtils.hardWait(1);
//          driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
            driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);

            driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            DesktopPageObjects.commonPage(driver).waitForLoaders();
//          new LandingPage(driver).launchApp();

            return driver;
        }
    },
    //TODO:
    FIREFOX_LOCAL_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getFirefoxLocalUat();

            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            return null;
        }
    },
    //TODO:
    FIREFOX_SAUCE_SIT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getFirefoxSauceSit();
            return DesiredCapabilities.firefox();
//            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            //TODO: Need to add Options/Capabilities that added from capabilities.yaml
            WebDriver driver = null;
            try {
                driver = new RemoteWebDriver(new URL(TestData.getConfigData().getSauceLabURL()), capabilities);
                result.setAttribute(TestNgCustomStrings.DRIVER, driver);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
//            driver.manage().window().maximize();

            JavaUtils.hardWait(1);
//          driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new CustomAppiumListener());
            driver.manage().timeouts().implicitlyWait(TestData.getConfigData().getImplicitTimeOut(), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(screenSize.width, screenSize.height));

            driver.get(TestData.getConfigData().getAppEnvironment().getWebAppUrl());

            JavaUtils.hardWait(2);
            DesktopPageObjects.commonPage(driver).waitForGlobalLoader();

            return driver;
        }
    },
    //TODO:
    FIREFOX_SAUCE_UAT {
        @Override
        public DesiredCapabilities setCapabilities(String jobName) {
            Map<String, String> capabilities = TestData.getCapabilitiesData().getFirefoxSauceUat();

            return getDesiredCapabilities(capabilities);
        }

        @Override
        public WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result) {
            return null;
        }
    };

    private static DesiredCapabilities getDesiredCapabilities_Sauce(String jobName, Map<String, String> iOsCapabilities) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        iOsCapabilities.forEach((key, value) -> {
            capabilities.setCapability("name", jobName);
            capabilities.setCapability(key, value);
        });
        return capabilities;
    }

    private static DesiredCapabilities getDesiredCapabilities1(Map<String, String> iOsCapabilities) {
        List<LocalEmulator> localEmulators = TestData.getLocalEmulators();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        iOsCapabilities.forEach((key, value) -> {
            if (key.equalsIgnoreCase("appName")) {
                File appDir = new File(System.getProperty("user.dir") + "/resources/apps/");
                capabilities.setCapability(key, new File(appDir, value).getAbsolutePath());
            } else {
                capabilities.setCapability(key, value);
            }
        });
        Optional<LocalEmulator> first = localEmulators.stream()
                .filter(item -> !item.isAssigned())
                .findFirst();
        for (int i = 0; i < 2; i++) {
            if (first.isPresent()) {

                LocalEmulator localEmulator = first.get();
                capabilities.setCapability("deviceName", localEmulator.getDeviceName());
                capabilities.setCapability("platformVersion", localEmulator.getPlatformVersion());
                capabilities.setCapability("chromeDriverPort", localEmulator.getChromeDriverPort());
                capabilities.setCapability("systemPort", localEmulator.getSystemPort());
                capabilities.setCapability("udid", localEmulator.getUdid());

                localEmulator.setAssigned(true);
                break;
            } else {
                JavaUtils.hardWait(5 * 60);
            }
            CustomLogging.writeToReport(CustomLogStrings.ERROR_LOG + "Waited for 10 min for emulator/device availability. All the emulators/Devices are busy.");
        }
        return capabilities;
    }

    private static DesiredCapabilities getDesiredCapabilities(Map<String, String> iosCapabilities) {
        File appDir = new File(System.getProperty("user.dir") + "/src/main/resources/apps/");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        iosCapabilities.forEach((key, value) -> {
            if (key.equalsIgnoreCase("appName")) {
                capabilities.setCapability("app", new File(appDir, value).getAbsolutePath());
            } else {
                capabilities.setCapability(key, value);
            }
        });
        return capabilities;
    }
};


interface LaunchApp {
    DesiredCapabilities setCapabilities(String jobName);

    WebDriver startDriver(DesiredCapabilities capabilities, ITestResult result);
}
