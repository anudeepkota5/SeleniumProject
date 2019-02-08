package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.JavaUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class LandingPage extends MobileBasePage {
    private final By button_LoginWithTrueID = By.id("login_button_button");
    private final By textField_serviceNumber = By.id("login_text_box_textbox_field");
    private final By button_PayNow = By.id("login_pay_button_button");
    private final By button_CheckUsage = By.xpath("//div[@id='login_pay_button_button']/../following-sibling::div[2]/div");
    private final By button_BuyExtraPack = By.xpath("//div[@id='login_pay_button_button']/../following-sibling::div[2]/div[2]");
    private final By button_PayForOthers = By.xpath("//div[@id='login_pay_button_button']/../following-sibling::div[2]/div[3]");
    private final By icon_myAccount_postLogin = By.id("mobile_header_profile_box_post_log_in");
    private final By button_myAccount_login = By.id("header_signup_login_login");
    private final By button_myAccount_register = By.id("header_signup_login_login");
    //    private final By globalLoader = By.xpath("//img[ends-with(@src, 'landingBannerLarge.png')]");
    private final By globalLoader = By.xpath("//img[contains(@src, 'landingBannerLarge.png')]");
    //    private final By globalLoader = By.xpath("//img[substring(@id, string-length(@src) - string-length('landingBannerLarge.png') +1) = 'landingBannerLarge.png')]");
    private final By signIn = By.id("header_signup_login_login");
    private final By logOut = By.id("header_signup_login_logout");
    //Loaders


    public LandingPage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void clickLoginButton() {
        ElementUtils.clickObject(driver, button_LoginWithTrueID);
    }

    public void enterServiceNumber(String serviceNumber) {
        ElementUtils.sendKeys(driver, textField_serviceNumber, serviceNumber);
    }

    public void clickPayNow() {
        ElementUtils.clickObject(driver, button_PayNow);
        hardWait(1);
        waitForLoaders();
    }

    public void clickCheckUsage() {
        ElementUtils.clickObject(driver, button_CheckUsage);
    }

    public void clickBuyExtraPack() {
        ElementUtils.clickObject(driver, button_BuyExtraPack);
    }

    public void clickPayForOthers() {
        ElementUtils.clickObject(driver, button_PayForOthers);
    }

    public void clickMyAccount() {
        ElementUtils.clickObject(driver, icon_myAccount_postLogin);
        JavaUtils.hardWait(1);
    }

    public void waitForPageToLoad() {
        JavaUtils.hardWait(1);
        ElementUtils.waitForElementNotDisplayed(driver, globalLoader);
//        JavaUtils.hardWait(1);

    }

    //TODO: Need to accept the Permission popups
    public void launchApp() {
        if (TestData.getConfigData().getDeviceType() == CommonEnums.DeviceType.ANDROID) {
            //switch to native context
            driver.context("NATIVE_APP");

            //find element with text attribute ALLOW and click it
            try {
                ElementUtils.clickObject(driver, By.xpath("//android.widget.Button[@text='ALLOW']"), TestData.getConfigData().getExplicitTimeOutType2());
            } catch (Exception e) {
            }
            hardWait(2);
            try {
                ElementUtils.clickObject(driver, By.xpath("//android.widget.Button[@text='ALLOW']"), TestData.getConfigData().getExplicitTimeOutType2());
            } catch (Exception e) {
            }

            //switch back to chrome context
            driver.context("CHROMIUM");
            hardWait(1);

            //TODO: Need to delete this logic by adding capability to accept all Permissions
//        LandingPage launchPage = new LandingPage(driver);
//        if (Utilities.getDeviceOS() == CommonEnums.DeviceType.IOS) {
//            launchPage.acceptNotifications();
//            CustomLogging.writeToReport("CustomLogStrings.INFO_LOG + "Notification accepted");
//        }
        } else if (TestData.getConfigData().getDeviceType() == CommonEnums.DeviceType.IOS) {
            hardWait(1);
            ElementUtils.acceptAlert(driver, TestData.getConfigData().getExplicitTimeOutType2());
            hardWait(1);
            ElementUtils.acceptAlert(driver, TestData.getConfigData().getExplicitTimeOutType2());

        }
    }

    public void clickLogOut() {
        ElementUtils.clickObject(driver, logOut);
        hardWait(5);
//        waitForLoaders();
        waitForPageLoaderToComplete();
        CustomLogging.writeInfoLogToReport("User logged out successfully");

    }

    public void logOutFromApp() {
        clickMyAccount();
        clickLogOut();
    }

    public void navigateToLoginPage() {

        clickLoginButton();
        new LoginPage(driver).switchToLogInFrame();

        CustomLogging.writeInfoLogToReport("Navigated to login page");
    }
}
