package com.manhpd;

import com.manhpd.utils.Constant;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpClientUtils {

    public static void getRequest() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(Constant.url));
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status of response code is: " + statusCode);
    }

    public static void postRequest() {

    }

}
