package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.DesktopPageObjects;
import org.openqa.selenium.WebDriver;

public enum PossibleFiguresEditableFields implements EditableFieldActions {
    NO_CHANGE {
        @Override
        public void clickEditIcon(WebDriver driver) {
            //Do Nothing
        }
    }, PREMIUM {
        @Override
        public void clickEditIcon(WebDriver driver) {
            DesktopPageObjects.possibleFiguresPage(driver).clickEditPremiumAmountIcon();
        }
    }, SUM_ASSURED {
        @Override
        public void clickEditIcon(WebDriver driver) {
            DesktopPageObjects.possibleFiguresPage(driver).clickSumAssuredEditIcon();
        }
    }, PREMIUM_TERM {
        @Override
        public void clickEditIcon(WebDriver driver) {
            DesktopPageObjects.possibleFiguresPage(driver).clickEditPremiumTermIcon();
        }
    }, PREMIUM_FREQUENCY {
        @Override
        public void clickEditIcon(WebDriver driver) {
            // Do Nothing as this field doesn't have any edit icon
        }
    }, MONTHLY_CASH_BENEFIT {
        @Override
        public void clickEditIcon(WebDriver driver) {
            DesktopPageObjects.possibleFiguresPage(driver).clickEditGuaranteedMonthlyCashBenefit();
        }
    }, PAYOUT_PERIOD {
        @Override
        public void clickEditIcon(WebDriver driver) {
            DesktopPageObjects.possibleFiguresPage(driver).clickEditPayoutPeriodIcon();
        }
    }, START_AGE_CASH_BENEFIT {
        @Override
        public void clickEditIcon(WebDriver driver) {
            DesktopPageObjects.possibleFiguresPage(driver).clickEditStartAgeForCashBenefitIcon();
        }
    }, POLICY_TERM {
        @Override
        public void clickEditIcon(WebDriver driver) {
            //TODO
            DesktopPageObjects.possibleFiguresPage(driver).clickEditPolicyTermIcon();
        }
    };
}

interface EditableFieldActions {
    void clickEditIcon(WebDriver driver);
}
