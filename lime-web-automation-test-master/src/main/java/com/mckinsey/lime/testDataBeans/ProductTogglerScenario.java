package com.mckinsey.lime.testDataBeans;

import com.mckinsey.lime.testDataEnums.*;
import com.mckinsey.lime.utils.CommonEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductTogglerScenario {

    private Plan currentPlan;
    private DOB dob;
    private Gender gender;
    private Smoke smoke;
    private EditScenario editScenario;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Plan {
        private PlanType planType;
        private String planTitle;
        private String planSubTitle;

        @Override
        public String toString() {
            return planType + " | " + planTitle + " | " + planSubTitle;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class EditScenario {
        private PossibleFiguresEditableFields editable;
        private PossibleFiguresEditableScenarios editableScenario;

        @Override
        public String toString() {
            return editable + " | " + editableScenario;
        }
    }
}
