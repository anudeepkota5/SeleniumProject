package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectedRiderPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "SelectedRiderPage";
    }

    private final By pageHeader = LocatorUtils.getLocator(PAGE_NAME, "PageHeader");
    private final By yesPleaseProceed = LocatorUtils.getLocator(PAGE_NAME, "YesPleaseProceed");
    private final By noThanks = LocatorUtils.getLocator(PAGE_NAME, "NoThanks");
    private final By selectedRiderPremiumMessage = LocatorUtils.getLocator(PAGE_NAME, "SelectedRiderPremiumMessage");
    private final By sumAssuredAmount = LocatorUtils.getLocator(PAGE_NAME, "SumAssuredAmount");
    private final By premiumAmount = LocatorUtils.getLocator(PAGE_NAME, "PremiumAmount");

    SelectedRiderPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public String getSelectedRiderPremiumMessage() {
        return ElementUtils.getText(driver, selectedRiderPremiumMessage).replace("\n", " ").trim();
    }

    public void clickYesPleaseProceedButton() {
        ElementUtils.clickObject(driver, yesPleaseProceed);
    }

    public void clickNoThanksButton() {
        ElementUtils.clickObject(driver, noThanks);
    }

    public String getSumAssuredAmount() {
        return ElementUtils.getText(driver, sumAssuredAmount);
    }

    public String getPremiumAmount() {
        return ElementUtils.getText(driver, premiumAmount);
    }
}
