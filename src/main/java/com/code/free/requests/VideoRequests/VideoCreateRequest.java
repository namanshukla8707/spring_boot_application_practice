package com.code.free.requests.VideoRequests;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoCreateRequest {

    private String title;
    private String description;
    private Integer position;
    private MultipartFile file;
}