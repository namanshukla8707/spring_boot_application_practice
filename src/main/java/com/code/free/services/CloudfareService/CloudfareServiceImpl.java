package com.code.free.services.CloudfareService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.code.free.utilities.CloudfareUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudfareServiceImpl implements CloudfareService {

    private final CloudfareUtils cloudfareUtils;

    @Value("${cloudflare.r2.bucket.name}")
    private String bucketName;

    public String upload(MultipartFile file, Long courseId, Integer lectureNo) throws IOException {
        String key = "course-" + courseId + "/" + "lesson_" + lectureNo;
        cloudfareUtils.getUploadFile(bucketName, file, key);
        return key;
    }
}
