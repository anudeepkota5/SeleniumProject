package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.pages.CommonPage;
import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.WantARiderPage;
import org.openqa.selenium.WebDriver;

public enum Riders implements RiderActions {

    EARLY_CANCER_WAIVER {
        @Override
        public void selectRider(WebDriver driver) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            wantARiderPage.selectEarlyCancerWaiver();
        }
    },
    EARLY_PROTECT_ACCELERATOR {
        @Override
        public void selectRider(WebDriver driver) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            wantARiderPage.selectEarlyProtectAccelerator();
        }
        @Override
        public void editAmount(WebDriver driver, String amountToUpdate) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            CommonPage commonPage = DesktopPageObjects.commonPage(driver);

//            wantARiderPage.selectWantToChangeAmount();
//            commonPage.clickNextButton();
            wantARiderPage.clickEditAmountIcon();
            wantARiderPage.fillAmountTextField(amountToUpdate);
            wantARiderPage.clickUpdateButton();
        }
    },
    DD_PREMIUM_WAIVER {
        @Override
        public void selectRider(WebDriver driver) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            wantARiderPage.selectDdPremiumWaiver();
        }
    },
    DIRECT_DD_RIDER_WHOLE_LIFE {
        @Override
        public void selectRider(WebDriver driver) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            wantARiderPage.selectDirectDDForWholeLife();
        }
    },
    DIRECT_DD_RIDER_TERM {
        @Override
        public void selectRider(WebDriver driver) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            wantARiderPage.selectDirectDDForTerm();
        }
    },
    CANCER_PREMIUM_WAIVER {
        @Override
        public void selectRider(WebDriver driver) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            wantARiderPage.selectCancerPremiumWaiver();
        }
    },
    NO_THANKS {
        @Override
        public void selectRider(WebDriver driver) {
            WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(driver);
            wantARiderPage.selectNoThanks();
        }
    };
    @Override
    public void editAmount(WebDriver driver, String amountToUpdate) {
    }

}

interface RiderActions {
    void selectRider(WebDriver driver);
    void editAmount(WebDriver driver, String amountToUpdate);
}
