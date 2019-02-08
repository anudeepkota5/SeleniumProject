package com.mckinsey.lime.pages;

import com.mckinsey.lime.guiDataBeans.ProductCardBean;
import com.mckinsey.lime.testDataBeans.Plan;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class OurPlansPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "OurPlansPage";
    }

    private final By pageHeading = LocatorUtils.getLocator(PAGE_NAME, "PageHeading");
    private final By pageHeadingDescription = LocatorUtils.getLocator(PAGE_NAME, "PageHeadingDescription");
    private final By protectionTab = LocatorUtils.getLocator(PAGE_NAME, "ProtectionTab");
    private final By savingsTab = LocatorUtils.getLocator(PAGE_NAME, "SavingsTab");
    private final By productBox = LocatorUtils.getLocator(PAGE_NAME, "ProductBox");
    private final By productHeader = LocatorUtils.getLocator(PAGE_NAME, "ProductHeader");
    private final By productTitle = LocatorUtils.getLocator(PAGE_NAME, "ProductTitle");
    private final By productSubTitle = LocatorUtils.getLocator(PAGE_NAME, "ProductSubTitle");
    private final By productDescription = LocatorUtils.getLocator(PAGE_NAME, "ProductDescription");
    private final By seeOtherIncomeProducts = LocatorUtils.getLocator(PAGE_NAME, "SeeOtherIncomeProducts");
    private final By popupHeader = LocatorUtils.getLocator(PAGE_NAME, "PopupHeader");
    private final By popupDescription = LocatorUtils.getLocator(PAGE_NAME, "PopupDescription");
    private final By stayHereButton = LocatorUtils.getLocator(PAGE_NAME, "StayHereButton");
    private final By seeOtherIncomeProductsButtonOnPopup = LocatorUtils.getLocator(PAGE_NAME, "SeeOtherIncomeProductsButtonOnPopup");
    private final By closePopupIcon = LocatorUtils.getLocator(PAGE_NAME, "ClosePopupIcon");
    private final By productBoxFooter = LocatorUtils.getLocator(PAGE_NAME, "ProductBoxFooter");

    OurPlansPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return ElementUtils.getText(driver, pageHeading);
    }

    public String getPageHeadingDescription() {
        return ElementUtils.getText(driver, pageHeadingDescription);
    }

    public boolean isProtectionTabSelected() {
        return ElementUtils.getCssAttribute(driver, protectionTab, "border-bottom-color").equalsIgnoreCase("rgba(245, 128, 35, 1)");
    }

    public boolean isSavingsTabSelected() {
        return ElementUtils.getCssAttribute(driver, savingsTab, "border-bottom-color").equalsIgnoreCase("rgba(245, 128, 35, 1)");
    }

    public boolean isUserOnOurPlansProtectionPlansTab() {
        return getPageHeading().equalsIgnoreCase("Our plans");
    }

    public List<ProductCardBean> getProductCardsDisplayed() {
        List<WebElement> visibleElements = ElementUtils.getVisibleElements(driver, productBox);

        List<ProductCardBean> productCardsDisplayed = new ArrayList<>();

        visibleElements.forEach(item -> {
            String currentProductHeader = ElementUtils.getText(driver, item, productHeader);
            String currentProductTitle = ElementUtils.getText(driver, item, productTitle);
            String currentProductSubTitle = ElementUtils.getText(driver, item, productSubTitle);
            String currentProductDescription = ElementUtils.getText(driver, item, productDescription);
            productCardsDisplayed.add(new ProductCardBean(currentProductHeader, currentProductDescription,
                    null, currentProductTitle, currentProductSubTitle));
        });
        return productCardsDisplayed;
    }

    //TODO: Need to delete
    @Deprecated
    public List<ProductCardBean> getComparableProductCardsDisplayed() {
        List<WebElement> visibleElements = ElementUtils.getVisibleElements(driver, productBox);

        List<ProductCardBean> productCardsDisplayed = new ArrayList<>();

        visibleElements.forEach(item -> {
            String currentProductHeader = ElementUtils.getText(driver, item, productHeader);
            String currentProductDescription = ElementUtils.getText(driver, item, productDescription);
            productCardsDisplayed.add(new ProductCardBean(currentProductHeader, currentProductDescription, null, "", ""));
        });
        return productCardsDisplayed;
    }

    public int getNumberOfProductCards() {
        return ElementUtils.numberOfElements(driver, productBox);
    }

    public void clickSeeOtherIncomeProductsButton() {
        ElementUtils.clickObject(driver, seeOtherIncomeProducts);
    }

    public boolean isPopupUpDisplayed() {
//        return ElementUtils.isElementDisplayed(driver, popupHeader);
        return ElementUtils.isElementDisplayed(driver, popupHeader, TestData.getConfigData().getExplicitTimeOutType2());
    }

    public String getPopupHeader() {
        return ElementUtils.getText(driver, popupHeader);
    }

    public String getPopupText() {
        return ElementUtils.getText(driver, popupDescription);
    }

    public void clickStayHere() {
        ElementUtils.clickObject(driver, stayHereButton);
    }

    public void clickSeeOtherIncomeProductsOnPopup() {
        ElementUtils.clickObject(driver, seeOtherIncomeProductsButtonOnPopup);
    }

    public void closePopup() {
        ElementUtils.clickObject(driver, closePopupIcon);
    }

    public void clickPlanCard(String title, String subTitle) {
        final By productBoxByNameAndTitle = LocatorUtils.getLocator(PAGE_NAME, "ProductBoxByNameAndTitle", new String[]{title, subTitle});

        WebElement visibleElement = ElementUtils.getVisibleElement(driver, productBoxByNameAndTitle);
        ElementUtils.clickObject(driver, visibleElement, productBoxFooter);
        waitForLoaders();
    }
    public void clickPlanCard(Plan plan) {
        plan.getPlanType().clickPlanType(driver);
        clickPlanCard(plan.getPlanTitle(), plan.getPlanSubTitle());
    }
    }
