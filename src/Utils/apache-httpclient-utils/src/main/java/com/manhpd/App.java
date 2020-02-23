package com.manhpd;

import com.manhpd.utils.HttpClientUtils;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        loginRequest();
    }

    private static void loginRequest() throws IOException {
        String path = "/users/auth";
        HttpClientUtils.postRequest(path);
    }
}
