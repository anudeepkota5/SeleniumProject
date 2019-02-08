package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

@Deprecated
public class EBillPage extends MobileBasePage {

    private final By eBillCard = By.id("bill_methods_billing_preferences_ebill_card");
    private final By paperBillCard = By.id("bill_methods_billing_preferences_paper_card");

    public EBillPage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public int numberOfEbillProducts() {
        try {
            return ElementUtils.getVisibleElements(driver, eBillCard, TestData.getConfigData().getExplicitTimeOutType2()).size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public int numberOfpaperBillProducts() {
        try {
            return ElementUtils.getVisibleElements(driver, paperBillCard, TestData.getConfigData().getExplicitTimeOutType2()).size();
        } catch (TimeoutException e) {
            return 0;
        }
    }
}
