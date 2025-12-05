package com.code.free.services.CloudfareService;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.code.free.configuration.CloudfareR2Config;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class CloudfareServiceImpl implements CloudfareService {

    private final CloudfareR2Config cloudfareClient;

    @Value("${cloudflare.r2.bucket.name}")
    private String bucketName;

    public String upload(MultipartFile file) throws IOException {

        String key = "" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        cloudfareClient.r2Client().putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build(), RequestBody.fromBytes(file.getBytes()));

        System.out.println(key + " <-- key");
        return key;
        
    }
}
