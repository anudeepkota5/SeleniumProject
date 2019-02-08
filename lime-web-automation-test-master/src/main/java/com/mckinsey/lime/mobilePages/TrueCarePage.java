package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class TrueCarePage extends MobileBasePage {

    private final By manageMyPackage = By.xpath("//div[./span[starts-with(@class, 'icon-manage-my-package')]]");
    private final By reportAProblem = By.xpath("//div[./span[starts-with(@class, 'icon-report-a-problem')]]");
    private final By trackDeviceRepair = By.xpath("//div[./span[starts-with(@class, 'icon-track-my-request')]]");
    private final By redeemAt7Eleven = By.xpath("//div[./span[starts-with(@class, 'icon-redeem-at-seven-eleven')]]");
    private final By backIcon = By.xpath("//div[./span[starts-with(@class, 'icon-back_arrow')]]");
    private final By startChatWithExpert = By.id("chatbox_footer");
    private final By typeMessage = By.id("message-to-send");
    private final By button_sendMessage = By.id("btn-send");


    public TrueCarePage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void clickManageMyPackage() {
        ElementUtils.clickObject(driver, manageMyPackage);
        waitForChatLoaders();
    }

    public void clickReportProblem() {
        ElementUtils.clickObject(driver, reportAProblem);
        waitForChatLoaders();
    }

    public void clickTrackDeviceRepair() {
        ElementUtils.clickObject(driver, trackDeviceRepair);
        waitForChatLoaders();
    }

    public void clickRedeemAt7Eleven() {
        ElementUtils.clickObject(driver, redeemAt7Eleven);
        waitForChatLoaders();
    }

    public void clickBackIcon() {
        ElementUtils.clickObject(driver, backIcon);
        hardWait(1);
    }

    public void clickStartChat() {
        ElementUtils.clickObject(driver, startChatWithExpert);
        waitForChatLoaders();
        hardWait(4);
    }

    public void enterChatMessage(String val) {
        driver.switchTo().frame(ElementUtils.getVisibleElement(driver, By.xpath("//div[@id='expert_chat_loader_icon']/iframe")));
        ElementUtils.sendKeys(driver, typeMessage, val);
        driver.switchTo().defaultContent();
    }


    public void sendMessage() {
        driver.switchTo().frame(ElementUtils.getVisibleElement(driver, By.xpath("//div[@id='expert_chat_loader_icon']/iframe")));
        ElementUtils.clickObject(driver, button_sendMessage);
        driver.switchTo().defaultContent();
    }
}
