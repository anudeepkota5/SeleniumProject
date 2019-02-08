package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WantARiderPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "WantARiderPage";
    }

    private final By pageHeader = LocatorUtils.getLocator(PAGE_NAME, "PageHeader");
    private final By earlyCancerWaiver = LocatorUtils.getLocator(PAGE_NAME, "EarlyCancerWaiver");
    private final By earlyProtectAccelerator = LocatorUtils.getLocator(PAGE_NAME, "EarlyProtectAccelerator");
    private final By noThanks = LocatorUtils.getLocator(PAGE_NAME, "NoThanks");
    private final By ddPremiumWaiver = LocatorUtils.getLocator(PAGE_NAME, "DDPremiumWaiver");
    private final By directDDForWholeLife = LocatorUtils.getLocator(PAGE_NAME, "DirectDDForWholeLife");
    private final By directDDForTerm = LocatorUtils.getLocator(PAGE_NAME, "DirectDDForTerm");
    private final By cancerPremiumWaiver = LocatorUtils.getLocator(PAGE_NAME, "CancerPremiumWaiver");
    private final By wantToChangeAmount = LocatorUtils.getLocator(PAGE_NAME, "WantToChangeAmount");
    private final By editAmountIcon = LocatorUtils.getLocator(PAGE_NAME, "editAmountIcon");
    private final By updateButton = LocatorUtils.getLocator(PAGE_NAME, "updateButton");
    private final By amountTextField = LocatorUtils.getLocator(PAGE_NAME, "amountTextField");
    private final By errorMsg_EditedField = LocatorUtils.getLocator(PAGE_NAME, "ErrorMsg_EditedField");

    WantARiderPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public void selectEarlyCancerWaiver() {
        ElementUtils.clickObject(driver, earlyCancerWaiver);
    }

    public void selectEarlyProtectAccelerator() {
        ElementUtils.clickObject(driver, earlyProtectAccelerator);
    }

    public void selectNoThanks() {
        ElementUtils.clickObject(driver, noThanks);
    }

    public void selectDdPremiumWaiver() {
        ElementUtils.clickObject(driver, ddPremiumWaiver);
    }

    public void selectDirectDDForWholeLife() {
        ElementUtils.clickObject(driver, directDDForWholeLife);
    }

    public void selectDirectDDForTerm() {
        ElementUtils.clickObject(driver, directDDForTerm);
    }

    public void selectCancerPremiumWaiver() {
        ElementUtils.clickObject(driver, cancerPremiumWaiver);
    }

    public void selectWantToChangeAmount() {
        ElementUtils.clickObject(driver, wantToChangeAmount);
    }

    public void clickEditAmountIcon() {
        ElementUtils.clickObject(driver, editAmountIcon);
        waitForLoaders();
    }

    public void clickUpdateButton() {
        ElementUtils.clickObject(driver, updateButton);
        waitForLoaders();
    }

    public void fillAmountTextField(String valueToEnter) {
        ElementUtils.sendKeys(driver, amountTextField, valueToEnter);
    }

    public String getEditedFieldErrorMessage() {
        return ElementUtils.getText(driver, errorMsg_EditedField);
    }


}
