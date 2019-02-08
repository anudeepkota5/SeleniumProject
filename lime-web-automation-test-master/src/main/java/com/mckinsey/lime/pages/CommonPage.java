package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.CustomExplicitWaits;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "CommonPage";
    }

    private final By homeNavigationIcon = LocatorUtils.getLocator(PAGE_NAME, "HomeNavigationIcon");
    private final By backNavigationIcon = LocatorUtils.getLocator(PAGE_NAME, "BackNavigationIcon");
    private final By globalLoader = LocatorUtils.getLocator(PAGE_NAME, "GlobalLoader");
    private final By nextButton = LocatorUtils.getLocator(PAGE_NAME, "NextButton");

    CommonPage(WebDriver driver) {
        super(driver);
    }

    public void clickHomeNavigationIcon() {
        ElementUtils.clickObject(driver, homeNavigationIcon);
        waitForLoaders();
    }

    public void clickBackNavigationIcon() {
        ElementUtils.clickObject(driver, backNavigationIcon);
        waitForLoaders();
    }

    public void waitForGlobalLoader() {
        ElementUtils.waitForElementNotDisplayed(driver, globalLoader);
        CustomExplicitWaits.waitForStaticDOM(driver);
    }

    public void clickNextButton() {
        ElementUtils.clickObject(driver, nextButton);
        waitForLoaders();
    }
}
