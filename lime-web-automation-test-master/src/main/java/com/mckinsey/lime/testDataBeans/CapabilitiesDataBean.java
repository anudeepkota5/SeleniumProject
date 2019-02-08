package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapabilitiesDataBean {

    private Map<String, String> firefoxLocalSit;
    private Map<String, String> chromeLocalSit;
    private Map<String, String> chromeSauceSit;
    private Map<String, String> firefoxSauceSit;

    private Map<String, String> firefoxLocalUat;
    private Map<String, String> chromeLocalUat;
    private Map<String, String> chromeSauceUat;
    private Map<String, String> firefoxSauceUat;

    private Map<String, String> androidLocalSit;
    private Map<String, String> iosLocalSit;
    private Map<String, String> iosSauceSit;
    private Map<String, String> androidSauceSit;

    private Map<String, String> androidLocalUat;
    private Map<String, String> iosLocalUat;
    private Map<String, String> iosSauceUat;
    private Map<String, String> androidSauceUat;

}
