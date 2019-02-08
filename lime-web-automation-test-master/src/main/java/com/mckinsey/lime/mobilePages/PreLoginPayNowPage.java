package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class PreLoginPayNowPage extends MobileBasePage {
    private final By button_payNow = By.id("bills_usage_postpaid_paynow_button_button");
    private final By text_totalBalance = By.id("bills_usage_postpaid_total_amount");

    public PreLoginPayNowPage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void clickPayNow() {
        ElementUtils.clickObject(driver, button_payNow);
        waitForPageLoaderToComplete();
    }

    public boolean isPayButtonEnabled() {
        return ElementUtils.isElementDisplayed(driver, button_payNow);
    }

    public void getTotalBalance() {
        ElementUtils.getText(driver, text_totalBalance);
    }
}
