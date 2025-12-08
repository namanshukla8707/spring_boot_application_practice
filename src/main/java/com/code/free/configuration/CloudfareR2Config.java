package com.code.free.configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class CloudfareR2Config {

    @Value("${cloudflare.r2.access.key}")
    private String accessKey;

    @Value("${cloudflare.r2.secret.key}")
    private String secretKey;

    @Value("${cloudflare.r2.endpoint}")
    private String endpoint;

    AwsCredentialsProvider credentialsProvider = () -> AwsBasicCredentials.create(accessKey, secretKey);

    @Bean
    public S3Client r2Client() {

        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.of("auto"))
                .endpointOverride(URI.create(endpoint))
                .serviceConfiguration(
                        S3Configuration.builder()
                                .pathStyleAccessEnabled(false)
                                .chunkedEncodingEnabled(true)
                                .build())
                .build();
    }

    @Bean
    public S3Presigner r2Presigner() {
        return S3Presigner.builder()
                .credentialsProvider(
                        credentialsProvider)
                .region(Region.of("auto"))
                .endpointOverride(URI.create(endpoint))
                .serviceConfiguration(
                        S3Configuration.builder()
                                .pathStyleAccessEnabled(true)
                                .checksumValidationEnabled(false)
                                .chunkedEncodingEnabled(true)
                                .build())
                .build();
    }
}
