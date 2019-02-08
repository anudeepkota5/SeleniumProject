package com.mckinsey.lime.init;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.JavaUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//TODO: Mobile & Destop Base pages to implement this class
public class BasePage {

    private final By loader_ProductDetails = By.id("in_component_loader_icon");
    private final By loader_Loadable = By.id("loadable_common_loader_icon");
    private final By loader_Chat = By.id("chat_loader_icon");
    private final By loader_expertChat = By.id("expert_chat_loader_icon");
    private final By loader_common = By.id("common_loader_icon");

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void hardWait(double seconds) {
        JavaUtils.hardWait(seconds);
    }

    @Deprecated
    public void waitForLoaders() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_common);
        ElementUtils.waitForElementNotDisplayed(driver, loader_ProductDetails);
//        ElementUtils.waitForElementNotDisplayed(driver, loader_Chat);
//        ElementUtils.waitForElementNotDisplayed(driver, loader_expertChat);
        ElementUtils.waitForElementNotDisplayed(driver, loader_Loadable);
    }

    @Deprecated
    public void waitForChatLoaders() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_Chat);
        //TODO: Expert chat icon is always visible (need to discuss with MAHESH)
//        ElementUtils.waitForElementNotDisplayed(driver, loader_expertChat);
        ElementUtils.waitForElementNotDisplayed(driver, loader_Loadable);
    }

    @Deprecated
    public void waitForProductDetailsToLoad() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_ProductDetails);
    }

    @Deprecated
    public void waitForPageLoaderToComplete() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_common);
    }

}
