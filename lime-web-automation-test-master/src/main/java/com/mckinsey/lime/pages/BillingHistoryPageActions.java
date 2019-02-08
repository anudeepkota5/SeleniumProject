package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class BillingHistoryPageActions extends DesktopBasePage {
    public static final String PAGENAME;

    static {
        PAGENAME = "BillingHistory";
    }

    private final By label_NoPostPaidAccountsMessage = LocatorUtils.getLocator(PAGENAME, "label_NoPostPaidAccountsMessage");
    private final By dropdown_BillingHistoryProducts = LocatorUtils.getLocator(PAGENAME, "dropdown_BillingHistoryProducts");
    private final By labelExpandedDropdown_4PValueTrueSmartChoice = LocatorUtils.getLocator(PAGENAME, "labelExpandedDropdown_4PValueTrueSmartChoice");

    public BillingHistoryPageActions(WebDriver driver) {
        super(driver);
    }

    public String getNoPostPaidAccountsErrorMessage() {
        return ElementUtils.getText(driver, label_NoPostPaidAccountsMessage);
    }

    public void selectSmartChoiceProductFromBillingHistoryDropdown() {
        ElementUtils.clickObject(driver, dropdown_BillingHistoryProducts);
        ElementUtils.clickObject(driver, labelExpandedDropdown_4PValueTrueSmartChoice);
    }

    public String dropdownSelectedBillingHistoryProduct() {
        return ElementUtils.getText(driver, dropdown_BillingHistoryProducts);
    }

}
