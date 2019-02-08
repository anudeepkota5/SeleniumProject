package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.LandingPage;
import com.mckinsey.lime.pages.OurPlansPage;
import org.openqa.selenium.WebDriver;

public enum PlanType implements PlanTypeActions {
    PROTECTION {
        @Override
        public void clickPlanType(WebDriver driver) {
            LandingPage landingPage = DesktopPageObjects.landingPage(driver);
            landingPage.clickProtectionMaster();
        }
    },
    SAVINGS {
        @Override
        public void clickPlanType(WebDriver driver) {
            LandingPage landingPage = DesktopPageObjects.landingPage(driver);
            landingPage.clickSavingsMaster();
        }
    };
}

interface PlanTypeActions {
    void clickPlanType(WebDriver driver);
}
