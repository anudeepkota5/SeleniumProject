package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalEmulator {
    private String deviceName;
    private String platformVersion;
    private int chromeDriverPort;
    private int systemPort;
    private String udid;

    private boolean isAssigned;
}
