package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindPlanResultsResponse extends BaseResponse {

    private List<IndFindPlanResultsResponse> option;

    public List<String> getResultPlanCodes(FindPlanOptionsResponse.IndFindPlanOptionsResponse planType, FindPlanOptionsResponse.IndFindPlanOptionsResponse selectedOption1, FindPlanOptionsResponse.IndFindPlanOptionsResponse selectedOption2, FindPlanOptionsResponse.IndFindPlanOptionsResponse selectedOption3) {

        return getOption().stream()
                .filter(item -> item.getPlanType().equalsIgnoreCase(planType.getName()))
                .filter(item -> item.getOption1().equalsIgnoreCase(selectedOption1.getOption()))
                .filter(item -> item.getOption2().equalsIgnoreCase(selectedOption2.getOption()))
                .filter(item -> item.getOption3().equalsIgnoreCase(selectedOption3.getOption()))
                .map(item -> item.getPlanCode())
                .collect(Collectors.toList());

    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndFindPlanResultsResponse {
        private String option1;
        private String option2;
        private String option3;
        private String planCode;
        private String planName;
        private String planType;
    }

}
