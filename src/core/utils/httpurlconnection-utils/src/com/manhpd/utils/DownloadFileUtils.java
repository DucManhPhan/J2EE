package com.manhpd.utils;

import com.manhpd.dto.DownloadFileInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFileUtils {

    public static void  downloadFile(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int responseCode = conn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = conn.getHeaderField("Content-Disposition");
            fileName = DownloadFileUtils.getFileNameDownload(disposition, fileURL);

            // opens input stream from the HTTP connection
            InputStream inputStream = conn.getInputStream();
            DownloadFileInfo fileInfo = new DownloadFileInfo(saveDir, fileName);
            HttpUrlConnectionUtils.saveFileToLocal(inputStream, fileInfo);
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }

        conn.disconnect();
    }

    private static String getFileNameDownload(String disposition, String fileURL) {
        if (StringUtils.isNotEmpty(disposition)) {
            return StringUtil.getFileNameFromDispositionHeader(disposition);
        } else {
            return StringUtil.getFileNameFromUrl(fileURL);
        }
    }

}
