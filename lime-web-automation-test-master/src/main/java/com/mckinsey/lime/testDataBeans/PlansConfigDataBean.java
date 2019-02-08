package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mckinsey.lime.testDataEnums.PossibleFiguresEditableFields;
import com.mckinsey.lime.testDataEnums.PremiumTerm;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlansConfigDataBean {
    private List<IndPlan> plans;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndPlan {
        private String planType;
        private String title;
        private String subTitle;
        private String minSumAssured;
        private String maxSumAssured;
        private PossibleFiguresEditableField editableFields;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PossibleFiguresEditableField {
            @JsonProperty("PREMIUM")
            private List<PremiumTerm> PREMIUM;
            @JsonProperty("SUM_ASSURED")
            private List<String> SUM_ASSURED;
            @JsonProperty("PREMIUM_TERM")
            private List<String> PREMIUM_TERM;
            @JsonProperty("PREMIUM_FREQUENCY")
            private List<String> PREMIUM_FREQUENCY;
            @JsonProperty("POLICY_TERM")
            private List<String> POLICY_TERM;
        }
    }

    public boolean isPremiumEditable(String planTitle, String planSubTitle) {
        return getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle) && item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .map(item -> item.getEditableFields())
                .findFirst().get().getPREMIUM() != null;

    }

    public boolean isPremiumTermEditable(String planTitle, String planSubTitle) {
        return getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle) && item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .map(item -> item.getEditableFields())
                .findFirst().get().getPREMIUM_TERM() != null;

    }

    public boolean isPremiumFrequencyEditable(String planTitle, String planSubTitle) {
        return getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle) && item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .map(item -> item.getEditableFields())
                .findFirst().get().getPREMIUM_FREQUENCY() != null;

    }

    public boolean isPolicyTermEditable(String planTitle, String planSubTitle) {
        return getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle) && item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .map(item -> item.getEditableFields())
                .findFirst().get().getPOLICY_TERM() != null;

    }

    public boolean isSumAssuredEditable(String planTitle, String planSubTitle) {
        return getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle) && item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .map(item -> item.getEditableFields())
                .findFirst().get().getSUM_ASSURED() != null;

    }

    public boolean isMaxSumAssuredAvailable(String planTitle, String planSubTitle) {
        boolean b = getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle) && item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .map(item -> item.getMaxSumAssured())
                .findFirst().get()
                .isEmpty();
        return !b;
    }

    public String getMinSumAssured(String planTitle, String planSubTitle) {
        return getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle))
                .filter(item -> item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .findFirst().get()
                .getMinSumAssured();

    }

    public String getMaxSumAssured(String planTitle, String planSubTitle) {
        return getPlans().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(planTitle))
                .filter(item -> item.getSubTitle().equalsIgnoreCase(planSubTitle))
                .findFirst().get()
                .getMaxSumAssured();

    }

    public boolean isEditable(String planTitle, String planSubTitle, PossibleFiguresEditableFields editable) {
        switch (editable) {
            case PREMIUM:
                return isPremiumEditable(planTitle, planSubTitle);
            case SUM_ASSURED:
                return isSumAssuredEditable(planTitle, planSubTitle);
            case PREMIUM_TERM:
                return isPremiumTermEditable(planTitle, planSubTitle);
            case POLICY_TERM:
                return isPolicyTermEditable(planTitle, planSubTitle);
            case PREMIUM_FREQUENCY:
                return isPremiumFrequencyEditable(planTitle, planSubTitle);
            case PAYOUT_PERIOD:
                return false;
            case MONTHLY_CASH_BENEFIT:
                return false;
            case START_AGE_CASH_BENEFIT:
                return false;
            case NO_CHANGE:
                return true;
            default:
                return false;
        }

    }
}
