package com.manhpd;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class HttpUrlConnectionUtils {

    public static void getRequest(String path, RequestContent requestContent) throws IOException {
        RequestType requestType = RequestType.GET;
        URL url = new URL(Constant.URL_VOCS + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(requestType.getRequestMethod());
        conn.setRequestProperty(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE);

        setHeaderParameters(conn, requestContent.getKeyValueHeaders());
        setAuthentication(conn, requestContent.getAccessToken());

        if (StringUtils.isNotEmpty(requestContent.getBodyData())) {
            OutputStream os = conn.getOutputStream();
            os.write(requestContent.getBodyData().getBytes());
            os.flush();
            os.close();
        }

        int responseCode = conn.getResponseCode();
        System.out.println("Response code is: " + responseCode);
    }


    private static void setHeaderParameters(HttpURLConnection conn, Map<String, String> keyValueHeaders) {
        if (Objects.isNull(conn) || Objects.isNull(keyValueHeaders)) {
            throw new IllegalArgumentException();
        }

        for (Map.Entry<String, String> me : keyValueHeaders.entrySet()) {
            conn.setRequestProperty(me.getKey().toString(), me.getValue().toString());
        }
    }

    private static void setAuthentication(HttpURLConnection conn, String token) {
        if (StringUtils.isNotEmpty(token)) {
            conn.setRequestProperty(Constant.AUTHORIZATION_FIELD, token);
        }
    }

}
