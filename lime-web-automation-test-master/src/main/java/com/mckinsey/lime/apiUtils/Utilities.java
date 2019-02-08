package com.mckinsey.lime.apiUtils;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.utils.CustomClass;

import java.io.IOException;
import java.util.List;

public class Utilities {
    private static final UsersDataBean usersData = TestData.getApiUsersData();
    private static final ConfigDataBean configData = TestData.getConfigData();

    @Deprecated
    public static ProductsResponse.IndividualProduct getAnyTmhPostPaidNumberWithBalance(CustomClass obj, boolean includeConvergenceProducts) throws IOException {
        for (UsersDataBean.User indUser : usersData.getUsers()) {

            CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(obj, indUser, configData.getApi_channel(), configData.getApi_platform());
            List<ProductsResponse.IndividualProduct> tmhPostPaidProducts = customResponse_Products.getResponseObj().getTmhPostPaidProducts(includeConvergenceProducts);

            if (tmhPostPaidProducts.size() > 0) {
                for (ProductsResponse.IndividualProduct item : tmhPostPaidProducts) {
                    if (Double.parseDouble(item.getBalance()) > 0) {
                        return item;
                    }
                }

            }
        }
        return null;
    }

    @Deprecated
    public static ProductsResponse.IndividualProduct getAnyTmhPrePaidNumber(CustomClass obj) throws IOException {
        for (UsersDataBean.User indUser : usersData.getUsers()) {

            CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(obj, indUser, configData.getApi_channel(), configData.getApi_platform());
            List<ProductsResponse.IndividualProduct> tmhPrePaidProducts = customResponse_Products.getResponseObj().getTmhPrePaidProducts();

            if (tmhPrePaidProducts.size() > 0) {
                return tmhPrePaidProducts.get(0);
            }
        }
        return null;
    }
}
