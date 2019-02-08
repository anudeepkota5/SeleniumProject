package com.mckinsey.lime.utils;


import com.mckinsey.lime.testDataBeans.LocatorsDataBean;
import com.mckinsey.lime.testDataBeans.TestData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class reads the PageObjectRepository text files. Uses buffer reader.
 */
public class ObjectFileReader {


    public static LocatorsDataBean.indLocator getELementFromFile(String pageName, String elementName) {
//        String folderName = TestData.getConfigData().getAppType().toString().toLowerCase();
        String folderName = "desktop";
        String specFilePath = setSpecFilePath(folderName, pageName);

        return getElement(specFilePath, elementName);
    }

    public static String setSpecFilePath(String folderName, String pageName) {
        return "src/main/resources/pageObjects/" + folderName + File.separator + pageName + ".yaml";

    }

    //TODO: Need to refactor. To be deleted mostly.
    private static Stream<String[]> getSpecFileStream(String specFilePath) {
        try {
            return Files.lines(Paths.get(specFilePath))
                    .filter((str) -> !str.startsWith("=="))
                    .filter((str) -> !str.startsWith("#"))
                    .filter((str) -> !str.trim().isEmpty())
                    .filter((str) -> !str.startsWith("Page Title:"))
                    .map(str -> str.replaceAll("[\\s]+", " "))
                    .map(str -> str.split(" ", 3));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static LocatorsDataBean.indLocator getElement(String specFilePath, String elementName) {
        LocatorsDataBean locatorData = TestData.getLocatorData(specFilePath);
        LocatorsDataBean.indLocator indLocator = locatorData.getLocators().stream()
                .filter(item -> item.getLocatorName().equalsIgnoreCase(elementName))
                .findFirst()
                .orElse(null);
        return indLocator;
    }

    //TODO: To be Deleted
    private static String[] getElement1(String specFilePath, String elementName) {
        return getSpecFileStream(specFilePath)
                .filter(str -> str[0].equalsIgnoreCase(elementName))
                .findFirst()
                .get();
    }

    @Deprecated
    private static String setTier() {
        switch (TestData.getConfigData().getDeviceType()) {
            case ANDROID:
                return "ANDROID";
            case IOS:
                return "IOS";
            default:
                return "IOS";
        }
    }

    @Deprecated
    private static String setPlatform() {
        if (TestData.getConfigData().getBrowser().equalsIgnoreCase("mobile"))
            return "Mobile";
        else
            return "Desktop";

    }
}
