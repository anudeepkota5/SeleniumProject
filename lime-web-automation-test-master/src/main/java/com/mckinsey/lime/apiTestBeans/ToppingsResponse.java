package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class ToppingsResponse extends BaseResponse {

    private HashMap<String, IndToppingDetail> toppingDetails;
    private List<ToppingTag> toppingTags;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndToppingDetail {
        private String packageType;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ToppingTag {
        private LanguageKeyObject<String> title;
        private List<String> planCodes;
        private List<String> hotOffers;
    }

}
