package com.DreamCompany.IDreamedThat.awsS3Config.awsS3Services;

import com.amazonaws.services.clouddirectory.model.ObjectAlreadyDetachedException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class S3Service {
    private final AmazonS3 s3Client;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public byte[] getObject(String key) {
        try {
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, key));
            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
            return IOUtils.toByteArray(objectInputStream);
        } catch (AmazonS3Exception | IOException e) {
            return null;
        }
    }

    public void createObject(String key, MultipartFile file) throws IOException {
        if (!s3Client.doesObjectExist(bucketName, key)) {
            s3Client.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), null));
        } else {
            throw new ObjectAlreadyDetachedException("Object '" + key + "' already exists in S3");
        }
    }

    public void updateObject(String fileName, MultipartFile file) throws IOException {
        boolean doesObjectExist = s3Client.doesObjectExist(bucketName, fileName);
        if (doesObjectExist) {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), null));
        } else {
            throw new FileNotFoundException("Object '" + fileName + "' does not exist in S3");
        }
    }

    public void deleteObject(String fileName) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }
}
