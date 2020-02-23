package com.manhpd.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClientUtils {

    public static void getRequest(String path) throws IOException {
        // Some ways to create HttpClient
//        HttpClient client = HttpClientBuilder.create().build();       // (1)
        CloseableHttpClient client = HttpClients.createDefault();       // (2)

        try {
            HttpGet getRequest = new HttpGet(Constant.url + path);
            getRequest.addHeader(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE);
            getRequest.addHeader(Constant.AUTHORIZATION_FIELD, Constant.JSON_CONTENT_TYPE);

            HttpResponse response = client.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status of response code is: " + statusCode);
        } finally {
            client.getConnectionManager().shutdown();
        }
    }

    public static void postRequest(String path) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        // Add parameters of header to Post request
        HttpPost httpPost = new HttpPost(Constant.url + path);
        httpPost.setHeader(Constant.ACCEPT_FIELD, Constant.JSON_CONTENT_TYPE);
        httpPost.setHeader(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE);

        // Add body data to Post request
        String json = "{\n" +
                "\t\"username\": \"admin\",\n" +
                "\t\"password\": \"admin\"\n" +
                "}";
        StringEntity stringEntity = new StringEntity(json);
        httpPost.setEntity(stringEntity);

        // Manage response
        // (1) - Create a custom response handler
        ResponseHandler< String > responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        String responseBody = client.execute(httpPost, responseHandler);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);

        // (2) - Use CloseableHttpResponse
//        CloseableHttpResponse httpResponse = client.execute(httpPost);
//
//        System.out.println("POST Response Status:: "
//                + httpResponse.getStatusLine().getStatusCode());
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(
//                httpResponse.getEntity().getContent()));
//
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = reader.readLine()) != null) {
//            response.append(inputLine);
//        }
//        reader.close();
//
//        // print result
//        System.out.println(response.toString());
//        client.close();
    }

}
