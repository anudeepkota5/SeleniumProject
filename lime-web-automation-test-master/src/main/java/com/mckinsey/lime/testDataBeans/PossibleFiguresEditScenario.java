package com.mckinsey.lime.testDataBeans;

import com.mckinsey.lime.testDataEnums.PossibleFiguresEditableFields;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PossibleFiguresEditScenario {
    private PossibleFiguresEditableFields editField;
    //TODO: convert to ENUM
    private String valueToUpdate;
    private String errorDetails;

    @Override
    public String toString() {
        return editField.toString();
    }
}
