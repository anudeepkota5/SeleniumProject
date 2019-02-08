package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.JavaUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileBasePage {

    private final By loader_ProductDetails = By.id("in_component_loader_icon");
    private final By loader_Loadable = By.id("loadable_common_loader_icon");
    private final By loader_Chat = By.id("chat_loader_icon");
    private final By loader_expertChat = By.id("expert_chat_loader_icon");
    private final By loader_common = By.id("common_loader_icon");

    protected AppiumDriver driver;

    public MobileBasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void hardWait(double seconds) {
        JavaUtils.hardWait(seconds);
    }

    public void hideKeyboard() {
        if (driver instanceof AppiumDriver) {
            try {
                ((AppiumDriver) driver).hideKeyboard();
            } catch (Exception e) {
            }
            hardWait(1);
        }
    }

    public void enterKeyBoard(WebDriver driver) {
        ((AndroidDriver) driver).pressKeyCode(66);
    }

    public void waitForLoaders() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_common);
        ElementUtils.waitForElementNotDisplayed(driver, loader_ProductDetails);
//        ElementUtils.waitForElementNotDisplayed(driver, loader_Chat);
//        ElementUtils.waitForElementNotDisplayed(driver, loader_expertChat);
        ElementUtils.waitForElementNotDisplayed(driver, loader_Loadable);
    }

    public void waitForChatLoaders() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_Chat);
        //TODO: Expert chat icon is always visible (need to discuss with MAHESH)
//        ElementUtils.waitForElementNotDisplayed(driver, loader_expertChat);
        ElementUtils.waitForElementNotDisplayed(driver, loader_Loadable);
    }

    public void waitForProductDetailsToLoad() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_ProductDetails);
    }

    public void waitForPageLoaderToComplete() {
        ElementUtils.waitForElementNotDisplayed(driver, loader_common);
    }

}
