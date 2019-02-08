package com.mckinsey.lime.utils;

import com.mckinsey.lime.testDataBeans.TestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//ToDo: Need to refactor
public class ElementUtils {

    public static void sendKeys(WebDriver driver, By element, String val) {
        WebElement visibleElement = getVisibleElement(driver, element);
//        visibleElement.clear();
        if (driver instanceof AndroidDriver) {
            //Work around for UIAutomator2
            visibleElement.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            visibleElement.sendKeys(val);
        } else if (driver instanceof IOSDriver) {
            visibleElement.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

//            visibleElement.sendKeys(val);
//            ((MobileElement)visibleElement).setValue(val);

//            TouchAction touchAction = new IOSTouchAction((AppiumDriver)driver);
//            touchAction.tap(PointOption.point(((MobileElement) visibleElement).getCenter().getX(), ((MobileElement) visibleElement).getCenter().getY()))
//                    .perform(); // goes from here straight to the exception
//            visibleElement.sendKeys(val);
//            ((IOSDriver) driver).hideKeyboard();
//            new LoginPage((AppiumDriver)driver).hideKeyboard();


            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='" + val + "'", visibleElement);


        } else {
            visibleElement.clear();
            visibleElement.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            visibleElement.sendKeys(val);
//            for(int i = 0; i < val.length(); i++) {
//                visibleElement.sendKeys(Character.toString(val.charAt(i)));
//            }
        }
    }

    public static void setKeys(WebDriver driver, By element, String val) {
        WebElement visibleElement = getVisibleElement(driver, element);
        setKeys(visibleElement, val);
    }

    public static void clearTextField(WebDriver driver, By element) {
        WebElement visibleElement = getVisibleElement(driver, element);

        if (driver instanceof AndroidDriver) {
            //Work around for UIAutomator2
            visibleElement.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }

        visibleElement.clear();
    }

    public static void setKeys(WebElement element, String val) {
        //        visibleElement.clear();
        if (element instanceof AndroidElement) {
            element.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        ((MobileElement) element).setValue(val);
    }

    public static WebElement getVisibleElement(WebDriver driver, By by) {
        return getVisibleElement(driver, null, by, TestData.getConfigData().getExplicitTimeOut());
    }

    public static WebElement getVisibleElement(WebDriver driver, WebElement element, By by) {
        return getVisibleElement(driver, element, by, TestData.getConfigData().getExplicitTimeOut());
    }

    public static WebElement getVisibleElement(WebDriver driver, WebElement element, By by, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        List<WebElement> until = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        if (element == null) {
            return until.get(0);
        }
        //TODO: need to add wait for visibility of inner by
        else
            return element.findElement(by);
    }

    public static WebElement getVisibleElement(WebDriver driver, By by, int timeOut) {
        return getVisibleElement(driver, null, by, timeOut);
    }

    public static List<WebElement> getVisibleElements(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TestData.getConfigData().getExplicitTimeOut());
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public static List<WebElement> getVisibleElements(WebDriver driver, By by, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (TimeoutException e) {
            return new ArrayList<>();
        } catch (StaleElementReferenceException e) {
            // To manage OVERDUE label which is displayed after DOM loads
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        }
    }

    public static WebElement getClickableElement(WebDriver driver, By by) {
        return getClickableElement(driver, null, by, TestData.getConfigData().getExplicitTimeOut());
    }

    public static WebElement getClickableElement(WebDriver driver, WebElement parentElement, By by, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.elementToBeClickable(getVisibleElement(driver, parentElement, by, timeOut)));
    }

    public static String getCssAttribute(WebDriver driver, By element, String attribute) {
        return getVisibleElement(driver, element).getCssValue(attribute);
    }

    public static String getHtmlAttribute(WebDriver driver, By element, String attribute) {
        return getVisibleElement(driver, element).getAttribute(attribute);
    }

    public static String getText(WebDriver driver, By by) {
        return getVisibleElement(driver, by).getText();
    }

    public static String getText(WebDriver driver, WebElement element, By by) {
        return getVisibleElement(driver, element, by).getText();
    }

    public static String getText(WebDriver driver, By by, int timeOut) {
        return getVisibleElement(driver, by, timeOut).getText();
    }

    public static String getText(WebElement element) {
        return element.getText();
    }

    public static void clickObject(WebDriver driver, By by) {
        clickObject(driver, by, TestData.getConfigData().getExplicitTimeOut());
    }

    public static void clickObject(WebDriver driver, WebElement element, By by) {
        clickObject(driver, element, by, TestData.getConfigData().getExplicitTimeOut());
    }

