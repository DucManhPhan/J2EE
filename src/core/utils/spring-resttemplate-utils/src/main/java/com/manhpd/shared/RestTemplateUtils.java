package com.manhpd.shared;

import com.manhpd.configuration.StorageProperties;
import com.manhpd.domain.value_object.Person;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
public class RestTemplateUtils {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StorageProperties storageProperties;

    public String getRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
//        headers.remove(Constant.CONTENT_TYPE_FIELD);
        headers.remove(Constant.AUTHORIZATION_FIELD);
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(Constant.URL, // + path,
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
    public  String postRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
        HttpEntity<String> httpEntity = new HttpEntity<String>(requestContent.getBodyData(), headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(Constant.URL + path,
                                                                    HttpMethod.POST,
                                                                    httpEntity,
                                                                    String.class);
            System.out.println("Response: " + response);
            return response.getBody();
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }

        return "";
    }

    // 2nd way - use postForEntity() method that returns ResponseEntity - contains HttpStatus, real object that push, ...
    public  void postRequestWithEntity(String path, RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
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
    public  void postRequestWithObject(String path, RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
        HttpEntity<Person> requestBody = new HttpEntity<>(new Person(), headers);

        Person person = restTemplate.postForObject(Constant.URL + path,
                                                    requestBody, Person.class);
        if (Objects.nonNull(person) && person.getId() != null) {
            System.out.println("Person created: "+ person.getId());
        } else {
            System.out.println("Do not get person object");
        }
    }

    public  String putRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
        HttpEntity<String> httpEntity = new HttpEntity<String>(requestContent.getBodyData(), headers);

        try {
            // 1st way - use exchange() method for put request
            ResponseEntity<String> response = restTemplate.exchange(Constant.URL + path,
                                                                    HttpMethod.PUT,
                                                                    httpEntity,
                                                                    String.class);
            System.out.println("Response: " + response);

            // 2nd way - use put() method
//            restTemplate.put(Constant.URL + path, httpEntity);

            return response.getBody();
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }

        return "";
    }

    public String deleteRequest(String path, RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
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

            return response.getBody();
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }

        return "";
    }

    public  void patchRequest(String path, RequestContent requestContent) throws MalformedURLException {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
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
    public void downloadRequest(String path, RequestContent requestContent) {
        try {
            HttpHeaders headers = this.createHttpHeaders(requestContent);
            headers.remove(Constant.CONTENT_TYPE_FIELD);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(Constant.URL + path,
                                                                    HttpMethod.GET,
                                                                    entity, byte[].class);
            Files.write(Paths.get(this.storageProperties.getDownloadDir() + requestContent.getDownloadedFileName()),
                                  response.getBody());
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
    public void downloadRequestWithLargeFile(String path, RequestContent requestContent) {
        // Optional Accept header
        RequestCallback requestCallback = request -> request.getHeaders()
                .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));

        // Streams the response instead of loading it all in memory
        ResponseExtractor<Void> responseExtractor = response -> {
            Path saveDir = Paths.get(this.storageProperties.getDownloadDir());
            Files.copy(response.getBody(), saveDir);
            return null;
        };

        restTemplate.execute(Constant.URL + path, HttpMethod.GET, requestCallback, responseExtractor);
    }

    public void uploadRequest(String path, RequestContent requestContent) throws IOException {
        HttpHeaders headers = this.createHttpHeadersForUploadFile(requestContent);
        HttpEntity requestEntity = this.createRequestForUploadFile(headers, requestContent.getResource());
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(Constant.URL + path,
                                                                        requestEntity,
                                                                        String.class);
            System.out.println("Response: " + response);
        } catch (HttpStatusCodeException exception) {
            System.out.println("Response: " + exception.getStatusCode().value());
        }
    }

    // ========================== Utility methods =============================
    private  void setParametersToRequestHeader(HttpHeaders headers, Map<String, String> paramHeaders) {
        if (Objects.isNull(paramHeaders)) {
            System.out.println("Do not exist parameters in Http Header.");
            return;
        }

        for (Map.Entry<String, String> me : paramHeaders.entrySet()) {
            headers.set(me.getKey().toString(), me.getValue().toString());
        }
    }

    private  HttpHeaders createHttpHeaders(RequestContent requestContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN);
        this.setParametersToRequestHeader(headers, requestContent.getHeaderParams());

        return headers;
    }

    private HttpHeaders createHttpHeadersForUploadFile(RequestContent requestContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set(Constant.AUTHORIZATION_FIELD, Constant.ACCESS_TOKEN);
        this.setParametersToRequestHeader(headers, requestContent.getHeaderParams());

        return headers;
    }

    private HttpEntity createRequestForUploadFile(HttpHeaders headers, Resource resource) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add(Constant.FILE_PART_FIELD, resource);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return requestEntity;
    }

}
