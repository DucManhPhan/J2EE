package com.manhpd.utils;

import com.manhpd.dto.RequestContent;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;


/**
 * Some steps for sending Java Http request.
 * 1. Create URL object from the GET/POST URL String.
 * 2. Call openConnection() method on URL object that returns instance of HttpURLConnection
 * 3. Set the request method in HttpURLConnection instance, default value is GET.
 * 4. Call setRequestProperty() method on HttpURLConnection instance to set request header values,
 *    such as “User-Agent” and “Accept-Language” etc.
 * 5. We can call getResponseCode() to get the response HTTP code.
 *    This way we know if the request was processed successfully or there was any HTTP error message thrown.
 * 6. For GET, we can simply use Reader and InputStream to read the response and process it accordingly.
 * 7. For POST, before we read response we need to get the OutputStream from HttpURLConnection instance
 *    and write POST parameters into it.
 *
 */
public class HttpUrlConnectionUtils {

    public static void getRequest(String path, RequestContent requestContent) throws IOException {
        HttpURLConnection conn = HttpUrlConnectionUtils.createHttpUrlConnection(path, requestContent);
        int responseCode = conn.getResponseCode();
        System.out.println("Response code is: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            HttpUrlConnectionUtils.toResponseResult(conn);
        } else {
            System.out.println("Do not call this request");
        }
    }

    public static void postRequest(String path, RequestContent requestContent) throws IOException {
        HttpURLConnection conn = HttpUrlConnectionUtils.createHttpUrlConnection(path, requestContent);
        conn.setDoOutput(true);

        if (StringUtils.isNotEmpty(requestContent.getBodyData())) {
            OutputStream os = conn.getOutputStream();
            os.write(requestContent.getBodyData().getBytes());
            os.flush();
            os.close();
        }

        int responseCode = conn.getResponseCode();
        System.out.println("Response code is: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            HttpUrlConnectionUtils.toResponseResult(conn);
        } else {
            System.out.println("Do not call this request");
        }
    }

    public static void putRequest() {

    }

    public static void deleteRequest(String path, RequestContent requestContent) {

    }

    private static String toResponseResult(HttpURLConnection conn) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine = "";
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        System.out.println("The content of response is: " + response);

        return response.toString();
    }


    private static void setHeaderParameters(HttpURLConnection conn, Map<String, String> keyValueHeaders) {
        if (Objects.isNull(conn)) {
            throw new IllegalArgumentException();
        }

        if (Objects.isNull(keyValueHeaders)) {
            System.out.println("There is not key value headers.");
            return;
        }

        for (Map.Entry<String, String> me : keyValueHeaders.entrySet()) {
            conn.setRequestProperty(me.getKey().toString(), me.getValue().toString());
        }
    }

    private static void setAuthentication(HttpURLConnection conn, RequestContent requestContent) {
        if (StringUtils.isNotEmpty(requestContent.getAccessToken())) {
            conn.setRequestProperty(Constant.AUTHORIZATION_FIELD, requestContent.getTokenType() + " " + requestContent.getAccessToken());
        }
    }

    private static HttpURLConnection createHttpUrlConnection(String path, RequestContent requestContent) throws IOException {
        URL url = new URL(Constant.URL_VOCS + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(requestContent.getRequestType().getRequestMethod());
        conn.setRequestProperty(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE);

        setHeaderParameters(conn, requestContent.getKeyValueHeaders());
        setAuthentication(conn, requestContent);

        return conn;
    }

}
