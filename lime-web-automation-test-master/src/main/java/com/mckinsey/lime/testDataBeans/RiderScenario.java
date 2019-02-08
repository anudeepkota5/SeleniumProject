package com.mckinsey.lime.testDataBeans;

import com.mckinsey.lime.testDataEnums.Riders;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RiderScenario {
    private Riders rider;
    private String riderMessage;
    //TODO: convert to ENUM
    private String acceptRider;

    @Override
    public String toString() {
        return rider.toString();
    }
}
