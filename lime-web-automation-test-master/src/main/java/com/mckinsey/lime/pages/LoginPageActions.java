package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import com.mckinsey.lime.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class LoginPageActions extends DesktopBasePage {
    public static final String PAGENAME;

    static {
        PAGENAME = "Login Page";
    }

    public final By textBox_username = LocatorUtils.getLocator(PAGENAME, "textBox_username");
    public final By textBox_password = LocatorUtils.getLocator(PAGENAME, "textBox_password");
    public final By button_signIn = LocatorUtils.getLocator(PAGENAME, "button_signIn");
    public final By text_successfulMessage = LocatorUtils.getLocator(PAGENAME, "text_successfulMessage");
    public final By text_accountMissingError = LocatorUtils.getLocator(PAGENAME, "text_accountMissingError");
    public final By text_passwordMissingError = LocatorUtils.getLocator(PAGENAME, "text_passwordMissingError");
    public final By text_wrongPassword = LocatorUtils.getLocator(PAGENAME, "text_wrongPassword");
    public final By button_cancel = LocatorUtils.getLocator(PAGENAME, "button_cancel");

    public LoginPageActions(WebDriver driver) {
        super(driver);
    }

    public void clickCancelButton() {
        ElementUtils.clickObject(driver, button_cancel);
    }

    public void clickLogInButton() {
        ElementUtils.clickObject(driver, button_signIn);
        hardWait(10);
    }

    public String getAccountMissingError() {
        return ElementUtils.getText(driver, text_accountMissingError);
    }

    public String getPasswordMissingEoor() {
        return ElementUtils.getText(driver, text_passwordMissingError);
    }

    public String getWrongPasswordError() {
        return ElementUtils.getText(driver, text_wrongPassword);
    }

    public void enterUserName(String userName) {
        ElementUtils.sendKeys(driver, textBox_username, userName);
    }

    public void enterPassword(String pwd) {
        ElementUtils.sendKeys(driver, textBox_password, pwd);
    }

    //Update it with user type and fetch user details at run time
    public void loginIntoTrueApplication(String username, String password) {
        ElementUtils.clearTextField(driver, textBox_username);
        enterUserName(username);
//        hideKeyboard();
//      ToDo: Work around for auto fill of userName text box
//        ElementUtils.clickObject(driver, textBox_password);
        enterPassword(password);
        hardWait(2);
        if (Utilities.getDeviceOS() == CommonEnums.DeviceType.ANDROID)
//            hideKeyboard();
            clickLogInButton();

        LaunchPageActions launchPage = new LaunchPageActions(driver);
        launchPage.waitForProgressBar();
//		Assert.assertEquals(getTextUsingXpath("//XCUIElementTypeStaticText[@name=\"Login Successful!\"]"), "Login Successful!");
    }
}
