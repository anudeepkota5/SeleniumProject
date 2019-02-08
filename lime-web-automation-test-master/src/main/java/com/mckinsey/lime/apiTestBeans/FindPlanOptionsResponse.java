package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindPlanOptionsResponse extends BaseResponse {

    private List<IndFindPlanOptionsResponse> option;

    public List<IndFindPlanOptionsResponse> getDropDown1Values() {
        return getOption().stream()
                .filter(item -> item.getOrder() == 1)
                .collect(Collectors.toList());
    }

    public List<IndFindPlanOptionsResponse> getDropDown2Values(IndFindPlanOptionsResponse dropdown1Selection) {
        return getOption().stream()
                .filter(item -> item.getOrder() == 2)
                .filter(item -> item.getParentAnswer().equalsIgnoreCase(dropdown1Selection.getName()))
                .collect(Collectors.toList());
    }

    public List<IndFindPlanOptionsResponse> getDropDown3Values(IndFindPlanOptionsResponse dropdown1Selection) {
        return getOption().stream()
                .filter(item -> item.getOrder() == 3)
                .filter(item -> item.getParentAnswer().equalsIgnoreCase(dropdown1Selection.getName()))
                .collect(Collectors.toList());
    }

    public List<IndFindPlanOptionsResponse> getDropDown4Values(IndFindPlanOptionsResponse dropdown1Selection) {
        return getOption().stream()
                .filter(item -> item.getOrder() == 4)
                .filter(item -> item.getParentAnswer().equalsIgnoreCase(dropdown1Selection.getName()))
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndFindPlanOptionsResponse {
        private String option;
        private String name;
        private Integer order;
        private String label;
        private String toolTip;
        private String parentAnswer;
        private String isIcon;
    }

}
