package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class ProductCardPage extends DesktopBasePage {
    public static final String PAGENAME;

    static {
        PAGENAME = "ProductCard";
    }

    private final By totalAmount = LocatorUtils.getLocator(PAGENAME, "totalAmount");
    private final By payButton = LocatorUtils.getLocator(PAGENAME, "payButton");
    private final By productCard = LocatorUtils.getLocator(PAGENAME, "productCard");
    private final By expandIcon = LocatorUtils.getLocator(PAGENAME, "expandIcon");
    private final By productServiceNumber = LocatorUtils.getLocator(PAGENAME, "productServiceNumber");
    private final By productState = LocatorUtils.getLocator(PAGENAME, "productState");
    private final By productType = LocatorUtils.getLocator(PAGENAME, "productType");
    private final By productAmount = LocatorUtils.getLocator(PAGENAME, "productAmount");
    private final By productCheckBox = LocatorUtils.getLocator(PAGENAME, "productCheckBox");

    public ProductCardPage(WebDriver driver) {
        super(driver);
    }

    public String gettotalAmount() {
        return ElementUtils.getText(driver, totalAmount);
    }

    public void clickPayButton() {
        ElementUtils.clickObject(driver, payButton);
    }

    public boolean isProductCardDisplayed() {
        return ElementUtils.isElementDisplayed(driver, productCard);
    }

    public int numberOfProducts() {
        return ElementUtils.numberOfElements(driver, productCard);
    }

    public boolean isExpandIconDisplayed() {
        return ElementUtils.isElementDisplayed(driver, expandIcon);
    }

    public void clickExpandIcon() {
        ElementUtils.clickObject(driver, expandIcon);
    }

    public String getProductServiceNumber() {
        return ElementUtils.getText(driver, productServiceNumber).replace("-", "");
    }

    public String getProductState() {
        return ElementUtils.getText(driver, productState);
    }

    public String getProductType() {
        return ElementUtils.getText(driver, productType);
    }

    public String getProDuctAmount() {
        return ElementUtils.getText(driver, productAmount);
    }

    public boolean isProductCheckBoxSelected() {
        return ElementUtils.isElementDisplayed(driver, productCheckBox);
    }

    public void clickProductCheckbox() {
        ElementUtils.clickObject(driver, productCheckBox);
    }
}
