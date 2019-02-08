package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.GetAQuotePage;
import org.openqa.selenium.WebDriver;

public enum Smoke implements SmokeActions {
    Y {
        @Override
        public void selectSmoke(WebDriver driver) {
            GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(driver);
            getAQuotePage.selectSmokeYes();
        }
    },
    N {
        @Override
        public void selectSmoke(WebDriver driver) {
            GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(driver);
            getAQuotePage.selectSmokeNo();
        }
    };
}

interface SmokeActions {
    void selectSmoke(WebDriver driver);
}
