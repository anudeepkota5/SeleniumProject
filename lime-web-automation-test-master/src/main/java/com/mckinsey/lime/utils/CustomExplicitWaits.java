package com.mckinsey.lime.utils;

import com.mckinsey.lime.testDataBeans.TestData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomExplicitWaits {


    public static ExpectedCondition<Boolean> getJQueryCondition() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return (((JavascriptExecutor) driver).executeScript("return jQuery.active").toString().equalsIgnoreCase("0"));
                } catch (Exception e) {
                    throw e;
//                    return true;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> getJSCondition() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");

            }
        };
    }

    public static ExpectedCondition<Boolean> getDOMCondition() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String previous = driver.getPageSource();
                JavaUtils.hardWait(1);
                String latest = driver.getPageSource();
                return previous.equalsIgnoreCase(latest);

            }
        };
    }

    public static void waitForStaticDOM(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TestData.getConfigData().getExplicitTimeOut());
        wait.until(CustomExplicitWaits.getDOMCondition());
    }


}
