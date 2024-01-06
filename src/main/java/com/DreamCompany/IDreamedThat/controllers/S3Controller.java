package com.DreamCompany.IDreamedThat.controllers;

import com.DreamCompany.IDreamedThat.awsS3Config.awsS3Services.S3Service;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/s3")
public class S3Controller {
    private final S3Service s3Service;
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/{key}")
    public ResponseEntity<byte[]> getObject(@PathVariable("key") String key) {
        try {
            S3Object s3Object = s3Service.getObject(key);
            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
            byte[] objectBytes = IOUtils.toByteArray(objectInputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentLength(objectBytes.length);
            headers.setContentDispositionFormData("attachment", key);
            return new ResponseEntity<>(objectBytes, headers, HttpStatus.OK);

        } catch (AmazonS3Exception | IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{key}")
    public ResponseEntity<String> createObject(@PathVariable("key") String key, @RequestBody MultipartFile file) {
        try {
            s3Service.createObject(key, file);
            return new ResponseEntity<>("Object created successfully", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error creating object: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{key}")
    public ResponseEntity<String> updateObject(@PathVariable("key") String key, @RequestBody MultipartFile file) {
        try {
            s3Service.updateObject(key, file);
            return new ResponseEntity<>("Object updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error updating object: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> deleteObject(@PathVariable("key") String key) {
        s3Service.deleteObject(key);
        return new ResponseEntity<>("Object deleted successfully", HttpStatus.OK);
    }
}
