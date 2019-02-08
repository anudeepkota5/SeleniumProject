package com.mckinsey.lime.testDataBeans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RiderEditScenario {
    private String editField;
    private String valueToEdit;
    private String errorMessage;
}
