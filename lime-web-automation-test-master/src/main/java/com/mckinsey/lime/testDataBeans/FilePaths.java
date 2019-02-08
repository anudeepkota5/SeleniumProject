package com.mckinsey.lime.testDataBeans;

import java.io.File;

public class FilePaths {

    public final static String USERS_DATA, API_SIT_USERS_DATA, API_UAT_USERS_DATA, API_PREPROD_USERS_DATA;
    public final static String PRODUCTS_DATA;
    public final static String THAIID_DATA;
    public final static String EVVIRONMENTS_DATA;
    public final static String PLANS_DATA;
    public final static String LOCAL_EMULATORS_DATA;
    public final static String CONFIG_DATA;
    public final static String CAPABILITIES_DATA;
    public final static String HTML_REPORT_STRINGS;
    public final static String REDEEM7ELEVEN_DATA;
    public final static String CARDS_DATA;

    public final static String CUSTOMREPORT2_TEMPLATE_TOTALREPORT;
    public final static String CUSTOMREPORT2_TEMPLATE_SCRIPTSFOLDER;
    public final static String CUSTOMREPORT2_TEMPLATE_STYLESFOLDER;
    public final static String CUSTOMREPORT2_TEMPLATE_TOTALROWTEMPLATE;
    public final static String CUSTOMREPORT2_TEMPLATE_TESTTAGTEMPLATE;
    public final static String CUSTOMREPORT2_TEMPLATE_ROWTEMPLATE;
    public final static String CUSTOMREPORT2_TEMPLATE_POPUP;
    public final static String CUSTOMREPORT2_TEMPLATE_POPUP_SCREENSHOT;
    public final static String CUSTOMREPORT2_TEMPLATE_REQUEST_POPUP;
    public final static String CUSTOMREPORT2_TEMPLATE_RESPONSE_POPUP;

    public final static String POST_REQUEST_PRODUCTS;
    public final static String SCREENSHOTS_FOLDER_NAME;
    public final static String SCREENSHOTS_FOLDER;
    public final static String TESTDATA_PRODUCT_TOGGLER, TESTDATA_PRE_TOGGLER;
    public final static String DRIVER_CHROME;
    public final static String DRIVER_FIREFOX;
    public static String SCREENSHOTS_FOLDER_REPORT;

    public final static String API_REQUEST_EDIT_POSSIBLE_FIGURES;
    static {
        final String mainResourcesFolderPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        final String customReport2_templateFolder = mainResourcesFolderPath + "htmlTemplates" + File.separator;
        final String testDataFolder = mainResourcesFolderPath + "testData" + File.separator;

        USERS_DATA = mainResourcesFolderPath + "users.json";
        API_SIT_USERS_DATA = mainResourcesFolderPath + "api-users.json";
        API_UAT_USERS_DATA = mainResourcesFolderPath + "api-users-alpha.json";
        API_PREPROD_USERS_DATA = mainResourcesFolderPath + "api-users-preprod.json";
        PRODUCTS_DATA = mainResourcesFolderPath + "products_alpha.yaml";
        THAIID_DATA = mainResourcesFolderPath + "thaiID.yaml";
        EVVIRONMENTS_DATA = mainResourcesFolderPath + "environment.json";
        PLANS_DATA = mainResourcesFolderPath + "plans.json";
        LOCAL_EMULATORS_DATA = mainResourcesFolderPath + "localEmulators.json";
        CONFIG_DATA = mainResourcesFolderPath + "config.yaml";
        CAPABILITIES_DATA = mainResourcesFolderPath + "capabilities.yaml";
        CARDS_DATA = mainResourcesFolderPath + "cards.yaml";
        HTML_REPORT_STRINGS = mainResourcesFolderPath + "htmlReportStrings.yaml";
        REDEEM7ELEVEN_DATA = testDataFolder + "Redeem7Eleven.yaml";

        CUSTOMREPORT2_TEMPLATE_TOTALREPORT = customReport2_templateFolder + "/TotalReport_Template.html";

        CUSTOMREPORT2_TEMPLATE_SCRIPTSFOLDER = customReport2_templateFolder + "Scripts";
        CUSTOMREPORT2_TEMPLATE_STYLESFOLDER = customReport2_templateFolder + "Styles";

        CUSTOMREPORT2_TEMPLATE_TOTALROWTEMPLATE = customReport2_templateFolder + "TotalRowtemplate.html";
        CUSTOMREPORT2_TEMPLATE_TESTTAGTEMPLATE = customReport2_templateFolder + "testTagResult.html";
        CUSTOMREPORT2_TEMPLATE_ROWTEMPLATE = customReport2_templateFolder + "Rowtemplate.html";
        CUSTOMREPORT2_TEMPLATE_POPUP = customReport2_templateFolder + "CellPopup.html";
        CUSTOMREPORT2_TEMPLATE_POPUP_SCREENSHOT = customReport2_templateFolder + "ImagePopup.html";
        CUSTOMREPORT2_TEMPLATE_REQUEST_POPUP = customReport2_templateFolder + "RequestPopup.html";
        CUSTOMREPORT2_TEMPLATE_RESPONSE_POPUP = customReport2_templateFolder + "ResponsePopup.html";

        POST_REQUEST_PRODUCTS = mainResourcesFolderPath + "apiRequests/IndProductTypes.json";

        SCREENSHOTS_FOLDER_NAME = "screenshots/";
        SCREENSHOTS_FOLDER = System.getProperty("user.dir") + "/" + SCREENSHOTS_FOLDER_NAME;

        TESTDATA_PRODUCT_TOGGLER = testDataFolder + "ProductToggler.xlsx";
        TESTDATA_PRE_TOGGLER = testDataFolder + "PreToggler.xlsx";

        DRIVER_CHROME = mainResourcesFolderPath + "drivers" + File.separator + "chromedriver";
        DRIVER_FIREFOX = mainResourcesFolderPath + "drivers" + File.separator + "geckodriver";

        API_REQUEST_EDIT_POSSIBLE_FIGURES = mainResourcesFolderPath + "apiRequests" + File.separator + "EditPossibleFigures.json";
    }
}
