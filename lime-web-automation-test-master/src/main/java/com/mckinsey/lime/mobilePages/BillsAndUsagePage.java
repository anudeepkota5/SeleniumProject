package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Deprecated
public class BillsAndUsagePage extends MobileBasePage {
    private final String PRODUCT_ID = "${productID}";
    private final By productCard = By.xpath("//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b')]]/../..");
    private final String XPATH_amountByProductID = "//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]//label[contains(@id, '_amount')]";
    private final By productCardHeading = By.xpath("//div[contains(@id, 'bills_usage_acc_card_content_') ]//div[contains(@id, 'bills_usage_acc_card_product_value_b')]/div[1]");
    private final By productExpandIcon = By.xpath("//i[@class='icon-chevron-down']");
    private final String XPATH_productExpandIconByProductID = "//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//*[@class='icon-chevron-down']";
    private final String XPATH_pricePlanHeaderByProductID = "//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//label[contains(@id, '_price_plan')]";
    private final String XPATH_pricePlanHeader4TflpByProductID = "(//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//label[contains(@id, '_price_plan')])[2]";
    private final String XPATH_viewPackageDetailIconByProductID = "//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//i[@class='icon-bills_usage_search-white']";
    private final String XPATH_viewPackageDetailIcon4TflpByProductID = "(//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//i[@class='icon-bills_usage_search-white'])[2]";
    private final By usageDetailsTab = By.id("undefined_second_label");
    private final By packageDetailsTab = By.id("undefined_first_label");
    private final By mainPackages = By.xpath("//div[@id='undefined_first_content']/div/div[contains(@id, 'bills_usage_package_details_')]");
    private final By mainAndExtraPackages = By.xpath("//div[@id='undefined_second_content']/div/div[starts-with(@id, 'postpaid_0_bills_usage_extra_package_')]");
    private final By billCycleDate = By.xpath("//div[@id='popupPanel__billCycle_Label']/following-sibling::label/span");
    private final By icon_popupClose = By.id("popupPanel_closeIcon");
    private final String XPATH_text_NumberOfExtraPackages = "//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//div[@class='Collapsible__contentInner']//*[contains(@id, '_extra_package')]";
    /**
     * Locators for TVS specific product
     */
    private final String XPATH_hdChannelCountByProductID = "(//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//div[contains(@id, 'bills_usage_vision_usage_') and contains(@id, '_hd_channel')])[3]";
    private final String XPATH_channelCountByProductID = "(//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//div[contains(@id, 'bills_usage_vision_usage_') and contains(@id, '_channel')])[7]";

    private final String XPATH_downloadSpeedByProductID = "(//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//div[contains(@id, 'bills_usage_online_usage_') and contains(@id, '_download_speed')])[3]";
    private final String XPATH_uploadSpeedByProductID = "(//div[contains(@id, 'bills_usage_acc_card_content_') and .//div[contains(@id, 'bills_usage_acc_card_product_value_b') and ./div[text()='" + PRODUCT_ID + "']]]/../..//div[contains(@id, 'bills_usage_online_usage_') and contains(@id, '_upload_speed')])[3]";

    private final By packageDetails_hdChannelCount = By.id("tvs_0_channel_count_hd");
    private final By packageDetails_channelCount = By.id("tvs_0_normal_channel_count");

    private final By packageDetails_downloadSpeed = By.id("bills_usage_online_usage_0bills_usage_online_details_package_download_value");
    private final By packageDetails_uploadSpeed = By.id("bills_usage_online_usage_0bills_usage_online_details_package_upload_value");

    public BillsAndUsagePage(WebDriver driver) {
        super((AppiumDriver) driver);
    }


    public String getDownloadSpeed_billsUsageScreen(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        String text = ElementUtils.getText(driver, By.xpath(XPATH_downloadSpeedByProductID.replace(PRODUCT_ID, formattedProductID)));
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }

    public String getUploadSpeed_billsUsageScreen(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        String text = ElementUtils.getText(driver, By.xpath(XPATH_uploadSpeedByProductID.replace(PRODUCT_ID, formattedProductID)));
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }

    public String getHdChannelsCount_billsUsageScreen(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        String text = ElementUtils.getText(driver, By.xpath(XPATH_hdChannelCountByProductID.replace(PRODUCT_ID, formattedProductID)));
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }

    public String getNormalChannelsCount_billsUsageScreen(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        String text = ElementUtils.getText(driver, By.xpath(XPATH_channelCountByProductID.replace(PRODUCT_ID, formattedProductID)));
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }

    public String getHdChannelsCount_packageDetailsScreen(ProductsResponse.IndividualProduct product) {

        String text = ElementUtils.getText(driver, packageDetails_hdChannelCount);
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }

    public String getNormalChannelsCount_packageDetailsScreen(ProductsResponse.IndividualProduct product) {
        String text = ElementUtils.getText(driver, packageDetails_channelCount);
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }


