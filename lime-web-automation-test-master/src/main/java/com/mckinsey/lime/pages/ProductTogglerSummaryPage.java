package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductTogglerSummaryPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "ProductTogglerSummaryPage";
    }

    private final By pageHeader = LocatorUtils.getLocator(PAGE_NAME, "PageHeader");
    private final By applyForThisPlan = LocatorUtils.getLocator(PAGE_NAME, "ApplyForThisPlan");
    private final By emailMeThisPlan = LocatorUtils.getLocator(PAGE_NAME, "emailMeThisPlan");

    ProductTogglerSummaryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public void clickApplyForThisPlanButton() {
        ElementUtils.clickObject(driver, applyForThisPlan);
    }

    public void clickEmailMeThisPlanButton() {
        ElementUtils.clickObject(driver, emailMeThisPlan);
    }
}
