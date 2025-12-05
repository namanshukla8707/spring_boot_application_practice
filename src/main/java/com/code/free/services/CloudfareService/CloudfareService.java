package com.code.free.services.CloudfareService;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CloudfareService {

    public String upload(MultipartFile file,Long courseId,Long lectureNo) throws IOException;
    
}
