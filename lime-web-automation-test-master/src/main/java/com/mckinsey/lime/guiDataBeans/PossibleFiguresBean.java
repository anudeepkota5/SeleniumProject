package com.mckinsey.lime.guiDataBeans;

import com.mckinsey.lime.testDataEnums.PayoutPeriod;
import com.mckinsey.lime.testDataEnums.PremiumFrequency;
import com.mckinsey.lime.testDataEnums.PremiumTerm;
import com.mckinsey.lime.testDataEnums.StartAgeForCashBenefit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class PossibleFiguresBean {
    private final String premiumAmount;
    private final PremiumTerm premiumTerm;
    private final PremiumFrequency premiumFrequency;
    private final String policyTerm;
    private final String sumAssured;
    private final StartAgeForCashBenefit startAgeForCashBenefit;
    private final PayoutPeriod payoutPeriod;
    private final String monthlyCashBenefit;
    private final ViewDetails viewDetails;

    public static class Builder {
        private String premiumAmount;
        private PremiumTerm premiumTerm;
        private PremiumFrequency premiumFrequency;
        private String policyTerm;
        private String sumAssured;
        private StartAgeForCashBenefit startAgeForCashBenefit;
        private PayoutPeriod payoutPeriod;
        private String monthlyCashBenefit;
        private ViewDetails viewDetails;

        public Builder premiumAmount(String premiumAmount) {
            this.premiumAmount = premiumAmount;
            return this;
        }

        public Builder premiumTerm(PremiumTerm premiumTerm) {
            this.premiumTerm = premiumTerm;
            return this;
        }

        public Builder premiumFrequency(PremiumFrequency premiumFrequency) {
            this.premiumFrequency = premiumFrequency;
            return this;
        }

        public Builder policyTerm(String policyTerm) {
            this.policyTerm = policyTerm;
            return this;
        }

        public Builder startAgeForCashBenefit(String startAgeForCashBenefit) {
            if (startAgeForCashBenefit.isEmpty())
                this.startAgeForCashBenefit = StartAgeForCashBenefit.EMPTY;
            else
                this.startAgeForCashBenefit = StartAgeForCashBenefit.valueOf(startAgeForCashBenefit);
            return this;
        }

        public Builder startAgeForCashBenefit(StartAgeForCashBenefit startAgeForCashBenefit) {
            this.startAgeForCashBenefit = startAgeForCashBenefit;
            return this;
        }

        public Builder payoutPeriod(String payoutPeriod) {
            if (payoutPeriod.isEmpty())
                this.payoutPeriod = PayoutPeriod.EMPTY;
            else
                this.payoutPeriod = PayoutPeriod.valueOf(payoutPeriod);
            return this;
        }

        public Builder payoutPeriod(PayoutPeriod payoutPeriod) {
            this.payoutPeriod = payoutPeriod;
            return this;
        }

        public Builder monthlyCashBenefit(String monthlyCashBenefit) {
            this.monthlyCashBenefit = monthlyCashBenefit;
            return this;
        }

        public Builder sumAssured(String sumAssured) {
            this.sumAssured = sumAssured;
            return this;
        }

        public Builder viewDetails(String scenarioA, String scenarioB, String guaranteedMaturity, String yieldA, String yieldB, String yearlyCashBenefit) {
            if (scenarioA.isEmpty()
                    && scenarioB.isEmpty()
                    && guaranteedMaturity.isEmpty()
                    && yieldA.isEmpty()
                    && yieldB.isEmpty()
                    && yearlyCashBenefit.isEmpty())
                this.viewDetails = null;
            else
                this.viewDetails = new ViewDetails(scenarioA, scenarioB, guaranteedMaturity, yieldA, yieldB, yearlyCashBenefit);
            return this;
        }

        public Builder viewDetails(ViewDetails viewDetails) {
            if (viewDetails != null)
                this.viewDetails = viewDetails;
            return this;
        }


        public PossibleFiguresBean Build() {

            return new PossibleFiguresBean(this);
        }
    }

    private PossibleFiguresBean(Builder builder) {
        this.premiumAmount = builder.premiumAmount;
        this.premiumTerm = builder.premiumTerm;
        this.premiumFrequency = builder.premiumFrequency;
        this.policyTerm = builder.policyTerm;
        this.sumAssured = builder.sumAssured;
        this.startAgeForCashBenefit = builder.startAgeForCashBenefit;
        this.payoutPeriod = builder.payoutPeriod;
        this.monthlyCashBenefit = builder.monthlyCashBenefit;
        this.viewDetails = builder.viewDetails;
    }

    @Getter
    @AllArgsConstructor
    public static class ViewDetails {
        private String scenarioA;
        private String scenarioB;
        private String guaranteedMaturity;
        private String yieldA;
        private String yieldB;
        private String yearlyCashBenefit;

    }
}


