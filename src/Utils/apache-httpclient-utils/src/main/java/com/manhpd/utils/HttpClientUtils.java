package com.manhpd.utils;

import com.google.common.collect.Lists;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.List;

public class HttpClientUtils {

    public static void getRequest(String path) throws IOException {
        // Some ways to create HttpClient
//        HttpClient client = HttpClientBuilder.create().build();       // (1)
        CloseableHttpClient client = HttpClients.createDefault();     // (2)

        try {
            HttpGet getRequest = new HttpGet(Constant.url + path);
            getRequest.addHeader(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE);
            getRequest.addHeader(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN);

            HttpClientUtils.toResponseDataWithResponseHandler(client, getRequest);
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
        HttpClientUtils.addJsonRequestBody(httpPost, json);

        // Response
        HttpClientUtils.toResponseDataWithResponseHandler(client, httpPost);
    }

    public static void putRequest(String path) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();     // (2)

        try {
            // Two ways to create http request
            // (1) - Use specific HttpUriRequest such as HttpGet, HttpPut, HttpPost, ...
            HttpPut getRequest = new HttpPut(Constant.url + path);
            getRequest.addHeader(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE);
            getRequest.addHeader(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN);

            // (2) - Use builder of HttpUriRequest
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(Constant.url + path)
                    .addHeader(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE)
                    .addHeader(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN)
                    .build();

            HttpClientUtils.toResponseDataWithResponseHandler(client, getRequest);
        } finally {
            client.getConnectionManager().shutdown();
        }
    }

    public static void deleteRequest(String path) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();     // (2)

        try {
            HttpDelete deleteRequest = new HttpDelete(Constant.url + path);
            System.out.println("Executing request " + deleteRequest.getRequestLine());
            deleteRequest.addHeader(Constant.CONTENT_TYPE_FIELD, Constant.JSON_CONTENT_TYPE);
            deleteRequest.addHeader(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN);

            HttpClientUtils.toResponseDataWithResponseHandler(client, deleteRequest);
        } finally {
            client.getConnectionManager().shutdown();
        }
    }

    public static void uploadRequest(String path, String fileName) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            File file = new File(fileName);
            String message = "Upload file with multipart";

            // build multipart upload request
            HttpEntity data = MultipartEntityBuilder.create()
                                                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                                                    .addBinaryBody("upfile", file, ContentType.DEFAULT_BINARY, file.getName())
                                                    .addTextBody("text", message, ContentType.DEFAULT_BINARY).build();

            // build http request and assign multipart upload data
            HttpUriRequest request = RequestBuilder.post(Constant.url + path)
                                                    .setEntity(data)
                                                    .build();

            System.out.println("Executing request " + request.getRequestLine());
            HttpClientUtils.toResponseDataWithResponseHandler(httpclient, request);
        } catch (IOException ex) {
            System.out.println("Exception happens when uploading file");
        }
    }

    public static void downloadRequest(String path) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(Constant.url + path);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            int responseCode = response.getStatusLine().getStatusCode();

            System.out.println("Request Url: " + request.getURI());
            System.out.println("Response Code: " + responseCode);

            InputStream is = entity.getContent();
            HttpClientUtils.saveFileToLocal(client, is);
            System.out.println("File Download Completed!!!");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ======================= Utility methods ===========================
    private static String toResponseDataWithReadingStream(CloseableHttpClient client, HttpUriRequest request)
                                            throws IOException {
        CloseableHttpResponse httpResponse = client.execute(request);

        System.out.println("POST Response Status:: "
                + httpResponse.getStatusLine().getStatusCode());

        // get data from HttpResponse
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine = "";
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        // close connection and stop reading
        reader.close();
        client.close();

        // print result
        System.out.println(response.toString());
        return response.toString();
    }

    private static String toResponseDataWithResponseHandler(CloseableHttpClient client, HttpUriRequest request)
            throws IOException {
        ResponseHandler< String > responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            System.out.println("Status for this request: " + status);
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        String responseBody = client.execute(request, responseHandler);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);

        return responseBody;
    }

    private static void addJsonRequestBody(HttpEntityEnclosingRequest request, String jsonBody)
            throws UnsupportedEncodingException {
        StringEntity stringEntity = new StringEntity(jsonBody);
        request.setEntity(stringEntity);
    }

    /**
     * In order to configure headers as a default header on the client, we will use this method.
     * Or we will use annotation @Bean to define singleton instance.
     */
    private static HttpClient configureHttpClientWithDefaultHeaders() {
        Header header = new BasicHeader(HttpHeaders.CONTENT_TYPE, Constant.JSON_CONTENT_TYPE);
        List<Header> headers = Lists.newArrayList(header);
        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();

        return client;
    }

    private static void saveFileToLocal(CloseableHttpClient client, InputStream streamDownloadedFile) throws IOException {
        String filePath = "C:\\demo\\file.zip";
        FileOutputStream fos = new FileOutputStream(new File(filePath));

        int inByte;
        while ((inByte = streamDownloadedFile.read()) != -1) {
            fos.write(inByte);
        }

        streamDownloadedFile.close();
        fos.close();

        client.close();
    }

}
