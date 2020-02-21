package com.manhpd;

import com.manhpd.dto.RequestContent;
import com.manhpd.dto.RequestType;
import com.manhpd.dto.UserPrincipal;
import com.manhpd.utils.Constant;
import com.manhpd.utils.GsonUtils;
import com.manhpd.utils.HttpUrlConnectionUtils;


import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
//        loginRequest();
        doGetRequest();
    }

    private static void loginRequest() throws IOException {
        UserPrincipal user = new UserPrincipal("admin", "admin");
        String path = "/users/auth";
        String bodyData = GsonUtils.toJsonString(user);

        RequestContent requestContent = RequestContent.builder()
                                                      .requestType(RequestType.POST)
                                                      .bodyData(bodyData)
                                                      .build();
        HttpUrlConnectionUtils.postRequest(path, requestContent);
    }

    private static void doGetRequest() throws IOException {
        String path = "/nslcm/v1/ns_instances";
        String bodyData = "{\n" +
                "  \"nsdId\": \"00112\",\n" +
                "  \"nsName\": \"admin\",\n" +
                "  \"nsDescription\": \"xxx\"\n" +
                "}";

        RequestContent requestContent = RequestContent.builder()
                .requestType(RequestType.GET)
                .bodyData(bodyData)
                .accessToken(Constant.ACCESS_TOKEN)
                .tokenType("Bearer")
                .build();
        HttpUrlConnectionUtils.getRequest(path, requestContent);
    }

}
