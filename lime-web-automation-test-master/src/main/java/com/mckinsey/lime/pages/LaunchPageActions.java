package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class LaunchPageActions extends DesktopBasePage {

    public static final String pageName;

    static {
        pageName = "Launch Page";

    }

    private final By button_loginWithTrueId = LocatorUtils.getLocator(pageName, "button_LoginWithTrueID");
    //=================================T O P / H E A D E R   P A N E L   E L E M E N T S============================================
    private final By progressBar = LocatorUtils.getLocator(pageName, "progressBar");
    private final By payNow = LocatorUtils.getLocator(pageName, "payNow");
    private final By msisdnTextBox = LocatorUtils.getLocator(pageName, "enterMobileNumber");
    private final By checkUsage = LocatorUtils.getLocator(pageName, "checkUsage");
    private final By buyExtraPack = LocatorUtils.getLocator(pageName, "buyExtraPack");
    private final By payForOthers = LocatorUtils.getLocator(pageName, "payForOthers");
    private final By bannerImage = LocatorUtils.getLocator(pageName, "bannerImage");
    private final By registerSection = LocatorUtils.getLocator(pageName, "registerSection");
    private final By iServiceTopBar = LocatorUtils.getLocator(pageName, "iServiceTopBar");

    public LaunchPageActions(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        CustomLogging.writeToReport("Clicking on Login button on Landing page.");
        ElementUtils.clickObject(driver, button_loginWithTrueId);
        hardWait(10);
    }

    public void acceptNotifications() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ElementUtils.acceptAlert(driver);
    }

    public void waitForProgressBar() {
        CustomLogging.writeToReport("Waiting for the progress to complete loading");
        ElementUtils.waitForElementNotDisplayed(driver, progressBar);
    }

    public void enterMsisdnorServiceNumber(String valToEnter) {
        CustomLogging.writeToReport("Entering value to landing page text box", valToEnter);
        ElementUtils.sendKeys(driver, msisdnTextBox, valToEnter);
//        if (driver instanceof AndroidDriver)
//            enterKeyBoard(driver);
//        else
//            hideKeyboard();
    }

    public void clearMsisdnorServiceNumber() {
        CustomLogging.writeToReport("Clearing value of landing page text box");
        ElementUtils.clearTextField(driver, msisdnTextBox);
//        hideKeyboard();
    }

    public void clickPayNowButton() {
        CustomLogging.writeToReport("Clicking on PayNow button");
//        if (driver instanceof AndroidDriver)
//            ElementUtils.clickObject(driver, payNow);
        ElementUtils.clickObject(driver, payNow);
        waitForProgressBar();
    }
}