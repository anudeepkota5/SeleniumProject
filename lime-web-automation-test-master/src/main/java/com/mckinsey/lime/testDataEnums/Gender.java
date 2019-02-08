package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.GetAQuotePage;
import org.openqa.selenium.WebDriver;

public enum Gender implements GenderActions {
    M {
        @Override
        public void selectGender(WebDriver driver) {
            GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(driver);
            getAQuotePage.selectGenderMale();
        }
    },
    F {
        @Override
        public void selectGender(WebDriver driver) {
            GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(driver);
            getAQuotePage.selectGenderFemale();
        }
    };
}

interface GenderActions {
    void selectGender(WebDriver driver);
}
