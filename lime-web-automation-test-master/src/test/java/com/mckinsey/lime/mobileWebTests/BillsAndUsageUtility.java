package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse_Depricated;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.mobilePages.BillsAndUsagePage;
import com.mckinsey.lime.mobilePages.LandingPage;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;

import java.io.IOException;

@Deprecated
public class BillsAndUsageUtility {
    private final ConfigDataBean configData = TestData.getConfigData();

    public void billsAndUsageScreen(CustomClass customClassObj, ITestContext context, AppiumDriver driver, UsersDataBean.User indUser, ProductsResponse.Product item, ProductsResponse productsResponse) {
        LandingPage landingPage = new LandingPage(driver);
        BillsAndUsagePage billsAndUsagePage = new BillsAndUsagePage(driver);

        //TODO: need to add Convergence Products as well
        //TODO: Need to check/count PrePaid Products separatelyxx
        if (item instanceof ProductsResponse.IndividualProduct) {
            ProductsResponse.IndividualProduct indProduct = (ProductsResponse.IndividualProduct) item;

            if (((ProductsResponse.IndividualProduct) item).isTmhIndividualProduct()) {
                if (productsResponse.isBusinessSIM(indProduct)) {
                    CustomAssertions.assertFalse(customClassObj, billsAndUsagePage.isAmountDisplayedForProduct(indProduct),
                            "Verified Whether Balance is displayed or not for business product: " + indProduct.getProductId());

                } else {
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getAmountForProducts(indProduct), indProduct.getBalance(), "Balance of Product " + indProduct.getProductId() + " is Verified");

                }

                CustomAssertions.assertTrue(customClassObj, billsAndUsagePage.isProductDisplayed(indProduct), "Verified whether product " + indProduct.getProductId() + " is displayed or not");

                if (!billsAndUsagePage.isProductCardExpanded(indProduct)) {
                    billsAndUsagePage.clickProductExpandIcon(indProduct);

                }

                try {

                    CustomResponse<ProductDetailsResponse_Depricated> customResponse_ProductDetails = ApiRequests.sendProductDetailsAPI(customClassObj, indUser, indProduct, configData.getApi_channel(), configData.getApi_platform());
                    CustomAssertions.assertText(customClassObj, customResponse_ProductDetails.getResponseCode(), 200, "Verified the PRODUCT DETAILS API response code of " + indProduct.getProductId() + "as 200");

                    ProductDetailsResponse_Depricated responseObj = customResponse_ProductDetails.getResponseObj();
                    // Price Plan Name
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getPricePlanName(indProduct),
                            responseObj.getPRICE_PLAN_INFO().getEn().getName(),
                            "Verified the pricePlan name for " + indProduct.getProductId());
                    // Get Number of extra Packages text
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getNumberOfExtraPackagesText(indProduct),
                            String.valueOf(responseObj.getBUNDLE_USAGE().getEXTRA_PACKAGE().size()),
                            "Verified the number of Extra Packages for " + indProduct.getProductId());

                    billsAndUsagePage.clickViewPackageDetailIcon(indProduct);

                    if (((ProductsResponse.IndividualProduct) item).isTmhPostPaidIndividualProduct()) {
                        CustomAssertions.assertText(customClassObj, billsAndUsagePage.getNumberOfMainPackages(), responseObj.getBUNDLE_USAGE().getMAIN_PACKAGE().size(),
                                "Verified the number of Main Packages for " + indProduct.getProductId());

                        CustomAssertions.assertText(customClassObj, billsAndUsagePage.getBillCycleDate(), responseObj.getBILL_CYCLE(),
                                "Verified the BILL CYCLE value for " + indProduct.getProductId());

                        billsAndUsagePage.clickUsageDetailsTab();
                    }

                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getNumberOfMainAndExtraPackages(),
                            responseObj.getBUNDLE_USAGE().getMAIN_PACKAGE().size() + responseObj.getBUNDLE_USAGE().getEXTRA_PACKAGE().size(),
                            "Verified the number of Main and Extra packages displayed in Usage Details tab for " + indProduct.getProductId());

                    billsAndUsagePage.closePopupUp();


                } catch (IOException e) {
                    System.out.println("Exception occurred for " + item);
                    e.printStackTrace();
                }


            } else if (((ProductsResponse.IndividualProduct) item).isTvsIndividualProduct()) {

                CustomAssertions.assertText(customClassObj, billsAndUsagePage.getAmountForProducts(indProduct), indProduct.getBalance(), "Balance of Product " + indProduct.getProductId() + " is Verified");

                CustomAssertions.assertTrue(customClassObj, billsAndUsagePage.isProductDisplayed(indProduct), "Verified whether product " + indProduct.getProductId() + " is displayed or not");

                if (!billsAndUsagePage.isProductCardExpanded(indProduct)) {
                    billsAndUsagePage.clickProductExpandIcon(indProduct);

                }

                try {

                    CustomResponse<ProductDetailsResponse_Depricated> customResponse_ProductDetails = ApiRequests.sendProductDetailsAPI(customClassObj, indUser, indProduct, configData.getApi_channel(), configData.getApi_platform());

                    ProductDetailsResponse_Depricated responseObj = customResponse_ProductDetails.getResponseObj();
                    // Price Plan Name
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getPricePlanName(indProduct),
                            responseObj.getPRICE_PLAN_INFO().getEn().getName(),
                            "Verified the pricePlan name for " + indProduct.getProductId());

                    // Verify channels
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getHdChannelsCount_billsUsageScreen(indProduct),
                            responseObj.getTVS_PACKAGES().getEn().getChannelCountHD(),
                            "Verified the number of HD channels for product " + indProduct.getProductId() + " in Bills & Usage screen");
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getNormalChannelsCount_billsUsageScreen(indProduct),
                            responseObj.getTVS_PACKAGES().getEn().getChannelCountSD(),
                            "Verified the number of channels for product " + indProduct.getProductId() + " in Bills & Usage screen");

                    billsAndUsagePage.clickViewPackageDetailIcon(indProduct);

                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getBillCycleDate(), responseObj.getBILL_CYCLE(),
                            "Verified the BILL CYCLE value for " + indProduct.getProductId());

                    // Verify Channels
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getHdChannelsCount_packageDetailsScreen(indProduct),
                            responseObj.getTVS_PACKAGES().getEn().getChannelCountHD(),
                            "Verified the number of HD channels for product " + indProduct.getProductId() + " in Package Details screen");
                    CustomAssertions.assertText(customClassObj, billsAndUsagePage.getNormalChannelsCount_packageDetailsScreen(indProduct),
                            responseObj.getTVS_PACKAGES().getEn().getChannelCountSD(),
                            "Verified the number of HD channels for product " + indProduct.getProductId() + " in Package Details screen");

                    billsAndUsagePage.closePopupUp();


                } catch (IOException e) {
                    System.out.println("Exception occurred for " + item);
                    e.printStackTrace();
                }

            } else if (((ProductsResponse.IndividualProduct) item).isTolIndividualProduct()) {

            }

        } else if (item instanceof ProductsResponse.ConvergenceProduct) {

        }
    }
}
