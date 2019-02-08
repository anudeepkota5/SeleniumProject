package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mckinsey.lime.utils.CommonEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocatorsDataBean {

    private List<indLocator> locators;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class indLocator {
        private String pageName;
        private String locatorName;
        private CommonEnums.Locators locatorType;
        private String locatorValue;

    }
}
