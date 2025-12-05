package com.code.free.services.CourseService;

import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.utilities.ApiResult;

public interface CourseService {
    ApiResult<String> createCourse(CourseRequestDto request) throws IOException;

    ApiResult<String> uploadCourseVideo(MultipartFile file) throws IOException;
}
