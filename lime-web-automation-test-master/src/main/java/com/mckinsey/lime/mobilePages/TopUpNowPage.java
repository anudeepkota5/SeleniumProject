package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class TopUpNowPage extends MobileBasePage {
    private final By button_topUp = By.xpath("//div[@id='prepaid-top-up_button']/div");
    private final By amount_50 = By.id("prepaid-top-up-0");

    public TopUpNowPage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void clickTopUpButton() {
        ElementUtils.clickObject(driver, button_topUp);
        waitForLoaders();
    }

    public void selectAmount50() {
        ElementUtils.clickObject(driver, amount_50);
        waitForLoaders();
    }

}
