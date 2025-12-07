package com.code.free.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.requests.VideoRequests.VideoCreateRequest;
import com.code.free.responses.CourseResponses.ViewCourseResponseDto;
import com.code.free.services.CourseService.CourseService;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public ApiResult<String> createCourse(@ModelAttribute CourseRequestDto request) throws IOException {
        return courseService.createCourse(request);

    }

    @PostMapping("/add-lessons")
    public ApiResult<String> addLessonsToCourse(@ModelAttribute List<VideoCreateRequest> videoRequests,Long courseId) throws IOException {
        return courseService.addVideosToCourse(videoRequests,courseId);
    }

    @GetMapping("/view-course")
    public ApiResult<ViewCourseResponseDto> getMethodName(@RequestParam Long courseId) {
        return courseService.viewCourse(courseId);
    }
    
}
