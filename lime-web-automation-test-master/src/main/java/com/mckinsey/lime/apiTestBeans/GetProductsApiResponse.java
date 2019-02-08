package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetProductsApiResponse extends BaseResponse {
    private List<Summary> summary;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {
        private String name;
        private List<PlanPossibleFigures> outputs;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PlanPossibleFigures {
            private Integer entryAge;
            private String policyTerm;
            private String premiumTerm;
            private String sumAssured;
            private Float monthlyPremium;
            private Float annualPremium;
            @JsonProperty("P_mth_prem_bas")
            private Float P_mth_prem_bas;
            @JsonProperty("P_annl_prem_bas")
            private Float P_annl_prem_bas;
            private Long minimumSumAssured;
            private Long maximumSumAssured;

            public String getMonthlyPremium() {
                return String.format("%.2f", monthlyPremium);
            }

            public String getAnnualPremium() {
                return String.format("%.2f", annualPremium);
            }
        }
    }
}
