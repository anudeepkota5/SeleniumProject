package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class PayForOthersPage extends MobileBasePage {
    private final By textField_serviceNumber = By.id("pay_for_others_service_number_input");
    private final By button_next = By.id("pay_for_others_service_number_button_button");
    private final By text_ErrorMessage = By.id("pay_for_others_service_number_error_message");

    public PayForOthersPage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void enterServiceNumber(String serviceNumber) {
        ElementUtils.sendKeys(driver, textField_serviceNumber, serviceNumber);
        hideKeyboard();
    }

    public void clickNextButton() {
        ElementUtils.clickObject(driver, button_next);
        waitForLoaders();
    }

    public String getErrorMessage() {
        return ElementUtils.getText(driver, text_ErrorMessage);
    }
}
