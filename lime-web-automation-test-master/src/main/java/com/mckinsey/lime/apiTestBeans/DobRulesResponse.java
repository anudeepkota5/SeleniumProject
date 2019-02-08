package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DobRulesResponse extends BaseResponse {

    private List<DobRules1> option;

    public int getMaxAgeLimit(String planCode) {
        return getOption().stream()
                .filter(item -> item.getPlanCode().equalsIgnoreCase(planCode))
                .map(DobRules1::getMaxAge)
                .findFirst()
                .orElse(62);
    }

    public int getMinAgeLimit(String planCode) {
        try {
            return getOption().stream()
                    .filter(item -> item.getPlanCode().equalsIgnoreCase(planCode))
                    .map(DobRules1::getMinAge)
                    .findFirst()
                    .orElse(18);
        } catch (NullPointerException e) {
            return 18;
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DobRules1 {
        private String planCode;
        private String key;
        private Integer minAge;
        private Integer maxAge;
    }

}
