package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;

public class GetAQuotePage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "GetAQuotePage";
    }

    private final By pageHeader = LocatorUtils.getLocator(PAGE_NAME, "PageHeader");
    private final By planName = LocatorUtils.getLocator(PAGE_NAME, "PlanName");
    private final By viewDetailsButton = LocatorUtils.getLocator(PAGE_NAME, "ViewDetailsButton");
    private final By dobDay = LocatorUtils.getLocator(PAGE_NAME, "DOB_Day");
    private final By dobMonth = LocatorUtils.getLocator(PAGE_NAME, "DOB_Month");
    private final By dobYear = LocatorUtils.getLocator(PAGE_NAME, "DOB_Year");
    private final By genderMale = LocatorUtils.getLocator(PAGE_NAME, "Gender_Male");
    private final By genderFemale = LocatorUtils.getLocator(PAGE_NAME, "Gender_Female");
    private final By smokeYes = LocatorUtils.getLocator(PAGE_NAME, "Smoke_Yes");
    private final By smokeNo = LocatorUtils.getLocator(PAGE_NAME, "Smoke_No");
    private final By dobErrorMessage = LocatorUtils.getLocator(PAGE_NAME, "DOB_ErrorMessage");
    private final By genderErrorMessage = LocatorUtils.getLocator(PAGE_NAME, "Gender_ErrorMessage");
    private final By smokeErrorMessage = LocatorUtils.getLocator(PAGE_NAME, "Smoke_ErrorMessage");

    GetAQuotePage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public String getPlanName() {
        return ElementUtils.getText(driver, planName);
    }

    public void clickViewDetailsButton() {
        ElementUtils.clickObject(driver, viewDetailsButton);
    }

    public void selectDay(String day) {
        ElementUtils.selectDropDownValue(driver, dobDay, By.xpath(".//div[text()='" + day + "']"), true);
    }

    public void selectMonth(String month) {
        ElementUtils.selectDropDownValue(driver, dobMonth, By.xpath(".//div[text()='" + month + "']"), true);
    }

    public void selectYear(int year) {
        selectYear(String.valueOf(year));
    }

    public void selectYear(String year) {
        ElementUtils.selectDropDownValue(driver, dobYear, By.xpath(".//div[text()='" + year + "']"), true);
    }

    public void selectGenderMale() {
        ElementUtils.clickObject(driver, genderMale);
    }

    public void selectGenderFemale() {
        ElementUtils.clickObject(driver, genderFemale);
    }

    public void selectSmokeYes() {
        ElementUtils.clickObject(driver, smokeYes);
    }

    public void selectSmokeNo() {
        ElementUtils.clickObject(driver, smokeNo);
    }

    public String getDobErrorMessage() {
        return ElementUtils.getText(driver, dobErrorMessage);
    }

    public String getGenderErrorMessage() {
        try {
            return ElementUtils.getText(driver, genderErrorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public String getSmokeErrorMessage() {
        try {
            return ElementUtils.getText(driver, smokeErrorMessage);
        } catch (Exception e) {
            return "";
        }
    }

}
