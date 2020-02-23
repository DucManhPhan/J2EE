package com.manhpd.utils;

public class Constant {

    public static final String ERROR_HEADER_PARAMS = "Parameters are invalid - HttpURLConnection or values of header";

    public static final String URL_VOCS = "http://dev.lifesup.com.vn:8088/com.viettel.vocs-service";
    public static final String URL_VMANO = "http://dev.lifesup.com.vn:9999";

    public static final String OPEN_STACK_USERID_FIELD = "openStackUserId";
    public static final String PROJECT_ID_FIELD = "projectId";
    public static final String AUTHORIZATION_FIELD = "Authorization";
    public static final String CONTENT_TYPE_FIELD = "Content-Type";
    public static final String ACCEPT_ENCODING_FIELD = "Accept-Encoding";
    public static final String USER_AGENT_FIELD = "User-Agent";

    // Types of Content-Type
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String FORM_URLENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String XML_CONTENT_TYPE = "application/xml";
    public static final String ZIP_CONTENT_TYPE = "application/zip";
    public static final String PDF_CONTENT_TYPE = "application/pdf";
    public static final String SQL_CONTENT_TYPE = "application/sql";
    public static final String GRAPHQL_CONTENT_TYPE = "application/graphql";
    public static final String MSWORD_DOC_CONTENT_TYPE = "application/msword";      // (.doc)
    public static final String MSWORD_DOCX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";  // (.docx)
    public static final String MSEXCEL_XLS_CONTENT_TYPE = "application/vnd.ms-excel"; // (.xls)
    public static final String MSEXCEL_XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";      // (.xlsx)
    public static final String MS_POWERPOINT_PPT_CONTENT_TYPE = "application/vnd.ms-powerpoint";       // (.ppt)
    public static final String MS_POWERPOINT_PPTX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.presentationml.presentation";                         // (.pptx)
    public static final String FORM_DATA_CONTENT_TYPE = "multipart/form-data";
    public static final String TEXT_CSS_CONTENT_TYPE = "text/css";
    public static final String TEXT_XML_CONTENT_TYPE = "text/xml;charset=UTF-8";
    public static final String TEXT_HTML_CONTENT_TYPE = "text/html";
    public static final String TEXT_CSV_CONTENT_TYPE = "text/csv";
    public static final String TEXT_PLAIN_CONTENT_TYPE = "text/plain";
    public static final String IMAGE_PNG_CONTENT_TYPE = "image/png";
    public static final String IMAGE_JPEG_CONTENT_TYPE = "image/jpeg";
    public static final String IMAGE_GIF_CONTENT_TYPE = "image/gif";

    // Types of Accept-Encoding
    // Single Compression
    public static final String GZIP_ACCEPT_ENCODING_TYPE = "gzip";
    public static final String COMPRESS_ACCEPT_ENCODING_TYPE = "compress";

    // Multiple Compression
    public static final String GZIP_COMPRESS_ACCEPT_ENCODING_TYPE = "gzip, compress";


    public static final String ACCESS_TOKEN ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOlt7InJvbGVUeXBlIjoiUFJPVklERVJfQURNSU4iLCJhdXRob3JpdHkiOiJQUk9WSURFUl9BRE1JTiJ9XSwiZW5hYmxlIjpmYWxzZSwiZXhwIjoxNTgyNDk1ODk2LCJpYXQiOjE1ODI0NjcwOTZ9.q_YexcBh4mje4jJR3KrqtSzqZtidopdJwq7APJ3VqnKTVXRnx6aPnm5sZ4k1opMemJgSUvyoJU-tLsLwD7y2nQ";
    public static final String OPEN_STACK_USERID = "83b3deb1-9364-43cb-940e-36245895fceb";
    public static final String PROJECT_ID = "001";
    public static final String DEFAULT_TOKEN_TYPE = "Bearer";
    public static final int BUFFER_SIZE = 4096;
}
