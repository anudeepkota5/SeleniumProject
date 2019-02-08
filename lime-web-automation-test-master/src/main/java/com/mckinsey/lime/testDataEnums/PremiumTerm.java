package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.PossibleFiguresPage;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public enum PremiumTerm implements PremiumScenarioActions {
    UP_TO_AGE_54 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectUpToAge54FromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "54";
        }
    },
    UP_TO_AGE_61 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectUpToAge61FromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "61";
        }
    },
    UP_TO_AGE_64 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectUpToAge64FromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "64";
        }
    },
    UP_TO_AGE_69 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectUpToAge69FromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "69";
        }
    },
    UP_TO_AGE_74 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectUpToAge74FromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "74";
        }
    },
    UP_TO_AGE_84 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectUpToAge84FromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "84";
        }
    },
    YEARS_5 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select5YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "5";
        }
    },
    YEARS_10 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select10YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "10";
        }
    },
    YEARS_15 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select15YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "15";
        }
    },
    YEARS_20 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select20YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "20";
        }
    },
    YEARS_25 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select25YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "25";
        }
    },
    YEARS_30 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select30YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "30";
        }
    },
    YEARS_35 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select35YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "35";
        }
    },
    YEARS_3 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select3YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "3";
        }
    },
    YEARS_24 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select24YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "24";
        }
    },
    YEARS_29 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select29YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "29";
        }
    },
    YEARS_12 {
        @Override
        public void selectPremium(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select12YearsFromPremiumTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "12";
        }
    },
    EMPTY {
        @Override
        public void selectPremium(WebDriver driver) {

        }

        @Override
        public String getExpGUIValue() {
            return "";
        }
    };

    public static PremiumTerm getByGuiValue(String guiValue) {
        return Arrays.stream(PremiumTerm.values())
                .filter(item -> item.getExpGUIValue().equalsIgnoreCase(guiValue))
                .findFirst()
                .orElse(null);

    }
}

interface PremiumScenarioActions {
    void selectPremium(WebDriver driver);

    String getExpGUIValue();
}
