package com.manhpd.shared;

import com.manhpd.domain.value_object.Person;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
public class RestTemplateUtils {

    @Autowired
    private static RestTemplate restTemplate;

    public static String getRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(Constant.URL + path,
                                                                HttpMethod.GET,
                                                                entity,
                                                                String.class);
        String result = response.getBody();
        System.out.println("Response result from get request: " + result);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);

        return result;
    }

    // ===================== POST method ========================
    // 1st way - use exchange() method
    public static void postRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
        HttpEntity<String> httpEntity = new HttpEntity<String>(requestContent.getBodyData(), headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(Constant.URL + path,
                                                                    HttpMethod.POST,
                                                                    httpEntity,
                                                                    String.class);
            System.out.println("Response: " + response);
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }
    }

    // 2nd way - use postForEntity() method that returns ResponseEntity - contains HttpStatus, real object that push, ...
    public static void postRequestWithEntity(String path, RequestContent requestContent) {
        HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
        HttpEntity<Person> requestBody = new HttpEntity<>(new Person(), headers);

        ResponseEntity<Person> result
                = restTemplate.postForEntity(Constant.URL + path, requestBody, Person.class);
        System.out.println("Status code:" + result.getStatusCode());

        // With HttpStatus = 200.
        if (result.getStatusCode() == HttpStatus.OK) {
            Person person = result.getBody();
            System.out.println("Person created: "+ person.getId());
        }
    }

    // 3rd way - use postForObject() method that returns a specific object
    public static void postRequestWithObject(String path, RequestContent requestContent) {
        HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
        HttpEntity<Person> requestBody = new HttpEntity<>(new Person(), headers);

        Person person = restTemplate.postForObject(Constant.URL + path,
                                                    requestBody, Person.class);
        if (Objects.nonNull(person) && person.getId() != null) {
            System.out.println("Person created: "+ person.getId());
        } else {
            System.out.println("Do not get person object");
        }
    }

    public static void putRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
        HttpEntity<Person> httpEntity = new HttpEntity<>(new Person(), headers);

        try {
            // 1st way - use exchange() method for put request
            ResponseEntity<String> response = restTemplate.exchange(Constant.URL + path,
                                                                    HttpMethod.PUT,
                                                                    httpEntity,
                                                                    String.class);
            System.out.println("Response: " + response);

            // 2nd way - use put() method
//            restTemplate.put(Constant.URL + path, httpEntity);
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }
    }

    public static void deleteRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
        HttpEntity<Person> httpEntity = new HttpEntity<>(headers);

        try {
            // 1st way - use exchange() method for delete request
            ResponseEntity<String> response = restTemplate.exchange(Constant.URL + path,
                    HttpMethod.DELETE,
                    httpEntity,
                    String.class);
            System.out.println("Response: " + response);

            // 2nd way - use delete() method
//            restTemplate.delete(Constant.URL + path);
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }
    }

    public static void patchRequest(String path, RequestContent requestContent) throws MalformedURLException {
        HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(requestContent.getUpdatedPartOfObject(), headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(Constant.URL + path,
                                                                    HttpMethod.PATCH,
                                                                    httpEntity,
                                                                    String.class);
            System.out.println("Response: " + response);
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }
    }

    // 1st way - download normal file with exchange() method and HttpMethod.GET
    public static void downloadRequest(String path, RequestContent requestContent) {
        try {
            HttpHeaders headers = RestTemplateUtils.createHttpHeaders(requestContent);
            headers.remove(Constant.CONTENT_TYPE_FIELD);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(Constant.URL + path,
                                                                    HttpMethod.GET,
                                                                    entity, byte[].class);
            Files.write(Paths.get(Constant.DOWNLOAD_PATH + Constant.DOWNLOADED_FILE_NAME), response.getBody());
        }catch (Exception e){
            System.out.println("Error happens in download request: " + e);
        }
    }

    // 2nd way - download large file
    // The getForObject and getForEntity methods of RestTemplate load the entire response in memory.
    // This is not suitable for downloading large files since it can cause out of memory exceptions.
    //
    // Note that you cannot simply return the InputStream from the extractor,
    // because by the time the execute method returns, the underlying connection and stream are already closed.
    public static void downloadRequestWithLargeFile(String path, RequestContent requestContent) {
        // Optional Accept header
        RequestCallback requestCallback = request -> request.getHeaders()
                .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));

        // Streams the response instead of loading it all in memory
        ResponseExtractor<Void> responseExtractor = response -> {
            Path saveDir = Paths.get(Constant.DOWNLOAD_PATH);
            Files.copy(response.getBody(), saveDir);
            return null;
        };

        restTemplate.execute(Constant.URL + path, HttpMethod.GET, requestCallback, responseExtractor);
    }

    public static void uploadRequest(String path, RequestContent requestContent) throws IOException {
        // save file in local server
        String pathLocal = Constant.UPLOAD_PATH;
        File localFile = new File(path);
        if (!localFile.exists())
            localFile.mkdirs(); // or file.mkdir()

        localFile = new File(pathLocal + "/" + requestContent.getFile().filename());
        localFile.createNewFile();
        requestContent.getFile().transferTo(localFile);

        // work with headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN);
        RestTemplateUtils.setParametersToRequestHeader(headers, requestContent.getHeaderParams());

        // prepate for request body
        Resource resource = new FileSystemResource(localFile);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add(Constant.FILE_PART_FIELD, resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = Constant.URL + path;
        RestTemplate restTemplate = new RestTemplate();

        // send file
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
            System.out.println("Response: " + response);
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }
    }

    // ========================== Utility methods =============================
    private static void setParametersToRequestHeader(HttpHeaders headers, Map<String, String> paramHeaders) {
        for (Map.Entry<String, String> me : paramHeaders.entrySet()) {
            headers.set(me.getKey().toString(), me.getValue().toString());
        }
    }

    private static HttpHeaders createHttpHeaders(RequestContent requestContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN);
        RestTemplateUtils.setParametersToRequestHeader(headers, requestContent.getHeaderParams());

        return headers;
    }

}
