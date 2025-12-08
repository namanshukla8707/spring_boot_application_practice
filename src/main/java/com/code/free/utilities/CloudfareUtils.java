package com.code.free.utilities;

import java.io.IOException;
import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.code.free.configuration.CloudfareR2Config;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

@Component
@RequiredArgsConstructor
public class CloudfareUtils {

    private final CloudfareR2Config cloudfareClient;

    private void uploadFile(String bucketName, MultipartFile file, String key) throws IOException {
        cloudfareClient.r2Client().putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build(), RequestBody.fromBytes(file.getBytes()));
    }

    private String presignedUrl(String bucketName, String key){
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(60))
            .getObjectRequest(getObjectRequest)
            .build();

        PresignedGetObjectRequest presignedRequest = cloudfareClient.r2Presigner().presignGetObject(presignRequest);
        return presignedRequest.url().toString();
    }

    public void getUploadFile(String bucketName, MultipartFile file, String key) throws IOException {
        uploadFile(bucketName, file, key);
        return;
    }

    public String getPresignedUrl(String bucketName, String key){
        return presignedUrl(bucketName, key);
    }

}
