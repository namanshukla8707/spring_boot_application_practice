package com.code.free.utilities;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.code.free.configuration.CloudfareR2Config;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@RequiredArgsConstructor
public class CloudfareUtils {

    private final CloudfareR2Config cloudfareClient;

    // Hidden method to upload file
    private void uploadFile(String bucketName, MultipartFile file, String key) throws IOException{
        cloudfareClient.r2Client().putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build(), RequestBody.fromBytes(file.getBytes()));
    }

    public void getUploadFile(String bucketName, MultipartFile file, String key) throws IOException{
        uploadFile(bucketName, file, key);
        return;
    }

}
