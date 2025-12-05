package com.code.free.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.services.CourseService.CourseService;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public ApiResult<String> createCourse(@RequestBody CourseRequestDto request) throws IOException {
        return courseService.createCourse(request);

    }

    @PostMapping("/upload")
    public ApiResult<String> uploadCourseVideo(@RequestParam MultipartFile file) throws IOException{
        return courseService.uploadCourseVideo(file);
    }
    
}
