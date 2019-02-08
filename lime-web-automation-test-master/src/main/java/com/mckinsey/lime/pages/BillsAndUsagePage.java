package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@Deprecated
public class BillsAndUsagePage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "Bill And Usage Page";
    }

    protected final By expandMibileBillDetails = LocatorUtils.getLocator(PAGE_NAME, "expandMibileBillDetails");
    protected final By billingSummarySectionHeader = LocatorUtils.getLocator(PAGE_NAME, "billingSummarySectionHeader");
    protected final By currentPlanName = LocatorUtils.getLocator(PAGE_NAME, "currentPlanName");
    protected final By extraPackagesText = LocatorUtils.getLocator(PAGE_NAME, "extraPackagesText");
    protected final By currentUsageTab = LocatorUtils.getLocator(PAGE_NAME, "currentUsageTab");
    protected final By billDetailsTab = LocatorUtils.getLocator(PAGE_NAME, "billDetailsTab");
    protected final By billDetails_ViewPaymentHistoryLink = LocatorUtils.getLocator(PAGE_NAME, "billDetails_ViewPaymentHistoryLink");
    protected final By currentUsage_talk = LocatorUtils.getLocator(PAGE_NAME, "currentUsage_talk");
    protected final By currentUsage_data = LocatorUtils.getLocator(PAGE_NAME, "currentUsage_data");
    protected final By currentUsage_buyExtraPackPopuUpHeader = LocatorUtils.getLocator(PAGE_NAME, "currentUsage_buyExtraPackPopuUpHeader");
    protected final By currentUsage_seeOtherExtraPackages = LocatorUtils.getLocator(PAGE_NAME, "currentUsage_seeOtherExtraPackages");
    protected final By currentUsage_sharedNumbersLink = LocatorUtils.getLocator(PAGE_NAME, "currentUsage_sharedNumbersLink");
    protected final By search_CurrentPlanDetailsIcon = LocatorUtils.getLocator(PAGE_NAME, "search_CurrentPlanDetailsIcon");
    protected final By packageDetailsTab = LocatorUtils.getLocator(PAGE_NAME, "packageDetailsTab");
    protected final By packageDetails_voiceLabel = LocatorUtils.getLocator(PAGE_NAME, "packageDetails_voiceLabel");
    protected final By packageDetails_dataLabel = LocatorUtils.getLocator(PAGE_NAME, "packageDetails_dataLabel");
    protected final By packageDetails_wifiLabel = LocatorUtils.getLocator(PAGE_NAME, "packageDetails_wifiLabel");
    protected final By packageDetails_smsMmsLabel = LocatorUtils.getLocator(PAGE_NAME, "packageDetails_smsMmsLabel");
    protected final By extraPackageTab = LocatorUtils.getLocator(PAGE_NAME, "extraPackageTab");
    protected final By extraPackage_activeSection = LocatorUtils.getLocator(PAGE_NAME, "extraPackage_activeSection");
    protected final By extraPackage_inActiveSection = LocatorUtils.getLocator(PAGE_NAME, "extraPackage_inActiveSection");
    protected final By billDetails_pendingBill1 = LocatorUtils.getLocator(PAGE_NAME, "billDetails_pendingBill1");

    public BillsAndUsagePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public String getBillingSummarySectionHeader() {
        return ElementUtils.getText(driver, billingSummarySectionHeader);
    }

    public String getCurrentPlanNameText() {
        return ElementUtils.getText(driver, currentPlanName);
    }

    public String getExtraPackagesText() {
        return ElementUtils.getText(driver, extraPackagesText);
    }

    public String getCurrentUsageTabText() {
        return ElementUtils.getText(driver, currentUsageTab);
    }

    public String getBillDetailsTabext() {
        return ElementUtils.getText(driver, billDetailsTab);
    }

    public void clickBillDetailsTab() {
        ElementUtils.clickObject(driver, billDetailsTab);

        LaunchPageActions launchPage = new LaunchPageActions(driver);
        launchPage.waitForProgressBar();
    }

    public String getBillDetails_ViewPaymentHistoryLinkText() {
        return ElementUtils.getText(driver, billDetails_ViewPaymentHistoryLink);
    }

    public String getCurrentUsage_talkText() {
        return ElementUtils.getText(driver, currentUsage_talk);
    }

    public String getCurrentUsage_dataText() {
        return ElementUtils.getText(driver, currentUsage_data);
    }

    public String getCurrentUsage_buyExtraPackPopuUpText() {
        return ElementUtils.getText(driver, currentUsage_buyExtraPackPopuUpHeader);
    }

    public String getCurrentUsage_seeOtherExtraPackagesText() {
        return ElementUtils.getText(driver, currentUsage_seeOtherExtraPackages);
    }

    public String getCurrentUsage_sharedNumbersLinkText() {
        return ElementUtils.getText(driver, currentUsage_sharedNumbersLink);
    }

    public void clickSearch_CurrentPlanDetailsIcon() {
        ElementUtils.clickObject(driver, search_CurrentPlanDetailsIcon);
    }

    public void clickExpandMibileBillDetailsIcon() {
        ElementUtils.clickObject(driver, expandMibileBillDetails);
    }

    public String getPackageDetailsTabText() {
        return ElementUtils.getText(driver, packageDetailsTab);
    }

    public String getPackageDetails_voiceLabelText() {
        return ElementUtils.getText(driver, packageDetails_voiceLabel);
    }

    public String getPackageDetails_dataLabelText() {
        return ElementUtils.getText(driver, packageDetails_dataLabel);
    }

    public String getPackageDetails_wifiLabelText() {
        return ElementUtils.getText(driver, packageDetails_wifiLabel);
    }

    public String getPackageDetails_smsMmsLabelText() {
        return ElementUtils.getText(driver, packageDetails_smsMmsLabel);
    }

    public void clickExtraPackageTab() {
        ElementUtils.clickObject(driver, extraPackageTab);
    }

    public String getExtraPackage_activeSectionText() {
        return ElementUtils.getText(driver, extraPackage_activeSection);
    }

    public String getBillDetails_pendingBill1Text() {
        return ElementUtils.getText(driver, billDetails_pendingBill1).replace(" ", "");
    }

    public String getExtraPackage_inActiveSectionText() {
        return ElementUtils.getText(driver, extraPackage_inActiveSection);
    }

}
