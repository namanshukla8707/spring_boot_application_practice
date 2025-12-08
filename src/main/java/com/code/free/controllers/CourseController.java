package com.code.free.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.requests.CourseRequests.CourseVideosAddRequestDto;
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
    public ApiResult<String> addLessonsToCourse(@ModelAttribute CourseVideosAddRequestDto request) throws IOException {
        return courseService.addVideosToCourse(request.getVideoRequests(),request.getCourseId());
    }

    @GetMapping("/view")
    public ApiResult<ViewCourseResponseDto> getMethodName(@RequestParam Long courseId) {
        return courseService.viewCourse(courseId);
    }

    @PostMapping("/purchase")
    public ApiResult<String> purchaseCourse(@RequestParam Long courseId){
        return courseService.purchaseCourse(courseId);
    }
}
