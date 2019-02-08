package com.mckinsey.lime.pages;

import com.mckinsey.lime.guiDataBeans.ProductCardBean;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class FindMeAPlanPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "FindMeAPlanPage";
    }

    private final By pageHeading = LocatorUtils.getLocator(PAGE_NAME, "PageHeading");

    FindMeAPlanPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return ElementUtils.getText(driver, pageHeading);
    }

    public boolean isUserOnFindMeAPlanPage() {
        return getPageHeading().equalsIgnoreCase("Find me a plan");
    }

    private final By PlanDropDown = LocatorUtils.getLocator(PAGE_NAME, "PlanDropDown");
    private final By option1Dropdown = LocatorUtils.getLocator(PAGE_NAME, "Option1Dropdown");
    private final By option2Dropdown = LocatorUtils.getLocator(PAGE_NAME, "Option2Dropdown");
    private final By option3Dropdown = LocatorUtils.getLocator(PAGE_NAME, "Option3Dropdown");
    private final By shortlistButton = LocatorUtils.getLocator(PAGE_NAME, "ShortlistButton");
    private final By noPlansMessage = LocatorUtils.getLocator(PAGE_NAME, "NoPlansMessage");
    private final By planResults = LocatorUtils.getLocator(PAGE_NAME, "PlanResults");
    private final By closeNoPlansPopup = LocatorUtils.getLocator(PAGE_NAME, "CloseNoPlansPopup");

    public void clickShortlistNowButton() {
        ElementUtils.clickObject(driver, shortlistButton);
    }

    public void selectDropDown1ByVisibleText(String option) {
        final By dropDown1Option = LocatorUtils.getLocator(PAGE_NAME, "DropDown1OptionByText", new String[]{option});

        ElementUtils.selectDropDownValue(driver, PlanDropDown, dropDown1Option);
    }

    public void selectDropDown2ByVisibleText(String option) {
        final By dropDown2Option = LocatorUtils.getLocator(PAGE_NAME, "DropDown2OptionByText", new String[]{option});

        ElementUtils.selectDropDownValue(driver, option1Dropdown, dropDown2Option);
    }

    public void selectDropDown3ByVisibleText(String option) {
        final By dropDown3Option = LocatorUtils.getLocator(PAGE_NAME, "DropDown3OptionByText", new String[]{option});

        ElementUtils.selectDropDownValue(driver, option2Dropdown, dropDown3Option);
    }

    public void selectDropDown4ByVisibleText(String option) {
        final By dropDown4Option = LocatorUtils.getLocator(PAGE_NAME, "DropDown4OptionByText", new String[]{option});

        ElementUtils.selectDropDownValue(driver, option3Dropdown, dropDown4Option);
    }

    public String getNoPlansMessage() {
        return ElementUtils.getText(driver, noPlansMessage);
    }

    public int getNumberOfPlanResults() {
        return ElementUtils.numberOfElements(driver, planResults);
    }

    public List<ProductCardBean> getDisplayedPlanResults() {
        return ElementUtils.getVisibleElements(driver, planResults).stream()
                .map(item -> new ProductCardBean(null,
                        ElementUtils.getText(driver, item, By.id("undefined-plan-description")),
                        null,
                        ElementUtils.getText(driver, item, By.id("undefined-plan-title")),
                        ElementUtils.getText(driver, item, By.id("undefined-plan-subtitle"))))

                .collect(Collectors.toList());

    }

    public void closeNoPlansPopup() {
        ElementUtils.clickObject(driver, closeNoPlansPopup);
    }
}
