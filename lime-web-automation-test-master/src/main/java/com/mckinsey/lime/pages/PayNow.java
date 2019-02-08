package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class PayNow extends ProductCardPage {
    public static final String PAGENAME;

    static {
        PAGENAME = "PayNow";
    }

    private final By pageHeader = LocatorUtils.getLocator(PAGENAME, "pageHeader");
    private final By backButton = LocatorUtils.getLocator(PAGENAME, "backButton");

    public PayNow(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public boolean isBackButtonDisplayed() {
        return ElementUtils.isElementDisplayed(driver, backButton);
    }

}
