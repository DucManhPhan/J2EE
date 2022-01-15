package com.manhpd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final String AWS_ACCESS_KEY = "AWS_ACCESS_KEY_ID";
    public static final String AWS_SECRET_KEY = "AWS_SECRET_ACCESS_KEY";
    public static final String PRI_BUCKET_NAME = "test-20220115";
    public static final String TRANSIENT_BUCKET_NAME = "test1-20220115";
    public static final String F1 = "sample1.txt";
    public static final String F2 = "sample2.txt";
    public static final String F3 = "sample3.txt";
    public static final String DIR = "C:\\Users\\ducma\\s3-bucket";
    public static final String DOWN_DIR = "C:\\Users\\ducma\\s3-bucket-alt";

    private final S3Client s3;

    public Application(S3Client s3) {
        this.s3 = s3;
    }

    public void createBucket(String name) {
        try {
            CreateBucketRequest request = CreateBucketRequest.builder()
                    .bucket(name)
                    .build();
            s3.createBucket(request);
        } catch (Exception ex) {
            LOGGER.error("Error during create bucket: ", ex);
        }
    }

    public void uploadFile(String bucket, String localFile, String localDirectory, String key) {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            this.s3.putObject(request, Paths.get(localDirectory, localFile));
        } catch (Exception ex) {
            LOGGER.error("Error uploading file", ex);
        }
    }

    public void downloadFile(String bucket, String localFile, String localDirectory, String key) {
        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            this.s3.getObject(request, Paths.get(localDirectory, localFile));
        } catch (Exception ex) {
            LOGGER.error("Error downloading file", ex);
        }
    }

    public void deleteFile(String bucket, String key) {
        try {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            this.s3.deleteObject(request);
        } catch (Exception ex) {
            LOGGER.error("Error deleting file", ex);
        }
    }

    public List<String> listFiles(String bucket) {
        List<String> keys = new ArrayList<>();

        try {
            ListObjectsRequest request = ListObjectsRequest.builder()
                    .bucket(bucket)
                    .build();

            ListObjectsResponse response = this.s3.listObjects(request);
            response.contents().forEach(content -> {
                keys.add(content.key());
            });

            LOGGER.info("Number of files in bucket: " + keys.size());
        } catch (Exception ex) {
            LOGGER.error("Error listing file", ex);
        }

        return keys;
    }

    public void copyFile(String sourceBucket, String destinationBucket, String sourceKey, String destinationKey) {
        try {
            String encodeUrl = URLEncoder.encode(sourceBucket + "/" + sourceKey, StandardCharsets.UTF_8.toString());
            CopyObjectRequest request = CopyObjectRequest.builder()
                    .copySource(encodeUrl)
                    .destinationBucket(destinationBucket)
                    .destinationKey(destinationKey)
                    .build();
            this.s3.copyObject(request);
        } catch (Exception ex) {
            LOGGER.error("Error copying file", ex);
        }
    }

    public void blockPublicAccess(String bucket) {
        try {
            PutPublicAccessBlockRequest request = PutPublicAccessBlockRequest.builder()
                    .bucket(bucket)
                    .publicAccessBlockConfiguration(PublicAccessBlockConfiguration
                            .builder()
                            .blockPublicAcls(true)
                            .blockPublicPolicy(true)
                            .ignorePublicAcls(true)
                            .restrictPublicBuckets(true)
                            .build())
                    .build();
            this.s3.putPublicAccessBlock(request);
        } catch (Exception ex) {
            LOGGER.error("Error blocking public access", ex);
        }
    }

    public static void main(String[] args) {
        String accessKey = System.getenv(AWS_ACCESS_KEY);
        String secretKey = System.getenv(AWS_SECRET_KEY);
        AwsSessionCredentials creds = AwsSessionCredentials.create(accessKey, secretKey, "");

        S3Client s3 = S3Client.builder()
                              .credentialsProvider(StaticCredentialsProvider.create(creds))
                              .build();
        Application app = new Application(s3);

        // 1st operation
//        app.createBucket(TRANSIENT_BUCKET_NAME);

        // 2nd operation
        app.uploadFile(TRANSIENT_BUCKET_NAME, F1, DIR, F1);
        app.downloadFile(TRANSIENT_BUCKET_NAME, F1, DOWN_DIR, F1);
//        app.deleteFile(TRANSIENT_BUCKET_NAME, F1);

        // 3rd operation
//        app.copyFile(PRI_BUCKET_NAME, TRANSIENT_BUCKET_NAME, F3, F3);
//        app.listFiles(PRI_BUCKET_NAME);
    }
}
