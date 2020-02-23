package com.manhpd.utils.http_request;

import com.manhpd.dto.RequestContent;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SendRequest extends DoRequest {

    @Override
    public void sendRequest(String path, RequestContent requestContent) throws IOException {
        HttpURLConnection conn = this.setHttpRequest(path, requestContent);
        this.receiveResponse(conn);
    }
}
