package com.mckinsey.lime.utils;

import com.mckinsey.lime.testDataBeans.FilePaths;
import com.mckinsey.lime.testDataBeans.TestData;

import java.time.LocalDate;

interface EnvironmentData {
    //Doesn't require for the Lime Insurance app
    @Deprecated
    String getAuthorization();

    String getBaseURL();

    String getTestDataPath();

    String getWebAppUrl();

    String getAuthority();
}

public class CommonEnums {
    public enum DeviceType {
        IOS, ANDROID;
    }

    public enum BrowserType {
        CHROME, FIREFOX;
    }

    public enum TestEnvironment {
        LOCAL, SAUCE;
    }

    public enum AppType {
        MOBILE, DESKTOP;
    }

    public enum Locators {
        ID, NAME, CLASSNAME, CSS, XPATH, LINKTEXT, ACCESSIBILITY;
    }

    public enum AppEnvironment implements EnvironmentData {
        SIT {
            @Override
            public String getAuthorization() {
                return TestData.getEnvironmentsData().getSit().getAuthorization();
            }

            @Override
            public String getBaseURL() {
                return TestData.getEnvironmentsData().getSit().getBaseURL();
            }

            @Override
            public String getTestDataPath() {
                return FilePaths.API_SIT_USERS_DATA;
            }

            @Override
            public String getWebAppUrl() {
                return TestData.getEnvironmentsData().getSit().getWebAppUrl();
            }

            @Override
            public String getAuthority() {
                return TestData.getEnvironmentsData().getSit().getAuthority();
            }
        }, UAT {
            @Override
            public String getAuthorization() {
                return TestData.getEnvironmentsData().getUat().getAuthorization();
            }

            @Override
            public String getBaseURL() {
                return TestData.getEnvironmentsData().getUat().getBaseURL();
            }

            @Override
            public String getTestDataPath() {
                return FilePaths.API_UAT_USERS_DATA;
            }

            @Override
            public String getWebAppUrl() {
                return TestData.getEnvironmentsData().getUat().getWebAppUrl();
            }

            @Override
            public String getAuthority() {
                return TestData.getEnvironmentsData().getUat().getAuthority();
            }
        }, PROD {
            @Override
            public String getAuthorization() {
                return "";
            }

            @Override
            public String getBaseURL() {
                return "";
            }

            @Override
            public String getTestDataPath() {
                return "";
            }

            @Override
            public String getWebAppUrl() {
                return "";
            }

            @Override
            public String getAuthority() {
                return TestData.getEnvironmentsData().getProd().getAuthority();
            }
        };


    }
}

