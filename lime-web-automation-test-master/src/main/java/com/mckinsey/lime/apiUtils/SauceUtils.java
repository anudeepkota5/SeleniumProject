package com.mckinsey.lime.apiUtils;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SauceUtils {

    static List<HashMap> last20SauceJobs;

    @Deprecated
    public static List<HashMap> getLast20SauceJobs() {
        if (last20SauceJobs == null) {

            System.out.println("Sending sauce Job request");
            try {
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet("https://saucelabs.com/rest/v1/sharma2908abhishek/jobs?limit=10&full=true");

                HttpResponse httpResponse = client.execute(request);
//                String response = RestApiUtils.getResponse(httpResponse);
//                CustomResponse<SauceLabsFullJobDetailsResponse> sauceLabsJobDetailsResponse = new CustomResponse<>(httpResponse, SauceLabsFullJobDetailsResponse.class);
                CustomResponse<HashMap> sauceLabsJobDetailsResponse = new CustomResponse<>(httpResponse, HashMap.class);
                List<HashMap> responseObjList = sauceLabsJobDetailsResponse.getResponseObjList();
                last20SauceJobs = responseObjList;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return last20SauceJobs;
    }
}
