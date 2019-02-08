package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class TopUpNow extends TopUp {

    public static final String PAGENAME;

    static {
        PAGENAME = "TopUpNow";
    }

    private final By pageHeader = LocatorUtils.getLocator(PAGENAME, "pageHeader");
    private final By backButton = LocatorUtils.getLocator(PAGENAME, "backButton");

    public TopUpNow(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public boolean isBackButtonDisplayed() {
        return ElementUtils.isElementDisplayed(driver, backButton);
    }

    public void clickBackButton() {
        if (ElementUtils.isElementDisplayed(driver, backButton))
            ElementUtils.clickObject(driver, backButton);
    }
}
