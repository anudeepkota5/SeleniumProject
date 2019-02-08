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
public class BuyExtraPackagePage extends MobileBasePage {

    private final static String PRODUCT_ID = "${PRODUCT_ID}";
    private final By dropDown = By.className("Select-control");
    private final By dropDownElements = By.xpath("//div[@class='Select-menu']/div[@role='option']");
    private final String XPATH_dropDownElementBYProductID = "//div[@class='Select-menu']/div[@role='option' and contains(@aria-label, '" + PRODUCT_ID + "')]";
    private final By noExtraPackMessage = By.xpath("//div[@id='buy_extra_package_tag_buy_extra_package']/following-sibling::div[2]");
    private final By voiceTab = By.xpath("//div[starts-with(@id, 'tmh_extra_tab_') and text()='Voice']");
    private final By voiceAndDataTab = By.xpath("//div[starts-with(@id, 'tmh_extra_tab_') and text()='Voice + Data' and @data-index>0]");
    private final By dataTab = By.xpath("//div[starts-with(@id, 'tmh_extra_tab_') and text()='Data' and @data-index>0]");
    private final By socialTab = By.xpath("//div[starts-with(@id, 'tmh_extra_tab_') and text()='Social' and @data-index>0]");
    private final By radioButton_oneTime = By.id("tmh_extra_packageradio_one_time_label");
    private final By radioButton_autoRenew = By.id("tmh_extra_packageradio_auto_label");
    private final By button_selectPackage = By.id("extraPackagesTMH_buy_button_button");
    private final By hotOffers_packages = By.xpath("//table[@id='extraPackagesTMH_hot_offers_table_component_table_parent']/tbody/tr");
    private final By packageCodes_packages = By.xpath("//table[@id='extraPackagesTMH_plan_codes_table_component_table_parent']/tbody/tr");

    public BuyExtraPackagePage(WebDriver driver) {
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

        waitForPageLoaderToComplete();
    }

    public String noExtraPackMessage() {
        return ElementUtils.getText(driver, noExtraPackMessage);
    }

    public void clickVoiceTab() {
        ElementUtils.clickObject(driver, voiceTab);
        waitForPageLoaderToComplete();
    }

    public void clickVoiceAndDataTab() {
        ElementUtils.clickObject(driver, voiceAndDataTab);
        waitForPageLoaderToComplete();
    }

    public void clickDataTab() {
        ElementUtils.clickObject(driver, dataTab);
        waitForPageLoaderToComplete();
    }

    public void clickSocialTab() {
        ElementUtils.clickObject(driver, socialTab);
        waitForPageLoaderToComplete();
    }

    public void selectOneTime() {
        ElementUtils.clickObject(driver, radioButton_oneTime);
    }

    public void selectAutoRenew() {
        ElementUtils.clickObject(driver, radioButton_autoRenew);
    }

    public void selectPackage() {
        ElementUtils.clickObject(driver, button_selectPackage);
    }

    public int getNumberOfHotOffers() {
        selectOneTime();
        int oneTimePacksSize = ElementUtils.getVisibleElements(driver, hotOffers_packages, TestData.getConfigData().getExplicitTimeOutType2()).size();
        selectAutoRenew();
        int autoRenewPacksSize = ElementUtils.getVisibleElements(driver, hotOffers_packages, TestData.getConfigData().getExplicitTimeOutType2()).size();

        return oneTimePacksSize + autoRenewPacksSize;

    }

    public int getNumberOfOtherPackages() {
        selectOneTime();
        int oneTimePacksSize = ElementUtils.getVisibleElements(driver, packageCodes_packages, TestData.getConfigData().getExplicitTimeOutType2()).size();
        selectAutoRenew();
        int autoRenewPacksSize = ElementUtils.getVisibleElements(driver, packageCodes_packages, TestData.getConfigData().getExplicitTimeOutType2()).size();

        return oneTimePacksSize + autoRenewPacksSize;

    }
}
