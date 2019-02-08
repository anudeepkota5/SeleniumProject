package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//ToDo: Need to update
@Deprecated
public class ProductDetailsResponse_Depricated extends BaseResponse {

    @JsonProperty("PRICE_PLAN_INFO")
    private LanguageKeyObject<Name> PRICE_PLAN_INFO;
    @JsonProperty("CONTRACT_INFO")
    private LanguageKeyObject<String> CONTRACT_INFO;
    @JsonProperty("ACCUMULATED_CHARGE")
    private String ACCUMULATED_CHARGE;
    @JsonProperty("CREDIT_LIMIT")
    private String CREDIT_LIMIT;
    @JsonProperty("BILL_CYCLE")
    private String BILL_CYCLE;

    @JsonProperty("BUNDLE_USAGE")
    private BundleUsage BUNDLE_USAGE;

    @JsonProperty("SHARED_PLAN_INFO")
    private List<Object> SHARED_PLAN_INFO;

    @JsonProperty("MULTI_SIM_INFO")
    private List<Object> MULTI_SIM_INFO;

    @JsonProperty("TVS_PACKAGES")
    private LanguageKeyObject<TvsPackage> TVS_PACKAGES;

    @JsonProperty("TOL_SPEEDS")
    private TolSpeeds TOL_SPEEDS;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TolSpeeds {
        private LanguageKeyObject<TolPackage> packages;

    }


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TolPackage {
        private Object displayUnit;
        private String downloadSpeed;
        private String imageURL;
        private String linkURL;
        private String packageName;
        private String unit;
        private String uploadSpeed;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TvsPackage {
        private String channelCountHD;
        private String channelCountSD;
        private String imageURL;
        private String linkURL;
        private String packageName;
        private String price;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BundleUsage {
        @JsonProperty("MAIN_PACKAGE")
        private List<Package> MAIN_PACKAGE;
        @JsonProperty("EXTRA_PACKAGE")
        private List<Package> EXTRA_PACKAGE;
        @JsonProperty("AGGREGATED_QUOTA")
        private AggregatedQuota AGGREGATED_QUOTA;

    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AggregatedQuota {
        private String aggregatedTotalVoiceQuota;
        private String aggregatedRemainingVoiceQuota;
        private Boolean isUnlimitedVoice;
        private String voiceQuotaUnit;
        private String aggregatedTotalDataQuota;
        private String totalDataQuotaUnit;
        private String aggregatedRemainingDataQuota;
        private String totalRemainingDataUnit;
        private String aggregatedRemainingCurrencyQuota;
        private String aggregatedTotalCurrencyQuota;
        private String isUnlimitedData;
        private String currencyQuotaUnit;
        private Boolean isReducedSpeed;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Package {
        private String packageCode;
        private String serviceType;
        private String quotaType;
        private String initialQuota;
        private String remainingQuota;
        private String initialQuotaUnit;
        private String remainingQuotaUnit;
        private String startDate;
        private String endDate;
        private String chargeType;
        private String packageName;
        private String subQuotaType;
        private Boolean isActive;
    }
}
