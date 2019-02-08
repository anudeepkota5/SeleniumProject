package com.mckinsey.lime.pages;

import com.mckinsey.lime.guiDataBeans.CompareSectionBean;
import com.mckinsey.lime.guiDataBeans.ProductCardBean;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.JavaUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComparePlansPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "ComparePlansPage";
    }

    private final By compareSection = LocatorUtils.getLocator(PAGE_NAME, "CompareSection");
    private final By compareSectionHeader = LocatorUtils.getLocator(PAGE_NAME, "CompareSectionHeader");
    private final By compareSectionTextLeft = LocatorUtils.getLocator(PAGE_NAME, "CompareSectionTextLeft");
    private final By compareSectionTextRight = LocatorUtils.getLocator(PAGE_NAME, "CompareSectionTextRight");
    private final By comparePlanHeaderLeft = LocatorUtils.getLocator(PAGE_NAME, "ComparePlanHeaderLeft");
    private final By comparePlanHeaderRight = LocatorUtils.getLocator(PAGE_NAME, "ComparePlanHeaderRight");
    private final By compareBoxes = LocatorUtils.getLocator(PAGE_NAME, "CompareBoxes");
    private final By compareBoxTitle = LocatorUtils.getLocator(PAGE_NAME, "CompareBoxTitle");
    private final By compareBoxSubTitle = LocatorUtils.getLocator(PAGE_NAME, "CompareBoxSubTitle");
    private final By comparePlansLink = LocatorUtils.getLocator(PAGE_NAME, "ComparePlansLink");
    private final By selectThisButton = LocatorUtils.getLocator(PAGE_NAME, "SelectThisButton");
    private final By compareNowButton = LocatorUtils.getLocator(PAGE_NAME, "CompareNowButton");

    ComparePlansPage(WebDriver driver) {
        super(driver);
    }

    public List<CompareSectionBean> getCompareSectionsDisplayed() {
        List<WebElement> visibleElements = ElementUtils.getVisibleElements(driver, compareSection);

        List<CompareSectionBean> compareSections = new ArrayList<>();

        visibleElements.forEach(item -> {
            String header = ElementUtils.getText(driver, item, compareSectionHeader);
            String textLeft = JavaUtils.trimStringOfMultipleLines(ElementUtils.getText(driver, item, compareSectionTextLeft));
            String textRight = JavaUtils.trimStringOfMultipleLines(ElementUtils.getText(driver, item, compareSectionTextRight));

            compareSections.add(new CompareSectionBean(header, textLeft, textRight, null));
        });
        return compareSections;
    }

    public List<ProductCardBean> getComparableBoxesDisplayed() {
        List<WebElement> visibleElements = ElementUtils.getVisibleElements(driver, compareBoxes);

        return visibleElements.stream()
                .map(item -> new ProductCardBean(null,
                        null,
                        null,
                        ElementUtils.getText(driver, item, compareBoxTitle),
                        ElementUtils.getText(driver, item, compareBoxSubTitle)))
                .collect(Collectors.toList());

    }

    public int getNumberOfCompareSectionsDisplayed() {
        return getCompareSectionsDisplayed().size();
    }

    public String getComparePlanHeaderLeft() {
        return ElementUtils.getText(driver, comparePlanHeaderLeft);
    }

    public String getComparePlanHeaderRight() {
        return ElementUtils.getText(driver, comparePlanHeaderRight);
    }

    public void clickComparePlansLink() {
        ElementUtils.clickObject(driver, comparePlansLink);
    }

    public void clickSelectThisButton(String title, String subTitle) {
        final By compareBoxByNameAndTitle = LocatorUtils.getLocator(PAGE_NAME, "CompareBoxByNameAndTitle", new String[]{title, subTitle});

        WebElement visibleElement = ElementUtils.getVisibleElement(driver, compareBoxByNameAndTitle);
        ElementUtils.clickObject(driver, visibleElement, selectThisButton);
    }

    public void clickCompareNowButton() {
        ElementUtils.clickObject(driver, compareNowButton);
    }
}
