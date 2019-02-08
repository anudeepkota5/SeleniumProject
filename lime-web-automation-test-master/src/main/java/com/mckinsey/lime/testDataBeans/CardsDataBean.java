package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//TODO: Need to update as per the Lime project requirement
public class CardsDataBean {

    private Card successCard;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Card {
        private String number;
        private String name;
        private String expiry;
        private String securityCode;

    }
}
