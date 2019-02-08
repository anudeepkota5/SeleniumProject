package com.mckinsey.lime.testDataBeans;

import com.mckinsey.lime.testDataEnums.PlanType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Plan {
    private PlanType planType;
    private String planTitle;
    private String planSubTitle;

    @Override
    public String toString() {
        return planType + " | " + planTitle + " | " + planSubTitle;
    }
}
