package com.manhpd.controller;


import com.manhpd.configuration.StorageProperties;
import com.manhpd.domain.service.IPersonService;
import com.manhpd.domain.service.IStorageService;
import com.manhpd.domain.value_object.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    @Autowired
    @Qualifier("uploadNormal")
    private IStorageService storageServiceUploadNormal;

    @Autowired
    @Qualifier("downloadNormal")
    private IStorageService storageServiceDownloadNormal;

    @Autowired
    private StorageProperties storageProperties;

    @GetMapping(value = "/persons")
    public ResponseEntity<Collection<Person>> getPersons() {
        return ResponseEntity.ok(personService.getAllPerson());
    }

    @PostMapping(value = "/person")
    public ResponseEntity<HttpStatus> create(@RequestBody Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @PutMapping(value = "/persons/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody Person person) {
        boolean hasState = personService.updatePerson(id, person);
        if (hasState) {
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        boolean hasState = personService.deletePerson(id);
        return hasState ? ResponseEntity.ok(HttpStatus.ACCEPTED) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource file = this.storageServiceDownloadNormal.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("filePart") MultipartFile file) {
        this.storageServiceUploadNormal.store(file);

        // return link for download or sharing
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/multi-upload")
    public ResponseEntity multiUpload(@RequestParam("fileParts") MultipartFile[] files) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(files)
                .stream()
                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
        return ResponseEntity.ok(fileDownloadUrls);
    }

}
