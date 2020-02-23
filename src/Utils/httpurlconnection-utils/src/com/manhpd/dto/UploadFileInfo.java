package com.manhpd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;


@Data
@AllArgsConstructor
public class UploadFileInfo {

    private String boundary;

    private HttpURLConnection conn;

    private String charset;

    private OutputStream outputStream;

    private PrintWriter writer;

}
