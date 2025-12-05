package com.code.free.services.CourseService;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.utilities.ApiResult;

public interface CourseService {
    ApiResult<String> createCourse(CourseRequestDto request) throws IOException;
    ApiResult<String> uploadCourseVideo(MultipartFile file) throws IOException;
}
