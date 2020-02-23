package com.manhpd.utils;

import com.manhpd.dto.UploadFileInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UploadFileUtils {

    private static final String LINE_FEED = "\r\n";

    private String boundary;

    private HttpURLConnection conn;

    private String charset;

    private OutputStream outputStream;

    private PrintWriter writer;

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     * @param requestURL
     * @param charset
     * @throws IOException
     */
    public UploadFileUtils(String requestURL, String charset)
            throws IOException {
        this.charset = charset;
        this.conn = HttpUrlConnectionUtils.createHttpUrlConnectionForUploadFile(new UploadFileInfo(this.boundary,
                                                                                                    this.conn, this.charset,
                                                                                                    this.outputStream, this.writer)
                                                                                , requestURL);
    }

    /**
     * Adds a form field to the request
     * @param name field name
     * @param value field value
     */
    public void addFormField(String name, String value) {
        this.writer.append("--" + this.boundary).append(UploadFileUtils.LINE_FEED);
        this.writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(UploadFileUtils.LINE_FEED);
        this.writer.append("Content-Type: text/plain; charset=" + this.charset).append(
                UploadFileUtils.LINE_FEED);
        this.writer.append(UploadFileUtils.LINE_FEED);
        this.writer.append(value).append(UploadFileUtils.LINE_FEED);
        this.writer.flush();
    }

    /**
     * Adds a upload file section to the request
     * @param fieldName name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    public void addFilePart(String fieldName, File uploadFile)
            throws IOException {
        String fileName = uploadFile.getName();
        this.writer.append("--" + this.boundary).append(UploadFileUtils.LINE_FEED);
        this.writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(UploadFileUtils.LINE_FEED);
        this.writer.append(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName))
                .append(UploadFileUtils.LINE_FEED);
        this.writer.append("Content-Transfer-Encoding: binary").append(UploadFileUtils.LINE_FEED);
        this.writer.append(UploadFileUtils.LINE_FEED);
        this.writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            this.outputStream.write(buffer, 0, bytesRead);
        }
        this.outputStream.flush();
        inputStream.close();

        this.writer.append(UploadFileUtils.LINE_FEED);
        this.writer.flush();
    }

    /**
     * Adds a header field to the request.
     * @param name - name of the header field
     * @param value - value of the header field
     */
    public void addHeaderField(String name, String value) {
        this.writer.append(name + ": " + value).append(UploadFileUtils.LINE_FEED);
        this.writer.flush();
    }

    /**
     * Completes the request and receives response from the server.
     * @return a list of Strings as response in case the server returned
     * status OK, otherwise an exception is thrown.
     * @throws IOException
     */
    public List<String> finish() throws IOException {
        List<String> response = new ArrayList<String>();

        this.writer.append(UploadFileUtils.LINE_FEED).flush();
        this.writer.append("--" + this.boundary + "--").append(UploadFileUtils.LINE_FEED);
        this.writer.close();

        // checks server's status code first
        int status = this.conn.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    this.conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            this.conn.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }

        return response;
    }

}
