package com.manhpd.controller;


import com.google.gson.Gson;
import com.manhpd.domain.service.IStorageService;
import com.manhpd.domain.value_object.Person;
import com.manhpd.shared.RequestContent;
import com.manhpd.shared.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class TestRestTemplateController {

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    @Autowired
    @Qualifier("downloadWithRestTemplate")
    private IStorageService storageServiceDownload;

    @Autowired
    @Qualifier("uploadWithRestTemplate")
    private IStorageService storageServiceUpload;

    @GetMapping(value = "test/persons")
    public void getAllPersons() {
        String path = "/persons";
        String jsonPersons = restTemplateUtils.getRequest(path, RequestContent.builder().build());

        System.out.println("Receive data when using RestTemplate: " + jsonPersons);
    }

    @PostMapping(value = "test/person")
    public ResponseEntity<?> create(@RequestBody Person person) {
        String path = "/person";
        String bodyData = new Gson().toJson(person);
        String json = restTemplateUtils.postRequest(path,
                                                    RequestContent.builder()
                                                                  .bodyData(bodyData)
                                                                  .build());

        return ResponseEntity.ok(json);
    }

    @PutMapping(value = "/test/persons/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Person person) {
        String path = "/persons/" + id;
        String bodyData = new Gson().toJson(person);
        String json = restTemplateUtils.putRequest(path,
                                                    RequestContent.builder()
                                                                  .bodyData(bodyData)
                                                                  .build());

        return ResponseEntity.ok(json);
    }

    @DeleteMapping(value = "/test/persons/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String path = "/persons/" + id;
        String json = this.restTemplateUtils.deleteRequest(path, RequestContent.builder().build());

        return ResponseEntity.ok(json);
    }

    @GetMapping(value = "/test/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        String path = "/download/" + filename;
        RequestContent requestContent = RequestContent.builder()
                                                      .downloadedFileName(filename)
                                                      .build();
        this.restTemplateUtils.downloadRequest(path, requestContent);
        Resource file = this.storageServiceDownload.loadAsResource(filename);       // load file in download directory
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(value = "/test/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("filePart") MultipartFile file) throws IOException {
        String path = "/upload";
        this.storageServiceUpload.store(file);
        Resource resource = this.storageServiceUpload.loadAsResource(file.getOriginalFilename());
        RequestContent requestContent = RequestContent.builder()
                .resource(resource)
                .build();
        this.restTemplateUtils.uploadRequest(path, requestContent);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/test/multi-upload")
    public ResponseEntity<String> uploadMultiFile(@RequestParam("fileParts") MultipartFile[] files) {
        Arrays.asList(files)
            .stream()
            .forEach(file -> {
                try {
                    uploadFile(file).getBody();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        return ResponseEntity.notFound().build();
    }

}
