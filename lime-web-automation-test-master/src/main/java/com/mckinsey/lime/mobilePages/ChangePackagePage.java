package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class ChangePackagePage extends MobileBasePage {

    private final static String PRODUCT_ID = "${PRODUCT_ID}";
    private final By dropDown = By.className("Select-control");
    private final By dropDownElements = By.xpath("//div[@class='Select-menu']/div[@role='option']");
    private final String XPATH_dropDownElementBYProductID = "//div[@class='Select-menu']/div[@role='option' and contains(@aria-label, '" + PRODUCT_ID + "')]";
    private final By noChangePackMessage = By.xpath("//div[@id='changepackage_change_package']/following-sibling::div[1]/div[2]");
    private final By noProducts4ChangePackMessage = By.xpath("//div[@id='changepackage_change_package']/following-sibling::div[1]");
    private final By radioButton_oneTime = By.id("tmh_extra_packageradio_one_time_label");
    private final By radioButton_autoRenew = By.id("tmh_extra_packageradio_auto_label");

    public ChangePackagePage(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void clickDropDown() {
        ElementUtils.clickObject(driver, dropDown);
    }

    public List<String> getProductsInDropDown() {
        List<WebElement> visibleElements = ElementUtils.getVisibleElements(driver, dropDownElements);

        List<String> collect = visibleElements.stream()
                .map(item -> ElementUtils.getText(item))
                .map(item -> item.substring(item.lastIndexOf(" ") + 1))
                .collect(Collectors.toList());

        return collect;
    }

    public void selectDropdownValueByProductID(ProductsResponse.IndividualProduct indProduct) {
        clickDropDown();
        ElementUtils.clickObject(driver,
                By.xpath(XPATH_dropDownElementBYProductID.replace(PRODUCT_ID, indProduct.getProductId())),
                TestData.getConfigData().getExplicitTimeOutType2());

        waitForLoaders();
    }

    public String noChangePackMessage() {
        return ElementUtils.getText(driver, noChangePackMessage);
    }

    public String noProducts4ChangePackMessage() {
        return ElementUtils.getText(driver, noProducts4ChangePackMessage);
    }

    public void selectOneTime() {
        ElementUtils.clickObject(driver, radioButton_oneTime);
    }

    public void selectAutoRenew() {
        ElementUtils.clickObject(driver, radioButton_autoRenew);
    }

}
