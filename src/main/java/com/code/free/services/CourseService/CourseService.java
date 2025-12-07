package com.code.free.services.CourseService;

import java.io.IOException;
import java.util.List;

import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.requests.VideoRequests.VideoCreateRequest;
import com.code.free.responses.CourseResponses.ViewCourseResponseDto;
import com.code.free.utilities.ApiResult;

public interface CourseService {
    ApiResult<String> createCourse(CourseRequestDto request) throws IOException;
    ApiResult<String> addVideosToCourse(List<VideoCreateRequest> videoRequests, Long courseId) throws IOException;
    ApiResult<ViewCourseResponseDto> viewCourse(Long courseId);
}
