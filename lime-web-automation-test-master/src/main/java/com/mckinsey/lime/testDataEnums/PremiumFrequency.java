package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.PossibleFiguresPage;
import org.openqa.selenium.WebDriver;

public enum PremiumFrequency implements PremiumFrequencyActions{
    MONTHLY{
        @Override
        public void selectFrequency(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectPremiumFrequencyMonthly();
        }

        @Override
        public String getExpGuiValue() {
            return "Monthly";
        }
    }, YEARLY {
        @Override
        public void selectFrequency(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectPremiumFrequencyYearly();
        }

        @Override
        public String getExpGuiValue() {
            return "Yearly";
        }
    },
    EMPTY {
        @Override
        public void selectFrequency(WebDriver driver) {

        }

        @Override
        public String getExpGuiValue() {
            return null;
        }
    };



}

interface PremiumFrequencyActions {
    void selectFrequency(WebDriver driver);
    String getExpGuiValue();
}
