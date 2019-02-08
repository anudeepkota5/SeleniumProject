package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//TODO: Need to update as per the Lime project Requirement
public class UsersDataBean {
    private List<User> users;

    //TODO: Need to remeve the methods related to getUser#
    public User getAdmin() {
        List<User> users = this.getUsers();
        return users.get(0);
    }

    //TODO: Need to remeve the methods related to getUser#
    public User getUser1() {
        List<User> users = this.getUsers();
        return users.get(1);
    }

    public User getUser3() {
        List<User> users = this.getUsers();
        return users.get(3);
    }

    public User getUser5() {
        List<User> users = this.getUsers();
        return users.get(5);
    }

    public User getUser15() {
        List<User> users = this.getUsers();
        return users.get(15);
    }

    public User.IndividualProduct getAnyPostPaidProduct() {
        Optional<User> first = this.users.stream()
                .filter(item -> item.getTmhPostPaid().length > 0)
                .findFirst();
        if (first.isPresent())
            return first.get().tmhPostPaid[0];
        else
            return null;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String userName;
        private String passWord;
        private String thaiID;
        private String description;
        private String name;
        private String displayName;
        private String privilege;
        private String truePoints;
        //        private String postPaidNumber;
//        private String prePaidNumber;
//        private String trueVisionNumber;
//        private String trueOnlineNumber;
        private String msAccessToken;
        private IndividualProduct[] tmhPostPaid;
        private IndividualProduct[] tol;
        private IndividualProduct[] tvs;
        private IndividualProduct[] tmhPrePaid;

        @Override
        public String toString() {
            return "name: " + userName + "\tpwd: " + passWord;
        }

        public List<IndividualProduct> getAllProducts() {
            List<IndividualProduct> allProducts = new ArrayList<>();
            allProducts.addAll(Arrays.asList(this.getTmhPostPaid()));
            allProducts.addAll(Arrays.asList(this.getTol()));
            allProducts.addAll(Arrays.asList(this.getTvs()));
            allProducts.addAll(Arrays.asList(this.getTmhPrePaid()));

            return allProducts;
        }

        public IndividualProduct getPrePaidProduct() {
            if (this.getTmhPrePaid().length > 0)
                return this.getTmhPrePaid()[0];
            return null;
        }

        public IndividualProduct getTmhPostPaidProduct() {
            if (this.getTmhPostPaid().length > 0)
                return this.getTmhPostPaid()[0];

            return null;
        }

        public IndividualProduct getProductByID(String productID) {
            List<IndividualProduct> allProducts = getAllProducts();

            Optional<IndividualProduct> any = allProducts.stream().filter(item -> item.getProductId().equalsIgnoreCase(productID)).findAny();
            if (any.isPresent())
                return any.get();
            else
                return null;
        }

        public String getSsoID() {

            String[] split_string = this.msAccessToken.split("\\.");
            String base64EncodedBody = split_string[1];

            Base64 base64Url = new Base64(true);

            String body = new String(base64Url.decode(base64EncodedBody));

            JSONObject bodyJson = null;
            String ssoID = "";
            try {
                bodyJson = new JSONObject(body);

                ssoID = bodyJson.get("ssoid").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String asB64 = null;
            try {
                asB64 = java.util.Base64.getEncoder().encodeToString(ssoID.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return asB64;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class IndividualProduct {
            private String productId;
            private String productType;
            private String status;

        }
    }
}