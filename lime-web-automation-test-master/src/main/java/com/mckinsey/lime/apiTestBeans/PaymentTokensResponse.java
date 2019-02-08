package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class PaymentTokensResponse extends BaseResponse {

    private Card card;
    private String created;
    private String id;
    private boolean livemode;
    private String location;
    private String object;
    private String used;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Card {
        private String bank;
        private String brand;
        private String city;
        private String country;
        private String created;
        private String expiration_month;
        private String expiration_year;
        private String financing;
        private String fingerprint;
        private String id;
        private String last_digits;
        private boolean livemode;
        private String name;
        private String object;
        private String postal_code;
        private boolean security_code_check;

    }
}
