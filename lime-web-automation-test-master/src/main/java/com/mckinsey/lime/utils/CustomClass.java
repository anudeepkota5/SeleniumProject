package com.mckinsey.lime.utils;

import com.mckinsey.lime.init.BaseTest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

@Getter
@Setter
@AllArgsConstructor
public class CustomClass {
    private Object object;
    private WebDriver driver;
    private ITestContext context;
    private int failedAssertionCount;
    private BaseTest testObject;

    public CustomClass(Object object, WebDriver driver, ITestContext context, BaseTest testObject) {
        this.object = object;
        this.driver = driver;
        this.context = context;
        this.failedAssertionCount = 1;
        this.testObject = testObject;
    }

    public CustomClass(ITestContext context) {
        this.context = context;
    }
}
