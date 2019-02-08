package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Deprecated
public class PaymentFlow extends DesktopBasePage {
    public static final String PAGENAME;

    static {
        PAGENAME = "PaymentFlow";
    }

    private final By close = LocatorUtils.getLocator(PAGENAME, "close");
    private final By pageHeader = LocatorUtils.getLocator(PAGENAME, "pageHeader");
    private final By creditCardRadioButton = LocatorUtils.getLocator(PAGENAME, "creditCardRadioButton");
    private final By confirmButton = LocatorUtils.getLocator(PAGENAME, "confirmButton");
    private final By creditCardNumbertextBox = LocatorUtils.getLocator(PAGENAME, "creditCardNumbertextBox");
    private final By cardHolderNameTextBox = LocatorUtils.getLocator(PAGENAME, "cardHolderNameTextBox");
    private final By expiryDate = LocatorUtils.getLocator(PAGENAME, "expirydate");
    private final By securityCode = LocatorUtils.getLocator(PAGENAME, "securityCode");
    private final By payButton = LocatorUtils.getLocator(PAGENAME, "payButton");
    private final By successIcon = LocatorUtils.getLocator(PAGENAME, "successIcon");

    public PaymentFlow(WebDriver driver) {
        super(driver);
    }

    public void clickClose() {
        ElementUtils.clickObject(driver, close);
    }

    public void clickConfirmButton() {
        ElementUtils.clickObject(driver, confirmButton);
    }

    public void clickpayButton() {
        if (driver instanceof IOSDriver) {
            ElementUtils.clickObject(driver, expiryDate);
            hardWait(0.5);
        }
        ElementUtils.clickObject(driver, payButton);
        ElementUtils.clickObject(driver, payButton);
        new LaunchPageActions(driver).waitForProgressBar();
    }

    public void clickCreditCardRadioButton() {
        ElementUtils.clickObject(driver, creditCardRadioButton);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public void enterCreditCardNumber(String valueToEnter) {
        ElementUtils.sendKeys(driver, creditCardNumbertextBox, valueToEnter);
//        hideKeyboard();
    }

    public void enterCreditCardName(String valueToEnter) {
        if (driver instanceof AndroidDriver)
            ElementUtils.clickObject(driver, cardHolderNameTextBox);
        ElementUtils.sendKeys(driver, cardHolderNameTextBox, valueToEnter);
//        hideKeyboard();
    }

    public void enterexpiryDate(String valueToEnter) {
        if (driver instanceof AndroidDriver)
            ElementUtils.clickObject(driver, expiryDate);
        WebElement expiryElement = ElementUtils.getVisibleElement(driver, expiryDate);
        for (int i = 0; i < valueToEnter.length(); i++) {
            ElementUtils.setKeys(expiryElement, String.valueOf(valueToEnter.charAt(i)));
//            hardWait(0.5);
        }

//        hideKeyboard();
    }

    public void enterSecurityCode(String valueToEnter) {
        if (driver instanceof AndroidDriver)
            ElementUtils.clickObject(driver, securityCode);
        ElementUtils.sendKeys(driver, securityCode, valueToEnter);
//        hideKeyboard();
    }

    public boolean isSuccessIconDisplayed() {
        return ElementUtils.isElementDisplayed(driver, successIcon);
    }
}
