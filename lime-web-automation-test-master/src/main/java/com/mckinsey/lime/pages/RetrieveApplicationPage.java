package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RetrieveApplicationPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "RetrieveApplicationPage";
    }

    private final By pageHeading = LocatorUtils.getLocator(PAGE_NAME, "PageHeading");
    private final By resumeNowButton = LocatorUtils.getLocator(PAGE_NAME, "ResumeNowButton");

    RetrieveApplicationPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return ElementUtils.getText(driver, pageHeading);
    }

    public boolean isUserOnRetrieveApplicationPage() {
        return getPageHeading().equalsIgnoreCase("Welcome back!");
    }

    public void clickResumeNowButton() {
        ElementUtils.clickObject(driver, resumeNowButton);
    }
}
