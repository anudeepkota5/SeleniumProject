package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlanDetailsPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "PlanDetailsPage";
    }

    private final By getAQuoteButton = LocatorUtils.getLocator(PAGE_NAME, "GetAQuote");
    private final By planTitle = LocatorUtils.getLocator(PAGE_NAME, "PlanTitle");
    private final By planSubTitle = LocatorUtils.getLocator(PAGE_NAME, "PlanSubTitle");

    PlanDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void clickGetAQuoteButton() {
        ElementUtils.clickObject(driver, getAQuoteButton);
        waitForLoaders();
    }

    public boolean isGetAQuoteButtonDisplayed() {
        return ElementUtils.isElementDisplayed(driver, getAQuoteButton);
    }

    public String getPlanTitle() {
        return ElementUtils.getText(driver, planTitle);
    }

    public String getPlanSubTitle() {
        return ElementUtils.getText(driver, planSubTitle);
    }
}
