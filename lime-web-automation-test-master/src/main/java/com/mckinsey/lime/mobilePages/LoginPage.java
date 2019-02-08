package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.Utilities;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class LoginPage extends MobileBasePage {
    private final By iFrame_LoginPopup = By.className("fancybox-iframe");
    private final By textBox_UserName = By.name("account");
    private final By textBox_Password = By.id("password");
    private final By button_LogIn = By.id("bt_signin");
    private final By icon_Close = By.id("bt_close");
    private final By text_ErrorUserName = By.xpath("//input[@name='account']/../following-sibling::small");
    private final By text_ErrorPassword = By.xpath("//input[@id='password']/../following-sibling::small");
    private final By text_ErrorLoginPopup = By.xpath("//div[@id='home']/div[1]/small");

    public LoginPage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void enterUserName(String textToEnter) {
        ElementUtils.sendKeys(driver, textBox_UserName, textToEnter);
    }

    public void enterPassword(String textToEnter) {
        ElementUtils.sendKeys(driver, textBox_Password, textToEnter);
    }

    public void clickLogInButton() {
        ElementUtils.clickObject(driver, button_LogIn);
        //TODO:
        driver.switchTo().defaultContent();
    }

    public String getErrorMessageForUserName() {
        return ElementUtils.getText(driver, text_ErrorUserName);
    }

    public String getErrorMessageForPassword() {
        return ElementUtils.getText(driver, text_ErrorPassword);
    }

    public String getErrorMessageForLoginPopup() {
        return ElementUtils.getText(driver, text_ErrorLoginPopup);
    }

    //TODO: Need to refactor
    public void closeLoginPopup() {
        ElementUtils.clickObject(driver, icon_Close);
        driver.switchTo().defaultContent();
    }

    //TODO: Need to refactor
    public void switchToLogInFrame() {
        driver.switchTo().frame(ElementUtils.getVisibleElement(driver, iFrame_LoginPopup));
    }


    //Update it with user type and fetch user details at run time
    public void loginIntoTrueApplication(String username, String password) {
        ElementUtils.clearTextField(driver, textBox_UserName);
        enterUserName(username);
        hideKeyboard();
//      ToDo: Work around for auto fill of userName text box
//        ElementUtils.clickObject(driver, textBox_password);
        enterPassword(password);
        hardWait(2);
        if (Utilities.getDeviceOS() == CommonEnums.DeviceType.ANDROID)
            hideKeyboard();
        clickLogInButton();


        LandingPage launchPage = new LandingPage(driver);
        launchPage.waitForPageToLoad();
//        launchPage.waitForProgressBar();
//        TODO: Need to verify for successful login
//		Assert.assertEquals(getTextUsingXpath("//XCUIElementTypeStaticText[@name=\"Login Successful!\"]"), "Login Successful!");
        hardWait(5);
        launchPage.waitForLoaders();

        CustomLogging.writeInfoLogToReport("User logged in with User name: " + username + "\n password: " + password + " successfully");

    }

    //Update it with user type and fetch user details at run time
    public void loginIntoTrueApplicationFromLandingPage(String username, String password) {
        new LandingPage(driver).navigateToLoginPage();

        loginIntoTrueApplication(username, password);

    }
}
