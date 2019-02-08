package com.mckinsey.lime.pages;

import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.CustomExplicitWaits;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.JavaUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesktopBasePage {

    protected WebDriver driver;

    public DesktopBasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void hardWait(double seconds) {
        JavaUtils.hardWait(seconds);
    }

    public void waitForLoaders() {

        WebDriverWait wait = new WebDriverWait(driver, TestData.getConfigData().getExplicitTimeOut());
//        wait.until(CustomExplicitWaits.getJQueryCondition());
        Boolean until = wait.until(CustomExplicitWaits.getJSCondition());

//        wait.until(CustomExplicitWaits.getDOMCondition());

        DesktopPageObjects.commonPage(driver).waitForGlobalLoader();

    }

    public void closeCurrentWindow() {
        ElementUtils.closeCurrentWindow(driver);
    }
}
