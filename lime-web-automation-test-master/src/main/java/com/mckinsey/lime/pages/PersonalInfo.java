package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class PersonalInfo extends DesktopBasePage {
    public static final String PAGENAME;

    static {
        PAGENAME = "Personal Info";
    }

    public final By text_Name = LocatorUtils.getLocator(PAGENAME, "text_Name");
    public final By label_Name = LocatorUtils.getLocator(PAGENAME, "label_Name");
    public final By text_DisplayName = LocatorUtils.getLocator(PAGENAME, "text_DisplayName");
    public final By label_DisplayName = LocatorUtils.getLocator(PAGENAME, "label_DisplayName");
    public final By text_Privilege = LocatorUtils.getLocator(PAGENAME, "text_Privilege");
    public final By label_Privilege = LocatorUtils.getLocator(PAGENAME, "label_Privilege");
    public final By link_EditDisplayName = LocatorUtils.getLocator(PAGENAME, "link_EditDisplayName");
    public final By textbox_NewDisplayName = LocatorUtils.getLocator(PAGENAME, "textbox_NewDisplayName");
    public final By button_SaveDisplayName = LocatorUtils.getLocator(PAGENAME, "button_SaveDisplayName");

    public PersonalInfo(WebDriver driver) {
        super(driver);
    }

    public String getNameValue() {
        return ElementUtils.getText(driver, text_Name);
    }

    public String getDisplayNameValue() {
        return ElementUtils.getText(driver, text_DisplayName);
    }

    public String getPrivilegeValue() {
        return ElementUtils.getText(driver, text_Privilege);
    }

    public void clickEditDisplayNameLink() {
        ElementUtils.clickObject(driver, link_EditDisplayName);
    }

    public void enterNewDisplayName(String valueToEnter) {
        ElementUtils.sendKeys(driver, textbox_NewDisplayName, valueToEnter);
    }

    public void clickSaveDisplayName() {
        ElementUtils.clickObject(driver, button_SaveDisplayName);
    }
}
