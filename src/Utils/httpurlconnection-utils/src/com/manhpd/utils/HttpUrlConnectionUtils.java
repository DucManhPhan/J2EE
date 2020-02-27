package com.manhpd.utils;

import com.manhpd.dto.DownloadFileInfo;
import com.manhpd.dto.RequestContent;
import com.manhpd.dto.UploadFileInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
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

    public static void sendBodyRequest(HttpURLConnection conn, RequestContent requestContent) throws IOException {
        if (Objects.isNull(conn)) {
            throw new IllegalArgumentException();
        }

        if (Objects.isNull(requestContent) || StringUtils.isEmpty(requestContent.getBodyData())) {
            System.out.println("Do not exist request content or body data of request");
            return;
        }

        OutputStream os = conn.getOutputStream();
        os.write(requestContent.getBodyData().getBytes());
        os.flush();
        os.close();
    }

    public static String toResponseResult(HttpURLConnection conn) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        return HttpUrlConnectionUtils.getResponseDataFromStream(in);
    }

    public static String toErrorResult(HttpURLConnection conn) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        return HttpUrlConnectionUtils.getResponseDataFromStream(in);
    }

    private static String getResponseDataFromStream(BufferedReader in) throws IOException {
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

    public static HttpURLConnection createHttpUrlConnection(String path, RequestContent requestContent) throws IOException {
        URL url = new URL(Constant.URL_VOCS + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(requestContent.getRequestType().getRequestMethod());
        conn.setRequestProperty(Constant.CONTENT_TYPE_FIELD, requestContent.getContentType());

        setHeaderParameters(conn, requestContent.getKeyValueHeaders());
        setAuthentication(conn, requestContent);

        // Setting the connect and read timeouts
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        return conn;
    }

    public static HttpURLConnection createHttpUrlConnectionForUploadFile(UploadFileInfo uploadFileInfo, String requestURL) throws IOException {
        // creates a unique boundary based on time stamp
        uploadFileInfo.setBoundary("===" + System.currentTimeMillis() + "===");

        URL url = new URL(requestURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty(Constant.CONTENT_TYPE_FIELD,
                Constant.FORM_DATA_CONTENT_TYPE + "; boundary=" + uploadFileInfo.getBoundary());
        conn.setRequestProperty(Constant.USER_AGENT_FIELD, "Upload file with HttpURLConnection");
        uploadFileInfo.setOutputStream(conn.getOutputStream());
        uploadFileInfo.setWriter(new PrintWriter(new OutputStreamWriter(uploadFileInfo.getOutputStream(),
                                                                         uploadFileInfo.getCharset()),
                                                                        true));

        return conn;
    }

    /**
     * Based on data from InputStream, we will save that data to local disk
     *
     * @param inputStream - Data that will be downloaded from other link
     * @param fileInfo - contains saveDir - the directory that will be saved, fileName - the file's name that will be filled
     * @throws IOException
     */
    public static void saveFileToLocal(InputStream inputStream, DownloadFileInfo fileInfo) throws IOException {
        String saveFilePath = fileInfo.getSaveDir() + File.separator + fileInfo.getFileName();

        // opens an output stream to save into file
        FileOutputStream outputStream = new FileOutputStream(saveFilePath);

        int bytesRead = -1;
        byte[] buffer = new byte[Constant.BUFFER_SIZE];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        System.out.println("File downloaded");
    }

}
