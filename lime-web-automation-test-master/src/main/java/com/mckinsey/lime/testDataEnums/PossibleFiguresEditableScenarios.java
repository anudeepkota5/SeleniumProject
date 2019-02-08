package com.mckinsey.lime.testDataEnums;

import com.mckinsey.lime.guiDataBeans.GetAQuoteBean;
import com.mckinsey.lime.guiDataBeans.PossibleFiguresBean;
import com.mckinsey.lime.pages.DesktopPageObjects;
import com.mckinsey.lime.pages.PossibleFiguresPage;
import com.mckinsey.lime.testDataBeans.TestData;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public enum PossibleFiguresEditableScenarios implements PossibleFiguresEditableScenarioActions {
    NO_CHANGE {
        @Override
        public void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) throws IOException {
            //Do nothing
        }
    },

    PREMIUM_LESS_THAN_MIN_SA {
        @Override
        public void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) throws IOException {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            Integer minSumAssured = Integer.valueOf(TestData.getPlansConfigData().getMinSumAssured(getAQuoteBean.getIndProductDetails().getName(), getAQuoteBean.getIndProductDetails().getSubTitle()));

            boolean premiumFrequencyMonthlySelected = possibleFiguresPage.isPremiumFrequencyMonthlySelected();
            String premiumFrequency = "YEARLY";
            if (premiumFrequencyMonthlySelected)
                premiumFrequency = "MONTHLY";

            PossibleFiguresBean possibleFiguresBean = new PossibleFiguresBean.Builder().premiumAmount(possibleFiguresPage.getPremiumAmount())
                    .premiumTerm(PremiumTerm.valueOf(possibleFiguresPage.getPremiumTerm()))
                    .premiumFrequency(PremiumFrequency.valueOf(premiumFrequency))
                    .policyTerm(possibleFiguresPage.getPolicyTerm())
                    .sumAssured(possibleFiguresPage.getSumAssured()).Build();

            Float i = (Float.valueOf(possibleFiguresBean.getSumAssured()) / minSumAssured) + 1;
            Float expPremiumAmount = Float.valueOf(possibleFiguresBean.getPremiumAmount()) / i;

            /*PossibleFiguresBean updatedPossibleFiguresBean = new PossibleFiguresBean(String.valueOf(expPremiumAmount),
                    possibleFiguresBean.getPremiumTerm(),
                    possibleFiguresBean.getPremiumFrequency(),
                    possibleFiguresBean.getPolicyTerm(),
                    possibleFiguresBean.getSumAssured());

            CustomResponse<GetProductsApiResponse> getProductsApiResponseCustomResponse = ApiRequests.sendEditGetProductsAPI(customClass, getAQuoteBean, updatedPossibleFiguresBean);*/

            possibleFiguresPage.fillPremiumAmount(String.valueOf(expPremiumAmount));
        }
    },
    PREMIUM_GRATER_THAN_MAX_SA {
        @Override
        public void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            Integer maxSumAssured = Integer.valueOf(TestData.getPlansConfigData().getMaxSumAssured(getAQuoteBean.getIndProductDetails().getName(), getAQuoteBean.getIndProductDetails().getSubTitle()));

            boolean premiumFrequencyMonthlySelected = possibleFiguresPage.isPremiumFrequencyMonthlySelected();
            String premiumFrequency = "YEARLY";
            if (premiumFrequencyMonthlySelected)
                premiumFrequency = "MONTHLY";

            PossibleFiguresBean possibleFiguresBean = new PossibleFiguresBean.Builder()
                    .premiumAmount(possibleFiguresPage.getPremiumAmount())
                    .premiumTerm(PremiumTerm.valueOf(possibleFiguresPage.getPremiumTerm()))
                    .premiumFrequency(PremiumFrequency.valueOf(premiumFrequency))
                    .policyTerm(possibleFiguresPage.getPolicyTerm())
                    .sumAssured(possibleFiguresPage.getSumAssured())
                    .Build();

            Float i = (Float.valueOf(possibleFiguresBean.getSumAssured()) / maxSumAssured) + 1;
            Float expPremiumAmount = Float.valueOf(possibleFiguresBean.getPremiumAmount()) * i;

            possibleFiguresPage.fillPremiumAmount(String.valueOf(expPremiumAmount));

        }
    },
    SA_LESS_THAN_MIN {
        @Override
        public void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);

            Integer minSumAssured = Integer.valueOf(TestData.getPlansConfigData().getMinSumAssured(getAQuoteBean.getIndProductDetails().getName(), getAQuoteBean.getIndProductDetails().getSubTitle()));
            possibleFiguresPage.fillSumAssuredAmount(String.valueOf(minSumAssured - 100));

        }
    },
    SA_GRATER_THAN_MAX {
        @Override
        public void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);

            Integer maxSumAssured = Integer.valueOf(TestData.getPlansConfigData().getMaxSumAssured(getAQuoteBean.getIndProductDetails().getName(), getAQuoteBean.getIndProductDetails().getSubTitle()));
            possibleFiguresPage.fillSumAssuredAmount(String.valueOf(maxSumAssured + 100));
            possibleFiguresPage.clickUpdateButton();

        }
    },
    PREMIUM_FREQUENCY_MONTHLY {
        @Override
        public void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            if (!possibleFiguresPage.isPremiumFrequencyMonthlySelected())
                possibleFiguresPage.selectPremiumFrequencyMonthly();

        }
    },
    PREMIUM_FREQUENCY_YEARLY {
        @Override
        public void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) {
            PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(driver);
            possibleFiguresPage.selectPremiumFrequencyYearly();
        }
    };
}

interface PossibleFiguresEditableScenarioActions {
    void fillField(WebDriver driver, GetAQuoteBean getAQuoteBean) throws IOException;
}
