package com.manhpd.utils.http_request;

import com.manhpd.dto.RequestContent;
import com.manhpd.utils.HttpUrlConnectionUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

public abstract class DoRequest {

    protected HttpURLConnection setHttpRequest(String path, RequestContent requestContent) throws IOException {
        HttpURLConnection conn = HttpUrlConnectionUtils.createHttpUrlConnection(path, requestContent);
        return conn;
    }

    protected HttpURLConnection setHttpRequestWithBodyData(String path, RequestContent requestContent) throws IOException {
        HttpURLConnection conn = HttpUrlConnectionUtils.createHttpUrlConnection(path, requestContent);
        conn.setDoOutput(true);

        HttpUrlConnectionUtils.sendBodyRequest(conn, requestContent);
        return conn;
    }

    protected void receiveResponse(HttpURLConnection conn) throws IOException {
        int responseCode = conn.getResponseCode();
        System.out.println("Response code is: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            HttpUrlConnectionUtils.toResponseResult(conn);
        } else {
            HttpUrlConnectionUtils.toErrorResult(conn);
            System.out.println("Receive error " + responseCode + " when sending request");
        }
    }

    public abstract void sendRequest(String path, RequestContent requestContent) throws IOException;

}
