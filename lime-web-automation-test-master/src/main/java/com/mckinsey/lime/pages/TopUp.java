package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class TopUp extends DesktopBasePage {

    public static final String PAGENAME;

    static {
        PAGENAME = "TopUp";
    }

    private final By textBox_msisdn = LocatorUtils.getLocator(PAGENAME, "textBox_msisdn");
    private final By amount50 = LocatorUtils.getLocator(PAGENAME, "amount50");
    private final By amount100 = LocatorUtils.getLocator(PAGENAME, "amount100");
    private final By amount200 = LocatorUtils.getLocator(PAGENAME, "amount200");
    private final By amount300 = LocatorUtils.getLocator(PAGENAME, "amount300");
    private final By amount500 = LocatorUtils.getLocator(PAGENAME, "amount500");
    private final By amount800 = LocatorUtils.getLocator(PAGENAME, "amount800");
    private final By amount1000 = LocatorUtils.getLocator(PAGENAME, "amount1000");
    private final By amountMore = LocatorUtils.getLocator(PAGENAME, "amountMore");
    private final By button_TopUp = LocatorUtils.getLocator(PAGENAME, "button_TopUp");
    private final By button_TopUp_More = LocatorUtils.getLocator(PAGENAME, "button_TopUp_More");
    private final By textBox_Amount_More = LocatorUtils.getLocator(PAGENAME, "textBox_Amount_More");

    public TopUp(WebDriver driver) {
        super(driver);
    }

    public String getFilledMSISDN() {
        return ElementUtils.getText(driver, textBox_msisdn);
    }

    public void enterMSISDN(String valToEnter) {
        ElementUtils.sendKeys(driver, textBox_msisdn, valToEnter);
        clickAmount50();
        new LaunchPageActions(driver).waitForProgressBar();
    }

    public void clearMSISDN() {
        ElementUtils.clearTextField(driver, textBox_msisdn);
    }

    public void clickAmount50() {
        ElementUtils.clickObject(driver, amount50);
    }

    public void clickAmount100() {
        ElementUtils.clickObject(driver, amount100);
    }

    public void clickAmount200() {
        ElementUtils.clickObject(driver, amount200);
    }

    public void clickAmount300() {
        ElementUtils.clickObject(driver, amount300);
    }

    public void clickAmount500() {
        ElementUtils.clickObject(driver, amount500);
    }

    public void clickAmount800() {
        ElementUtils.clickObject(driver, amount800);
    }

    public void clickAmount1000() {
        ElementUtils.clickObject(driver, amount1000);
    }

    public void clickAmountMore() {
        ElementUtils.clickObject(driver, amountMore);
    }

    public void clickTopUpButton() {
        ElementUtils.clickObject(driver, button_TopUp);
        new LaunchPageActions(driver).waitForProgressBar();
    }

    public void clickTopUpMoreButton() {
        ElementUtils.clickObject(driver, button_TopUp_More);
        new LaunchPageActions(driver).waitForProgressBar();
    }

    public void enterTopUpMoreAmount(String valToEnter) {
        ElementUtils.sendKeys(driver, textBox_Amount_More, valToEnter);
    }
}
