package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class BillingHistoryPage extends MobileBasePage {

    private final By dropDown = By.className("Select-control");
    private final By dropDownElements = By.xpath("//div[@class='Select-menu']/div[@role='option']/div/div/div[1]");

    public BillingHistoryPage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void clickDropDown() {
        ElementUtils.clickObject(driver, dropDown);
    }

    public List<String> getProductsInDropDown() {
        List<WebElement> visibleElements = ElementUtils.getVisibleElements(driver, dropDownElements);

        List<String> collect = visibleElements.stream()
                .map(item -> ElementUtils.getText(item))
                .map(item -> item.replaceAll("-", ""))
                .collect(Collectors.toList());

        return collect;
    }
}
