package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class PaymentsPopUp extends MobileBasePage {
    private final By button_continue = By.id("payment_mode_selection_popup_pay_button_button");
    private final By textBox_cardNumber = By.id("payment_popup_cardNumber_input_textbox_field");
    private final By textBox_cardHolderName = By.id("payment_popup_cardName_input_textbox_field");
    private final By textBox_expiryDate = By.id("payment_popup_cardDate_input_textbox_field");
    private final By textBox_cvv = By.id("payment_popup_cvvNumber_input_textbox_field");
    private final By button_Pay = By.id("payment_popup_pay_button_button");
    /**
     * Elements for Payment progress popup
     */
    private final By button_paymentProgress_OK = By.id("undefined_button");
    private final By text_paymentProgress_header = By.id("payment_progress_text");

    public PaymentsPopUp(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void enterCardNumber(String valueToEnter) {
        ElementUtils.sendKeys(driver, textBox_cardNumber, valueToEnter);
    }

    public void enterCardHolderName(String valueToEnter) {
        ElementUtils.sendKeys(driver, textBox_cardHolderName, valueToEnter);
    }

    public void enterExpiryDate(String valueToEnter) {
        ElementUtils.sendKeys(driver, textBox_expiryDate, valueToEnter);
    }

    public void enterCVV(String valueToEnter) {
        ElementUtils.sendKeys(driver, textBox_cvv, valueToEnter);
    }

    public void clickContinue() {
        ElementUtils.clickObject(driver, button_continue);
    }

    public void clickPay() {
        ElementUtils.clickObject(driver, button_Pay);
        //Wait for payment to Complete
        hardWait(4);
        waitForLoaders();
    }

    public boolean isPaymentProgressPopupDisplayed() {
        return ElementUtils.isElementDisplayed(driver, text_paymentProgress_header);
    }

    public void closePaymentProgressPopup() {
        ElementUtils.clickObject(driver, button_paymentProgress_OK);
    }
}
