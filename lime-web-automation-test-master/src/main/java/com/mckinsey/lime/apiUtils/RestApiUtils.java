package com.mckinsey.lime.apiUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mckinsey.lime.utils.CustomLogging;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

//ToDo: Need to update as per the latest design
public class RestApiUtils {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static HttpResponse sendingGetRequest(String urlString, Map<String, String> headers) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(urlString);
        headers.put("User-Agent", USER_AGENT);
//        headers.put("Authorization", TestData.getConfigData().getAppEnvironment().getAuthorization());
        headers.put("Content-Type", "application/json");
//        headers.put("device-id", "3C056156-A760-424D-BE15-0C3A3315D4EC");

        headers.forEach((k, v) -> request.addHeader(k, v));

        HttpResponse response = client.execute(request);
        CustomLogging.writeToReport("Request: " + urlString);
        CustomLogging.writeToReport("Request Headers: " + headers);

        return response;

    }

    public static String getResponse(HttpResponse con) throws IOException {
        InputStream content = con.getEntity().getContent();
        String next = "";
        try (Scanner s = new Scanner(content)) {
            Scanner scanner = s.useDelimiter("\\Z");
            if (scanner.hasNext()) {
                next = scanner.next();
            }
        }
        String responseJsonString = null;
        try {
            if (new ObjectMapper().readTree(next).isArray()) {
                responseJsonString = new JSONArray(next).toString(4);
            } else {
                responseJsonString = new JSONObject(next).toString(4);
            }
        } catch (JSONException e) {
            //TODO: Just to check
//            responseJsonString = "";
            responseJsonString = next;
        } catch (JsonParseException e){
            e.printStackTrace();
            CustomLogging.writeToReport("Response: \n" + next);
            throw e;
        }

        CustomLogging.writeToReport("Response: \n" + responseJsonString);

        return next;

    }

    public static int getResponseCode(HttpResponse con) {
        return con.getStatusLine().getStatusCode();
    }

    public static <T> T convertJsonToJavaBean(String response, Class<T> T) throws IOException {

        return new ObjectMapper().readValue(response, T);
    }

    public static HttpResponse sendingPostRequest(String url, String postDataFileData, Map<String, String> headers, boolean isOmiseAPI) throws IOException {

        String requestJsonString = "";
        try {
            requestJsonString = new JSONObject(postDataFileData).toString(4);
        } catch (JSONException e) {
            requestJsonString = postDataFileData;
        }

        CustomLogging.writeToReport("Request: " + url + "\n\nRequest Body: " + requestJsonString);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        headers.put("User-Agent", USER_AGENT);
//        headers.put("device-id", "3C056156-A760-424D-BE15-0C3A3315D4EC");

//        if (isOmiseAPI)
//            headers.put("Authorization", "Basic cGtleV90ZXN0XzU5bHQ2a2k5azR3aWtydzNxMzI=");
//        else
//            headers.put("Authorization", TestData.getConfigData().getAppEnvironment().getAuthorization());
        post.addHeader("Content-Type", "application/json");

        headers.forEach((k, v) -> post.addHeader(k, v));
        CustomLogging.writeToReport("Request Headers: " + headers);

        post.setEntity(new StringEntity(postDataFileData, ContentType.APPLICATION_JSON));

        HttpResponse response = client.execute(post);

        return response;

    }

    public static HttpResponse sendingPostRequest(String url, String postDataFileData, Map<String, String> headers) throws IOException {

        return sendingPostRequest(url, postDataFileData, headers, false);

    }
}