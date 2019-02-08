package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//ToDo: Need to update as per the latest design
@Deprecated
public class ProductsResponse extends BaseResponse {

    private Products products;
    private CustomerInfo customerInfo;

    /********************************************************************************************/

    //TODO: Need to add logic to get Products out of conv bundle
    public List<IndividualProduct> getAllProductsWithOutConvergence() {
        return getAllProducts(false);
    }

    //TODO: Need to add logic to get Products out of conv bundle
    public List<IndividualProduct> getAllPostPaidProducts(boolean includeConvergence) {
        return getAllProducts(false, false).stream()
                .filter(item -> item.getSubscriptionType().equalsIgnoreCase("POSTPAID"))
                .collect(Collectors.toList());
    }

    public List<IndividualProduct> getAllProductsIncludingConvergence() {
        return getAllProducts(true);
    }

    public List<IndividualProduct> getAllProducts(boolean includeConvergenceProducts) {
        return getAllProducts(includeConvergenceProducts, true);
    }

    public List<IndividualProduct> getAllProducts(boolean includeConvergenceProducts, boolean includeBusinessProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPostpaid);
        allProducts.addAll(this.products.tvs);
        allProducts.addAll(this.products.tol);
        if (includeBusinessProducts)
            allProducts.addAll(this.products.business);
        allProducts.addAll(this.products.tmhPrepaid);

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts()));

        return allProducts;
    }

    public List<Product> getProductCards() {
        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPostpaid);
        allProducts.addAll(this.products.tvs);
        allProducts.addAll(this.products.tol);
        allProducts.addAll(this.products.business);
        allProducts.addAll(this.products.conv);
        allProducts.addAll(this.products.tmhPrepaid);

        return allProducts;
    }


    public List<IndividualProduct> getAllProducts4CPP() {

        return getTmhPostPaidProducts(false);
    }

    /********************************************************************************************/
    /***************              Utility methods             ***********************************/

    public List<IndividualProduct> getActiveProducts(boolean includeConvergenceProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPostpaid.stream().filter(item -> item.status.equalsIgnoreCase("ACTIVE")).collect(Collectors.toList()));
        allProducts.addAll(this.products.tvs.stream().filter(item -> item.status.equalsIgnoreCase("ACTIVE")).collect(Collectors.toList()));
        allProducts.addAll(this.products.tol.stream().filter(item -> item.status.equalsIgnoreCase("ACTIVE")).collect(Collectors.toList()));
        allProducts.addAll(this.products.business.stream().filter(item -> item.status.equalsIgnoreCase("ACTIVE")).collect(Collectors.toList()));

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts().stream().filter(item -> item.status.equalsIgnoreCase("ACTIVE")).collect(Collectors.toList())));

        return allProducts;
    }

    //TODO: need to confirm the excat string SUSPEND
    public List<IndividualProduct> getSuspendedProducts(boolean includeConvergenceProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPostpaid.stream().filter(item -> item.status.equalsIgnoreCase("SUSPEND")).collect(Collectors.toList()));
        allProducts.addAll(this.products.tvs.stream().filter(item -> item.status.equalsIgnoreCase("SUSPEND")).collect(Collectors.toList()));
        allProducts.addAll(this.products.tol.stream().filter(item -> item.status.equalsIgnoreCase("SUSPEND")).collect(Collectors.toList()));
        allProducts.addAll(this.products.business.stream().filter(item -> item.status.equalsIgnoreCase("SUSPEND")).collect(Collectors.toList()));

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts().stream().filter(item -> item.status.equalsIgnoreCase("SUSPEND")).collect(Collectors.toList())));

        return allProducts;
    }

    //TODO: need to confirm the excat string CANCEL
    public List<IndividualProduct> getCancelledProducts(boolean includeConvergenceProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPostpaid.stream().filter(item -> item.status.equalsIgnoreCase("CANCEL")).collect(Collectors.toList()));
        allProducts.addAll(this.products.tvs.stream().filter(item -> item.status.equalsIgnoreCase("CANCEL")).collect(Collectors.toList()));
        allProducts.addAll(this.products.tol.stream().filter(item -> item.status.equalsIgnoreCase("CANCEL")).collect(Collectors.toList()));
        allProducts.addAll(this.products.business.stream().filter(item -> item.status.equalsIgnoreCase("CANCEL")).collect(Collectors.toList()));

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts().stream().filter(item -> item.status.equalsIgnoreCase("CANCEL")).collect(Collectors.toList())));

        return allProducts;
    }

    //TODO: need to confirm String for product type
    public List<IndividualProduct> getTmhPostPaidProducts(boolean includeConvergenceProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPostpaid);

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts().stream().filter(item -> item.productType.isEmpty()).collect(Collectors.toList())));

        return allProducts;
    }

    //TODO: need to confirm String for product type
    public List<IndividualProduct> getTmhProducts(boolean includeConvergenceProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPostpaid);
        allProducts.addAll(this.products.tmhPrepaid);

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts().stream().filter(item -> item.productType.equalsIgnoreCase("TrueMoveH")).collect(Collectors.toList())));

        return allProducts;
    }

    //TODO: need to confirm String for product type
    public List<IndividualProduct> getTmhPrePaidProducts() {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tmhPrepaid);

        return allProducts;
    }

    //TODO: need to confirm String for product type
    public List<IndividualProduct> getTolProducts(boolean includeConvergenceProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tol);

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts().stream().filter(item -> item.productType.isEmpty()).collect(Collectors.toList())));

        return allProducts;
    }

    //TODO: need to confirm String for product type
    public List<IndividualProduct> getTvsProducts(boolean includeConvergenceProducts) {
        List<IndividualProduct> allProducts = new ArrayList<>();
        allProducts.addAll(this.products.tvs);

        if (includeConvergenceProducts)
            this.products.conv.forEach(indConv -> allProducts.addAll(indConv.getLinkedProducts().stream().filter(item -> item.productType.isEmpty()).collect(Collectors.toList())));

        return allProducts;
    }

    //TODO: Yet to omplement
    public boolean isSharedProduct(IndividualProduct product) {
        return false;
    }

    //TODO: Yet to omplement
    public boolean isMultiSimProduct(IndividualProduct product) {
        return false;
    }

    public boolean isBusinessSIM(IndividualProduct product) {
        long count = this.products.business.stream()
                .map(item -> item.getProductId())
                .filter(item -> item.equalsIgnoreCase(product.getProductId()))
                .count();

        if (count > 0) return true;
        else return false;
    }

    public List<String> getAllConvergenceInvoiceIdentifiers() {

        return this.products.conv.stream().map(item -> item.getLinkedProducts().get(0).getConvergenceInvoiceIdentifier()).collect(Collectors.toList());

    }

    public List<String> getAllAccountIDs4NonConvergenceProducts() {

        return this.getAllProductsWithOutConvergence().stream().map(item -> item.getAccountId()).collect(Collectors.toList());

    }

    public List<IndividualProduct> getAllConvergenceProducts() {
        return this.products.conv.stream().map(item -> item.getLinkedProducts()).flatMap(List::stream).collect(Collectors.toList());
    }

    public List<IndividualProduct> getAllProductsForBuyExtraPackage() {

        return getAllProducts(true, false)
                .stream()
                .filter(item -> item.getProductType().equalsIgnoreCase("TrueMoveH")
                        || item.getProductType().equalsIgnoreCase("TrueVision"))
                .filter(item -> item.getStatus().equalsIgnoreCase("ACTIVE")
                        || item.getStatus().equalsIgnoreCase("SOFT_SUSPEND"))
                .collect(Collectors.toList());
    }


    public List<IndividualProduct> getAllProductsForChangePackage() {

        return getAllProducts(false, false)
                .stream()
                .filter(item -> item.getProductType().equalsIgnoreCase("TrueMoveH"))
                .filter(item -> item.getSubscriptionType().equalsIgnoreCase("POSTPAID"))
                .filter(item -> item.getStatus().equalsIgnoreCase("ACTIVE"))
                .collect(Collectors.toList());
    }

    public List<IndividualProduct> getProductsForPaymentHistory() {
        List<IndividualProduct> tmhPostPaidProducts = getTmhPostPaidProducts(true);
        List<IndividualProduct> tolProducts = getTolProducts(true);
        List<IndividualProduct> tvsProducts = getTvsProducts(true);

        List<IndividualProduct> collect = new ArrayList<>();
        collect.addAll(tmhPostPaidProducts);
        collect.addAll(tolProducts);
        collect.addAll(tvsProducts);

        return collect;
    }

    public List<Product> getProductsForBillingHistory() {
        List<IndividualProduct> tmhPostPaidProducts = getTmhPostPaidProducts(false);
        List<IndividualProduct> tolProducts = getTolProducts(false);
        List<IndividualProduct> tvsProducts = getTvsProducts(false);
        List<ConvergenceProduct> conv = getProducts().getConv();

        List<Product> collect = new ArrayList<>();
        collect.addAll(tmhPostPaidProducts);
        collect.addAll(tolProducts);
        collect.addAll(tvsProducts);
        collect.addAll(conv);

        return collect;
    }

    public interface Product {

    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Products {
        private List<String> tflp = new ArrayList<>();
        private List<IndividualProduct> tmhPostpaid = new ArrayList<>();
        private List<IndividualProduct> tvs = new ArrayList<>();
        private List<IndividualProduct> tol = new ArrayList<>();
        private List<ConvergenceProduct> conv = new ArrayList<>();
        private List<IndividualProduct> business = new ArrayList<>();
        private List<IndividualProduct> tmhPrepaid = new ArrayList<>();


    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConvergenceProduct implements Product {
        private String bundle;
        private String name;
        private List<IndividualProduct> linkedProducts = new ArrayList<>();
        private String productType;
        private String subscriptionType;
        private String balance;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndividualProduct implements Product {
        private String productId;
        private String subscriberId;
        private String customerId;
        private String accountId;
        private String balance;
        private String actualBalance;
        private String subscriptionType;
        private String name;
        private String statusCode;
        private String status;
        private String productType;
        private String convergenceCode;
        private String bundleName;
        private String assertGroupId;
        private String uniqueBundleIdentifier;
        private String isBillConsolidated;
        private String convergenceInvoiceIdentifier;
        private String companyCode;
        private String merchantName;

        private String mappingType;

        // for prepaid products
        private String expiryDate;
        private List<IndividualProduct> linkedProducts;

        public String getBalance() {
            return String.format("%.2f", Double.parseDouble(balance));
        }

        public boolean isPartOfConvergenceProduct() {

            return this.convergenceCode != null;
        }

        public boolean isTmhPostPaidIndividualProduct() {
            if (!isPartOfConvergenceProduct()) {
                return this.getProductType().equalsIgnoreCase("TrueMoveH") && this.getSubscriptionType().equalsIgnoreCase("POSTPAID");
            } else return false;
        }

        public boolean isTmhIndividualProduct() {
            if (!isPartOfConvergenceProduct()) {
                return this.getProductType().equalsIgnoreCase("TrueMoveH");
            } else return false;
        }

        public boolean isTmhPrePaidIndividualProduct() {
            if (!isPartOfConvergenceProduct()) {
                return this.getProductType().equalsIgnoreCase("TrueMoveH") && this.getSubscriptionType().equalsIgnoreCase("PREPAID");
            } else return false;
        }

        public boolean isTvsIndividualProduct() {
            if (!isPartOfConvergenceProduct()) {
                return this.getProductType().equalsIgnoreCase("TrueVision");
            } else return false;
        }

        public boolean isTolIndividualProduct() {
            if (!isPartOfConvergenceProduct()) {
                return this.getProductType().equalsIgnoreCase("TrueOnline") && this.getLinkedProducts() == null;
            } else return false;
        }


        public boolean isTFLProduct() {
            if (!isPartOfConvergenceProduct()) {
                return this.getProductType().equalsIgnoreCase("TrueOnline") && this.getLinkedProducts() != null;
            } else return false;
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomerInfo {
        private String nameType;
        private String firstName;
        private String lastName;
        private String title;

    }
}