    public String getDownloadSpeed_packageDetailsScreen(ProductsResponse.IndividualProduct product) {

        String text = ElementUtils.getText(driver, packageDetails_downloadSpeed);
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }

    public String getUploadSpeed_packageDetailsScreen(ProductsResponse.IndividualProduct product) {
        String text = ElementUtils.getText(driver, packageDetails_uploadSpeed);
        if (text.equalsIgnoreCase("--"))
            return "0";
        else return text;
    }

    public int getNumberOfProducts() {
        return ElementUtils.getVisibleElements(driver, productCard).size();
    }

    //TODO: Need to addd Status as well for getting Amount
    public String getAmountForProducts(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        return ElementUtils.getText(driver, By.xpath(XPATH_amountByProductID.replace(PRODUCT_ID, formattedProductID))).replaceAll(",", "");
    }

    public boolean isAmountDisplayedForProduct(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        return ElementUtils.isElementDisplayed(driver, By.xpath(XPATH_amountByProductID.replace(PRODUCT_ID, formattedProductID)), TestData.getConfigData().getExplicitTimeOutType2());
    }

    public boolean isProductDisplayed(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        long count = ElementUtils.getVisibleElements(driver, productCardHeading).stream()
                .map(WebElement::getText)
                .filter(item -> item.equalsIgnoreCase(formattedProductID))
                .count();
        if (count > 0)
            return true;
        else return false;
    }

    private String getFormattedProduct(ProductsResponse.IndividualProduct product) {
        if (product.isTFLProduct()) {
            return "True Online +";
        } else {
            String productId = product.getProductId();
            StringBuilder productIdStr = new StringBuilder(productId);

            if (product.getProductType().equalsIgnoreCase("TrueMoveH")) {
                if (productId.length() == 10) {
                    productIdStr.insert(3, "-");
                    productIdStr.insert(7, "-");
                } else {
                    productIdStr = new StringBuilder(productId);
                }
            }
            return productIdStr.toString();
        }
    }

    public boolean isProductCardExpanded(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        waitForProductDetailsToLoad();
        return ElementUtils.isElementDisplayed(driver, By.xpath(XPATH_pricePlanHeaderByProductID.replace(PRODUCT_ID, formattedProductID)), TestData.getConfigData().getExplicitTimeOutType2());
    }

    public void clickProductExpandIcon(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        ElementUtils.clickObject(driver, By.xpath(XPATH_productExpandIconByProductID.replace(PRODUCT_ID, formattedProductID)));
        hardWait(1);
        waitForProductDetailsToLoad();
    }

    public void clickViewPackageDetailIcon(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        ElementUtils.clickObject(driver, By.xpath(XPATH_viewPackageDetailIconByProductID.replace(PRODUCT_ID, formattedProductID)));
        hardWait(1);
    }

    public void clickViewPackageDetailIcon4Tflp(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        ElementUtils.clickObject(driver, By.xpath(XPATH_viewPackageDetailIcon4TflpByProductID.replace(PRODUCT_ID, formattedProductID)));
        hardWait(1);
    }

    public void clickUsageDetailsTab() {
        ElementUtils.clickObject(driver, usageDetailsTab);
        hardWait(1);
    }

    public String getNumberOfExtraPackagesText(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        String text;
        try {
            text = ElementUtils.getText(driver,
                    By.xpath(XPATH_text_NumberOfExtraPackages.replace(PRODUCT_ID, formattedProductID)),
                    TestData.getConfigData().getExplicitTimeOutType2());

        } catch (TimeoutException e) {
            return "0";
        }
        text = text.replaceAll("\\(", "");
        text = text.split(" ")[0];
        return text;
    }

    public int getNumberOfMainPackages() {
        try {
            return ElementUtils.getVisibleElements(driver, mainPackages, TestData.getConfigData().getExplicitTimeOutType2()).size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public int getNumberOfMainAndExtraPackages() {
        try {
            return ElementUtils.getVisibleElements(driver, mainAndExtraPackages, TestData.getConfigData().getExplicitTimeOutType2()).size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public String getBillCycleDate() {
        return ElementUtils.getText(driver, billCycleDate);
    }

    public void closePopupUp() {
        ElementUtils.clickObject(driver, icon_popupClose);
    }

    public String getPricePlanName(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        return ElementUtils.getText(driver, By.xpath(XPATH_pricePlanHeaderByProductID.replace(PRODUCT_ID, formattedProductID)));
    }

    public String getPricePlanName4TFLP(ProductsResponse.IndividualProduct product) {
        final String formattedProductID = getFormattedProduct(product);

        return ElementUtils.getText(driver, By.xpath(XPATH_pricePlanHeader4TflpByProductID.replace(PRODUCT_ID, formattedProductID)));
    }

}
