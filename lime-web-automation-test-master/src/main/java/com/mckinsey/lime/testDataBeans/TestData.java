package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//TODO: Need to synchronise
public class TestData {

    private static ConfigDataBean configData;
    private static HashMap<String, LocatorsDataBean> locatorsData = new HashMap<>();
    private static CapabilitiesDataBean capabilitiesData;
    private static UsersDataBean usersData;
    private static UsersDataBean apiUsersData;
    private static UsersDataBean usersDataJson;
    private static HtmlReportStringsBean htmlReportStrings;
    private static Redeem7ElevenDataBean redeem7ElevenData;
    private static CardsDataBean cardsData;
    private static ProductsDataBean productsDataBean;
    private static AllThaiIDs thaiIDsDataBean;
    private static EnvironmentDataBean environmentsDataBean;
    private static PlansConfigDataBean plansConfigDataBean;
    private static List<LocalEmulator> localEmulators;

    public static ConfigDataBean getConfigData() {
        if (configData == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                configData = mapper.readValue(new File(FilePaths.CONFIG_DATA), ConfigDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configData;
    }

    public static CapabilitiesDataBean getCapabilitiesData() {
        if (capabilitiesData == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                capabilitiesData = mapper.readValue(new File(FilePaths.CAPABILITIES_DATA), CapabilitiesDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return capabilitiesData;
    }

    public static UsersDataBean getUsersData() {
        if (usersData == null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                usersData = mapper.readValue(new File(FilePaths.USERS_DATA), UsersDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usersData;
    }

    public static UsersDataBean getApiUsersData() {
        if (apiUsersData == null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                apiUsersData = mapper.readValue(new File(getConfigData().getAppEnvironment().getTestDataPath()), UsersDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return apiUsersData;
    }

    public static HtmlReportStringsBean getHtmlReportStrings() {
        if (htmlReportStrings == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                htmlReportStrings = mapper.readValue(new File(FilePaths.HTML_REPORT_STRINGS), HtmlReportStringsBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return htmlReportStrings;
    }

    @Deprecated
    public static Redeem7ElevenDataBean getRedeam7ElevenData() {
        if (redeem7ElevenData == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                redeem7ElevenData = mapper.readValue(new File(FilePaths.REDEEM7ELEVEN_DATA), Redeem7ElevenDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return redeem7ElevenData;
    }

    public static CardsDataBean getCardsData() {
        if (cardsData == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                cardsData = mapper.readValue(new File(FilePaths.CARDS_DATA), CardsDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cardsData;
    }

    @Deprecated
    public static ProductsDataBean getProductsData() {
        if (productsDataBean == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                productsDataBean = mapper.readValue(new File(FilePaths.PRODUCTS_DATA), ProductsDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return productsDataBean;
    }

    @Deprecated
    public static AllThaiIDs getThaiIDsData() {
        if (thaiIDsDataBean == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                thaiIDsDataBean = mapper.readValue(new File(FilePaths.THAIID_DATA), AllThaiIDs.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return thaiIDsDataBean;
    }

    public static EnvironmentDataBean getEnvironmentsData() {
        if (environmentsDataBean == null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                environmentsDataBean = mapper.readValue(new File(FilePaths.EVVIRONMENTS_DATA), EnvironmentDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return environmentsDataBean;
    }

    public static synchronized List<LocalEmulator> getLocalEmulators() {
        if (localEmulators == null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                TypeReference<List<LocalEmulator>> typeReference = new TypeReference<List<LocalEmulator>>() {
                };
                localEmulators = mapper.readValue(new File(FilePaths.LOCAL_EMULATORS_DATA), typeReference);
//                o.stream()
//                        .forEach(item -> {
//                            LocalEmulator localEmulator = new LocalEmulator();
//                            localEmulator.setCapabilities(item);
//                            localEmulators.add(localEmulator);
//                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return localEmulators;
    }


    public static synchronized LocatorsDataBean getLocatorData(String filePath) {
        LocatorsDataBean locatorsDataBean = locatorsData.get(filePath);

        if (locatorsDataBean == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                locatorsDataBean = mapper.readValue(new File(filePath), LocatorsDataBean.class);
                locatorsData.put(filePath, locatorsDataBean);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return locatorsDataBean;
    }


    public static PlansConfigDataBean getPlansConfigData() {
        if (plansConfigDataBean == null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                plansConfigDataBean = mapper.readValue(new File(FilePaths.PLANS_DATA), PlansConfigDataBean.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return plansConfigDataBean;
    }
}
