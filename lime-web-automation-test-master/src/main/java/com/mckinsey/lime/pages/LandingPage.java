package com.mckinsey.lime.pages;

import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "LandingPage";
    }

    private final By incomeLogo = LocatorUtils.getLocator(PAGE_NAME, "IncomeLogo");
    private final By pageHeading = LocatorUtils.getLocator(PAGE_NAME, "PageHeading");
    private final By pageHeadingDescription = LocatorUtils.getLocator(PAGE_NAME, "PageHeadingDescription");
    private final By findMeAPlanBox = LocatorUtils.getLocator(PAGE_NAME, "FindMeAPlanBox");
    private final By mobile_findMeAPlanBox = LocatorUtils.getLocator(PAGE_NAME, "Mobile_FindMeAPlanBox");
    private final By totalControlMessage = LocatorUtils.getLocator(PAGE_NAME, "TotalControlMessage");
    private final By easyToUseMessage = LocatorUtils.getLocator(PAGE_NAME, "EasyToUseMessage");
    private final By protectionMaster = LocatorUtils.getLocator(PAGE_NAME, "ProtectionMaster");
    private final By savingsMaster = LocatorUtils.getLocator(PAGE_NAME, "SavingsMaster");
    private final By retrieveApplicationMaster = LocatorUtils.getLocator(PAGE_NAME, "RetrieveApplicationMaster");
    private final By helpIcon = LocatorUtils.getLocator(PAGE_NAME, "HelpIcon");

    LandingPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserOnLandingPage() {
        return ElementUtils.getText(driver, pageHeading).equalsIgnoreCase("Find yourself a life insurance");
    }

    public void clickIncomeLogo() {
        ElementUtils.clickObject(driver, incomeLogo);
    }

    public boolean isIncomeLogoDisplayed() {
        return ElementUtils.isElementDisplayed(driver, incomeLogo);
    }

    public String getPageHeading() {
        return ElementUtils.getText(driver, pageHeading);
    }

    public String getPageHeadingDescription() {
        return ElementUtils.getText(driver, pageHeadingDescription);
    }

    public void clickFindMePlanMaster() {
        if (TestData.getConfigData().isAndroidBrowser())
            ElementUtils.clickObject(driver, mobile_findMeAPlanBox);
        else
            ElementUtils.clickObject(driver, findMeAPlanBox);
    }

    public String getTotalControlMessage() {
        return ElementUtils.getText(driver, totalControlMessage);
    }

    public String getEasyToUseMessage() {
        return ElementUtils.getText(driver, easyToUseMessage);
    }

    public void clickProtectionMaster() {
        ElementUtils.clickObject(driver, protectionMaster);
        waitForLoaders();
    }

    public void clickSavingsMaster() {
        ElementUtils.clickObject(driver, savingsMaster);
        waitForLoaders();
    }

    public void clickRetrieveApplicationMaster() {
        ElementUtils.clickObject(driver, retrieveApplicationMaster);

    }

    public boolean isHelpIconDisplayed() {
        return ElementUtils.isElementDisplayed(driver, helpIcon);
    }
}
