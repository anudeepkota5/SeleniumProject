package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.PossibleFiguresPage;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public enum PayoutPeriod implements PayoutPeriodActions {
    YEARS_10 {
        @Override
        public void selectPayout(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectYears10FromPayoutPeriodDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "10";
        }
    },YEARS_20 {
        @Override
        public void selectPayout(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectYears20FromPayoutPeriodDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "20";
        }
    },YEARS_30 {
        @Override
        public void selectPayout(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectYears30FromPayoutPeriodDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "30";
        }
    },
    EMPTY {
        @Override
        public void selectPayout(WebDriver driver) {

        }

        @Override
        public String getExpGUIValue() {
            return "";
        }
    };

}

interface PayoutPeriodActions {
    void selectPayout(WebDriver driver);

    String getExpGUIValue();
}
