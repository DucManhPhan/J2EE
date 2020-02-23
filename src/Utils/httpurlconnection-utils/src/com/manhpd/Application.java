package com.manhpd;

import com.manhpd.dto.*;
import com.manhpd.utils.Constant;
import com.manhpd.utils.DownloadFileUtils;
import com.manhpd.utils.GsonUtils;
import com.manhpd.utils.UploadFileUtils;
import com.manhpd.utils.http_request.DoRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Application {

    public static void main(String[] args) throws IOException {
//        loginRequest();
//        doGetRequest();

//        deleteProjectRequest();
//        deleteServerGroupRequest();
//        uploadFileRequest();

        downloadFileRequest();
    }

    private static void loginRequest() throws IOException {
        UserPrincipal user = new UserPrincipal("admin", "admin");
        String path = "/users/auth";
        String bodyData = GsonUtils.toJsonString(user);

        RequestContent requestContent = RequestContent.builder()
                                                      .requestType(RequestType.POST)
                                                      .bodyData(bodyData)
                                                      .build();
//        HttpUrlConnectionUtils.sendRequestWithBodyData(path, requestContent);
        Optional<DoRequest> optRequest = SendRequestFactory.createSendRequest(SendRequestType.SEND_REQUEST_WITH_BODY_DATA);
        if (optRequest.isEmpty()) {
            System.out.println("Do not create SendRequest object");
            return;
        }

        optRequest.get().sendRequest(path, requestContent);
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
                .build();
        Optional<DoRequest> optRequest = SendRequestFactory.createSendRequest(SendRequestType.NORMAL_SEND_REQUEST);
        if (optRequest.isEmpty()) {
            System.out.println("Do not create SendRequest object");
            return;
        }

        optRequest.get().sendRequest(path, requestContent);
    }

    private static void deleteProjectRequest() throws IOException {
        String path = "/api/v1/projects/003";
        String bodyData = "{\n" +
                "  \"projectName\": \"xxx\",\n" +
                "  \"projectDescription\": \"xxx\",\n" +
                "  \"isEnabled\": true\n" +
                "}\n";
        Map<String, String> paramHeaders = createParamHeaders(Constant.OPEN_STACK_USERID, Constant.PROJECT_ID);
        RequestContent requestContent = RequestContent.builder()
                .requestType(RequestType.DELETE)
                .bodyData(bodyData)
                .build();

        Optional<DoRequest> optRequest = SendRequestFactory.createSendRequest(SendRequestType.SEND_REQUEST_WITH_BODY_DATA);
        if (optRequest.isEmpty()) {
            System.out.println("Do not create SendRequest object");
            return;
        }

        optRequest.get().sendRequest(path, requestContent);
    }

    private static void deleteServerGroupRequest() throws IOException {
        String path = "/vim/v1/server_groups/80134c29-ac61-4c07-b24f-de6e44203660";
        Map<String, String> paramHeaders = createParamHeaders(Constant.OPEN_STACK_USERID, Constant.PROJECT_ID);
        RequestContent requestContent = RequestContent.builder()
                .requestType(RequestType.DELETE)
                .keyValueHeaders(paramHeaders)
                .build();

        Optional<DoRequest> optRequest = SendRequestFactory.createSendRequest(SendRequestType.NORMAL_SEND_REQUEST);
        if (optRequest.isEmpty()) {
            System.out.println("Do not create SendRequest object");
            return;
        }

        optRequest.get().sendRequest(path, requestContent);
    }

    private static void uploadFileRequest() {
        String charset = "UTF-8";
        File uploadFile1 = new File("e:/Test/PIC1.JPG");
        File uploadFile2 = new File("e:/Test/PIC2.JPG");
        String requestURL = "http://localhost:8080/FileUploadSpringMVC/uploadFile.do";

        try {
            UploadFileUtils multipart = new UploadFileUtils(requestURL, charset);

            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");

            multipart.addFormField("description", "Cool Pictures");
            multipart.addFormField("keywords", "Java,upload,Spring");

            multipart.addFilePart("fileUpload", uploadFile1);
            multipart.addFilePart("fileUpload", uploadFile2);

            List<String> response = multipart.finish();

            System.out.println("SERVER REPLIED:");

            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private static void downloadFileRequest() {
        String fileURL = "https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.19";
        String saveDir = "D:/Download";
        try {
            DownloadFileUtils.downloadFile(fileURL, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Map<String, String> createParamHeaders(String openStackUserId, String projectId) {
        Map<String, String> paramHeaders = new HashMap<>();
        paramHeaders.put(Constant.OPEN_STACK_USERID_FIELD, openStackUserId);
//        paramHeaders.put(Constant.PROJECT_ID_FIELD, projectId);

        return paramHeaders;
    }

}
