package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvironmentDataBean {
    private EnvironmentData sit;
    private EnvironmentData uat;
    private EnvironmentData prod;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EnvironmentData {

        private String name;
        @Deprecated
        private String authorization;
        private String baseURL;
        private String WebAppUrl;
        private String authority;

    }
}