    //TODO: Need to add waitForClickable code
    public static void clickObject(WebDriver driver, WebElement parentElement, By by, int timeOut) {
        WebElement clickableElement = getClickableElement(driver, parentElement, by, timeOut);
//        wait.until(ExpectedConditions.elementToBeClickable(by));
//        WebElement visibleElement = getVisibleElement(driver, parentElement, by, timeOut);

        try {
            clickableElement.click();
        } catch (WebDriverException e) {
            e.printStackTrace();
            WebElement clickableElement1 = getClickableElement(driver, parentElement, by, timeOut);

            JavascriptExecutor jse2 = (JavascriptExecutor) driver;

            jse2.executeScript("arguments[0].scrollIntoView()", clickableElement1);

//            myElement.scrollIntoView(true);
//            jse2.executeScript("var viewportH = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);");
            jse2.executeScript("window.scrollBy(0, -(Math.max(document.documentElement.clientHeight, window.innerHeight || 0))/2);");

/**
 * Below logic also working fine. But commented as part of refactor
 */
            /*((AppiumDriver) driver).context("NATIVE_APP");
            int i = driver.manage().window().getSize().getHeight() / 20;
            ((AppiumDriver) driver).context("CHROMIUM");

            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
//            String o = jse2.executeScript("window.innerHeight").toString();

            jse2.executeScript("arguments[0].scrollIntoView()", visibleElement);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            jse2.executeScript("window.scrollBy(0,-" + i + ")");*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            clickableElement1.click();
        }
//        CustomLogging.writeToReport("Clicked on element: " + by);
    }

    public static void clickObject(WebDriver driver, By by, int timeOut) {
        clickObject(driver, null, by, timeOut);
    }

    public static void swipeLeft(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.90);
        int endx = (int) (size.width * 0.10);
        int starty = size.height / 2;
        int endy = starty;
        new TouchAction(driver).tap(PointOption.point(startx, starty))/*.moveTo(endx, endy).release()*/.perform();
    }

    public static void swipeRight(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.10);
        int endx = (int) (size.width * 0.90);
        int starty = size.height / 2;
        int endy = starty;

        new TouchAction(driver).press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
    }

    public static void swipeDown(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();

        int startx = size.width / 2;
        int endx = startx;
        int starty = (int) (size.height * 0.10);
        int endy = (int) (size.height * 0.90);

        new TouchAction(driver).press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
    }

    public static void swipeUp(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startx = size.width / 2;
        int endx = startx;
        int starty = (int) (size.height * 0.90);
        int endy = (int) (size.height * 0.10);

        new TouchAction(driver).press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();

    }

    public static void shortSwipeUp(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startx = size.width / 2;
        int endx = startx;
        int starty = (int) (size.height * 0.70);
        int endy = (int) (size.height * 0.30);

        new TouchAction(driver).press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
    }

    public static boolean acceptAlert(WebDriver driver) {
        return acceptAlert(driver, TestData.getConfigData().getExplicitTimeOut());
    }

    public static boolean acceptAlert(WebDriver driver, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static Boolean waitForElementNotDisplayed(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TestData.getConfigData().getExplicitTimeOut());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static Point getLocation(WebDriver driver, By by) {
        return getVisibleElement(driver, by).getLocation();
    }

    public static Dimension getSize(WebDriver driver, By by) {
        return getVisibleElement(driver, by).getSize();
    }

    public static String getRectangle(WebDriver driver, By by) {
        Rectangle rect = getVisibleElement(driver, by).getRect();
        return "Location: " + rect.getPoint() + "\tSize: " + rect.getDimension();

    }

    public static boolean isElementDisplayed(WebDriver driver, By by) {
        return isElementDisplayed(driver, by, TestData.getConfigData().getExplicitTimeOut());

    }

    public static boolean isElementDisplayed(WebDriver driver, By by, int timeOut) {
        try {
            WebElement visibleElement = getVisibleElement(driver, by, timeOut);
            return visibleElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public static int numberOfElements(WebDriver driver, By by) {
        return ElementUtils.getVisibleElements(driver, by).size();
    }

    public static void closeCurrentWindow(WebDriver driver) {
        switchToLatestWindow(driver);
        System.out.println(driver.getCurrentUrl());

        driver.close();

        switchToLatestWindow(driver);
    }

    private static void switchToLatestWindow(WebDriver driver) {
        Iterator<String> iterator = driver.getWindowHandles().iterator();
        while (iterator.hasNext())
            driver.switchTo().window(iterator.next());
    }

    public static void selectDropDownValue(WebDriver driver, By dropDown, By elementToSelect) {
        selectDropDownValue(driver, dropDown, elementToSelect, false);
    }

    public static void selectDropDownValue(WebDriver driver, By dropDown, By elementToSelect, boolean isRelativePathForOptionToSelect) {
//        WebElement dropDownElement = getVisibleElement(driver, dropDown);

        if (isRelativePathForOptionToSelect) {
            clickObject(driver, dropDown);
            clickObject(driver, getVisibleElement(driver, dropDown), elementToSelect);
//            new Actions(driver).click(dropDownElement).click(getVisibleElement(driver, dropDownElement, elementToSelect)).build().perform();
        } else {
            clickObject(driver, dropDown);
            clickObject(driver, elementToSelect);
//            new Actions(driver).click(dropDownElement).click(driver.findElement(elementToSelect)).build().perform();
        }


    }
}