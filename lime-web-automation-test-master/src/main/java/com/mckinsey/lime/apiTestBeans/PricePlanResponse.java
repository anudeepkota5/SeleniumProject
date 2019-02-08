package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class PricePlanResponse extends BaseResponse {
    private CurrentPricePlan currentPricePlan;
    private List<RecommendedPricePlan> recommendedPricePlans;
    private ChangePricePlanInfo changePricePlanInfo;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RecommendedPricePlan {
        private String price;
        private String offerCode;
        private String familyCode;
        private LanguageKeyObject<String> pricePlancharging;
        private LanguageKeyObject<String> name;
        private Object data;
        private Object voice;
        private List<Object> extraBenefits;
        private LanguageKeyObject<String> marketingMessage;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChangePricePlanInfo {
        private String ouId;
        private String nextBillCycle;
        private Object warningMessage;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentPricePlan {
        private String offerCode;
        private String familyCode;
        private String price;
        private LanguageKeyObject<String> name;
    }
}

