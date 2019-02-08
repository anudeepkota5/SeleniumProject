package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.PossibleFiguresPage;
import org.openqa.selenium.WebDriver;

public enum StartAgeForCashBenefit implements StartAgeForCashBenefitActions {
    YEARS_50 {
        @Override
        public void selectStartAge(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectStartAge50ForCashBenefitDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "50";
        }
    },
    YEARS_55 {
        @Override
        public void selectStartAge(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectStartAge55ForCashBenefitDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "55";
        }
    },
    YEARS_60 {
        @Override
        public void selectStartAge(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectStartAge60ForCashBenefitDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "60";
        }
    },
    YEARS_65 {
        @Override
        public void selectStartAge(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectStartAge65ForCashBenefitDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "65";
        }
    },
    EMPTY {
        @Override
        public void selectStartAge(WebDriver driver) {

        }

        @Override
        public String getExpGUIValue() {
            return "";
        }
    };

}

interface StartAgeForCashBenefitActions {
    void selectStartAge(WebDriver driver);

    String getExpGUIValue();
}
