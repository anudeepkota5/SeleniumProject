package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.PossibleFiguresPage;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public enum PolicyTerm implements PolicyScenarioActions {

    YEARS_13 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select13YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "13";
        }
    },YEARS_19 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select19YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "19";
        }
    },YEARS_20 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select20YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "20";
        }
    },YEARS_21 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select21YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "21";
        }
    },YEARS_22 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select22YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "22";
        }
    },YEARS_23 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select23YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "23";
        }
    },YEARS_24 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select24YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "24";
        }
    },YEARS_25 {
        @Override
        public void selectPolicy(WebDriver driver) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.select25YearsFromPolicyTermDropdown();
        }

        @Override
        public String getExpGUIValue() {
            return "25";
        }
    },
    EMPTY {
        @Override
        public void selectPolicy(WebDriver driver) {

        }

        @Override
        public String getExpGUIValue() {
            return "";
        }
    };

    public static PolicyTerm getByGuiValue(String guiValue) {
        return Arrays.stream(PolicyTerm.values())
                .filter(item -> item.getExpGUIValue().equalsIgnoreCase(guiValue))
                .findFirst()
                .orElse(null);

    }
}

interface PolicyScenarioActions {
    void selectPolicy(WebDriver driver);

    String getExpGUIValue();
}
