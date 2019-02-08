package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mckinsey.lime.guiDataBeans.ProductCardBean;
import com.mckinsey.lime.testDataBeans.Plan;
import com.mckinsey.lime.utils.JavaUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailsResponse extends BaseResponse {

    private List<IndProductDetails> product;

    /**
     * Utility methods for list of products
     */

    public int getAllProtectionPlanCount() {
        return this.product.stream()
                .filter(IndProductDetails::isProtectionPlan)
                .map(item -> 1)
                .reduce(0, Integer::sum);
    }

    public List<IndProductDetails> getAllProtectionPlans() {
        return this.product.stream()
                .filter(IndProductDetails::isProtectionPlan)
                .collect(Collectors.toList());
    }

    public int getAllSavingsPlanCount() {
        return this.product.stream()
                .filter(IndProductDetails::isSavingsPlan)
                .map(item -> 1)
                .reduce(0, Integer::sum);
    }

    public List<IndProductDetails> getAllSavingsPlans() {
        return this.product.stream()
                .filter(IndProductDetails::isSavingsPlan)
                .collect(Collectors.toList());
    }

    public IndProductDetails getProductDetailsByName(String planName) {

        String[] split = planName.split("\n");
        String cardName = split[0];

        String cardSubTitle = "";
        try {
            cardSubTitle = split[1];
        } catch (IndexOutOfBoundsException e) {
        }

        return getProductDetailsByTitles(cardName, cardSubTitle);

        /*for (ProductDetailsResponse.IndProductDetails indProduct: product) {
            String namefromAPI = indProduct.getPlanTitle();
            System.out.println(namefromAPI +" == "+cardName);

            if (namefromAPI.equalsIgnoreCase(cardName)) {
                if (indProduct.getSubTitle().equalsIgnoreCase(finalCardSubTitle)){
                    return indProduct;
                }
            }

        }
        return null;*/
    }

    public String getPlanCode(ProductCardBean productCardBean) {
        return getProduct().stream()
                .filter(item -> item.getName().trim().equalsIgnoreCase(productCardBean.getProductTitle().trim()))
                .filter(item -> item.getSubTitle().trim().equalsIgnoreCase(productCardBean.getProductSubTitle().trim()))
                .map(item -> item.getPlanCode())
                .findFirst()
                .orElse("");

    }

    public IndProductDetails getProductDetailsByTitles(String cardName, String finalCardSubTitle) {

        return this.product.stream()
                .filter(item -> item.getName().equalsIgnoreCase(cardName))
                .filter(item -> item.getSubTitle().equalsIgnoreCase(finalCardSubTitle))
                .findFirst()
                .orElse(null);
    }

    public IndProductDetails getProductDetailsByTitles(Plan plan) {
        return getProductDetailsByTitles(plan.getPlanTitle(), plan.getPlanSubTitle());
    }

        @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndProductDetails {
        private String planType;
        private String name;
        private String subTitle;
        private String description;
        private String productType;
        private String mascotImageUrl;
        private String primaryColor;
        private String secondaryColor;
        private List<Compare> compare;
        private String planCode;
        private String remark;
        private String terms;
        private String benefitTitle;
        private String benefitDescription;
        private List<BenefitTabs> benefitTabs;
        private String receive;
        private String premiumTitle;
        private String premiumDescription;
        private String policyTitle;
        private String policyDescription;
        private String otherbenefits;
        private String otherHighlights;
        private String optionalrider;
        private String notes;
        private String pdfUrl;
        private String disclaimerDescription;
        private String otherSimilarPlans;
        private String policyAndPremiumTitle;
        private String policyAndPremiumDescription;
        private String benefitText;
        private String payoutTitle;
        private String payoutDescription;
        private String key;

        /**
         * Trimming because for one of the Product name (DIRECT - Term (Non-Renewable)) is coming with trailing space
         *
         * @return
         */
        public String getName() {
            return name.trim();
        }

        /**
         * Trimming because for one of the Product description is coming with trailing space
         *
         * @return
         */
        public String getDescription() {
            return description.trim();
        }

        public String getBenefitText() {
            return benefitText.trim();
        }

        public String getotherHighlights() {
            return otherHighlights.trim();
        }

        public String getoptionalrider() {
            return optionalrider.trim();
        }

        /**
         * Utility methods for individual products
         */

        public boolean isProtectionPlan() {
            return this.planType.equalsIgnoreCase("Protection");
        }

        public boolean isSavingsPlan() {
            return this.planType.equalsIgnoreCase("Savings");
        }

        public Compare getCompareSectionObjectByName(String sectionTitle) {
            return getCompare().stream()
                    .filter(item -> item.getTitle().equalsIgnoreCase(sectionTitle))
                    .findFirst()
                    .orElse(null);

        }

        public String getCompareObjectDescriptionByTitle(String sectionTitle) {
            return getCompare().stream()
                    .filter(item -> item.getTitle().equalsIgnoreCase(sectionTitle))
                    .map(item -> item.description)
                    .map(item -> item.replace("<br/>", "\n"))
                    .map(item -> item.replace("<br />", "\n"))
                    .map(item -> item.replace("<p>", ""))
                    .map(item -> item.replace("</p>", ""))
                    // below 2 mapping are the workAround to remove the extra space displayed on UI next to String "Death"
                    .map(item -> item.replace(" \n", "\n"))
                    .map(item -> JavaUtils.trimStringOfMultipleLines(item))
                    .findFirst()
                    .orElse(null);

        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Compare {
            private String title;
            private String key;
            private String description;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class BenefitTabs {
            private String name;
            private List<BenefitTabFeatures> features;
            private String receiveText;

            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class BenefitTabFeatures {
                private String iconUrl;
                private String text;
            }
        }
    }
}
