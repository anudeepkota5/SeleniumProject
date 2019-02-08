package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.Utilities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigDataBean {

    private String browser;
    private int implicitTimeOut;
    private int explicitTimeOut;
    private int explicitTimeOutType2;
    private String seleniumServer;
    private CommonEnums.TestEnvironment testEnvironment;
    private CommonEnums.AppEnvironment appEnvironment;
    private CommonEnums.AppType appType;
    private CommonEnums.DeviceType deviceType;
    private CommonEnums.BrowserType browserType;
    private String sauceLabURL;
    private String testObjectURL;
    private String authorization;
    private String devAuthorization;
    private String api_channel;
    private String api_platform;
    private Boolean slackReport;

    public void setImplicitTimeOut(int implicitTimeOut) {
        String property = Utilities.getEffectiveConfig("implicitTimeOut", String.valueOf(implicitTimeOut));
        this.implicitTimeOut = Integer.parseInt(property);
    }

    public void setTestEnvironment(String testEnvironment) {
        this.testEnvironment = CommonEnums.TestEnvironment.valueOf(Utilities.getEffectiveConfig("testEnvironment", testEnvironment));
    }

    public void setAppEnvironment(String appEnvironment) {
        this.appEnvironment = CommonEnums.AppEnvironment.valueOf(Utilities.getEffectiveConfig("appEnvironment", appEnvironment));
    }

    public void setAppType(String appType) {
        this.appType = CommonEnums.AppType.valueOf(Utilities.getEffectiveConfig("appType", appType));
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = CommonEnums.DeviceType.valueOf(Utilities.getEffectiveConfig("deviceType", deviceType));
    }

    public void setBrowserType(String browserType) {
        this.browserType = CommonEnums.BrowserType.valueOf(Utilities.getEffectiveConfig("browserType", browserType));
    }

    public void setApi_channel(String api_channel) {
        this.api_channel = Utilities.getEffectiveConfig("api_channel", api_channel);
    }

    public void setApi_platform(String api_platform) {
        this.api_platform = Utilities.getEffectiveConfig("api_platform", api_platform);
    }

    public void setSlackReport(String slackReport) {
        this.slackReport = Boolean.valueOf(Utilities.getEffectiveConfig("slackReport", slackReport));
    }

    public boolean isAndroidBrowser() {
        return this.getAppType() == CommonEnums.AppType.MOBILE && this.getBrowserType() == CommonEnums.BrowserType.CHROME;
    }

}
