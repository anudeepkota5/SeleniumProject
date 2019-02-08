package com.mckinsey.lime.utils;

import com.mckinsey.lime.testDataBeans.LocatorsDataBean;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.regex.Pattern;

public class LocatorUtils {

    public static By getLocator(String pageName, String elementToken) {
        return getLocator(pageName, elementToken, new String[]{});
    }

    public static By getLocator(String pageName, String elementToken, String[] replacement) {
        LocatorsDataBean.indLocator locator = ObjectFileReader.getELementFromFile(pageName, elementToken);
        String byString = locator.getLocatorValue();
        //TODO: Refactor
        for (String indRep : replacement) {
            String substring = byString.substring(byString.indexOf("${"), byString.indexOf("}") + 1);
//            locator.setLocatorValue(locator.getLocatorValue().replaceFirst("\\$\\{.+\\}", indRep));}
            byString = byString.replaceFirst(Pattern.quote(substring), indRep);
        }
        return getBy(new LocatorsDataBean.indLocator(locator.getPageName(), locator.getLocatorName(), locator.getLocatorType(), byString));
    }

    /*public static By getLocator(String pageName, String elementToken, String specName, Boolean look) {
        return getLocator(pageName, elementToken, "", specName, look);
    }*/

    /*public static By getLocator(String pageName, String elementToken, String replacement, String specName, Boolean look) {
        String[] locator = ObjectFileReader.getELementFromFile(pageName, elementToken, specName, look);
        locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
        return getBy(locator[1].trim(), locator[2].trim());
    }*/

    /*public static By getLocator(String pageName, String elementToken, String replacement, String replace, String specName, Boolean look) {
        String[] locator = ObjectFileReader.getELementFromFile(pageName, elementToken, specName, look);
        locator[2] = locator[2].replaceAll("\\#\\{.+\\}", replace);
        locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
        return getBy(locator[1].trim(), locator[2].trim());
    }*/

    //TODO: to be Deleted
    private static By getBy(String locatorType, String locatorValue) {
        switch (CommonEnums.Locators.valueOf(locatorType.toUpperCase())) {
            case ID:
                return By.id(locatorValue);
            case XPATH:
                return By.xpath(locatorValue);
            case ACCESSIBILITY:
                return MobileBy.AccessibilityId(locatorValue);
            case CSS:
                return By.cssSelector(locatorValue);
            case NAME:
                return By.name(locatorValue);
            case CLASSNAME:
                return By.className(locatorValue);
            case LINKTEXT:
                return By.linkText(locatorValue);
            default:
                return By.id(locatorValue);
        }
    }

    private static By getBy(LocatorsDataBean.indLocator locator) {
        String locatorValue = locator.getLocatorValue();

        switch (locator.getLocatorType()) {
            case ID:
                return By.id(locatorValue);
            case XPATH:
                return By.xpath(locatorValue);
            case ACCESSIBILITY:
                return MobileBy.AccessibilityId(locatorValue);
            case CSS:
                return By.cssSelector(locatorValue);
            case NAME:
                return By.name(locatorValue);
            case CLASSNAME:
                return By.className(locatorValue);
            case LINKTEXT:
                return By.linkText(locatorValue);
            default:
                return By.id(locatorValue);
        }
    }
}
