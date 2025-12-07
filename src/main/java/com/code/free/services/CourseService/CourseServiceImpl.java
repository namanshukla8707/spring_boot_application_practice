package com.code.free.services.CourseService;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.code.free.entities.course.CourseEntity;
import com.code.free.entities.user.UserEntity;
import com.code.free.entities.userCourseMapping.UserCourseMappingEntity;
import com.code.free.exceptions.CourseExceptions.CourseNotFoundException;
import com.code.free.exceptions.CourseExceptions.CourseNotPurchased;
import com.code.free.repositories.course.CourseRepo;
import com.code.free.repositories.userCourseMapping.UserCourseMappingRepo;
import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.requests.VideoRequests.VideoCreateRequest;
import com.code.free.responses.CustomResponse;
import com.code.free.responses.CourseResponses.ViewCourseResponseDto;
import com.code.free.services.VideoService.VideoService;
import com.code.free.utilities.ApiResult;
import com.code.free.utilities.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepository;
    private final Utils utils;
    private final VideoService videoService;
    private final UserCourseMappingRepo userCourseMappingRepo;

    public ApiResult<String> createCourse(CourseRequestDto request) throws IOException {
        Long id = utils.getCurrentUser().getId();
        CourseEntity course = CourseEntity.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .createdBy(id)
                .discount(request.getDiscount())
                .build();

        courseRepository.save(course);

        Long courseId = course.getId();

        if (request.getVideos() == null || request.getVideos().isEmpty()) {
            return CustomResponse.success(course.getTitle(), "Course created successfully without videos",
                    HttpStatus.CREATED);
        }
        videoService.uploadVideoFile(request.getVideos(), courseId);
        return CustomResponse.success(course.getTitle(), "Course created successfully", HttpStatus.CREATED);
    }

    public ApiResult<String> addVideosToCourse(List<VideoCreateRequest> videoRequests, Long courseId)
            throws IOException {
        videoService.uploadVideoFile(videoRequests, courseId);
        return CustomResponse.success("Videos added successfully", "Videos added successfully", HttpStatus.CREATED);
    }

    public ApiResult<ViewCourseResponseDto> viewCourse(Long courseId) {
        CourseEntity course = courseRepository.findCourseById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        UserEntity currentUser = utils.getCurrentUser();

        UserCourseMappingEntity userCourseMappingResponse = userCourseMappingRepo
                .findByUserIdAndCourseId(currentUser.getId(), course.getId())
                .orElseThrow(() -> new CourseNotPurchased("Course not purchased"));

        ViewCourseResponseDto courseResponse;

        if (userCourseMappingResponse != null) {
            courseResponse = ViewCourseResponseDto.builder()
                    .courseTitle(course.getTitle())
                    .coursePrice(course.getPrice())
                    .courseDiscount(course.getDiscount())
                    .createdBy(course.getCreatedBy())
                    .videosList(videoService.videosGlimpseForCourse(courseId).getBody().getData())
                    .build();
        } else {
            courseResponse = ViewCourseResponseDto.builder()
                    .courseTitle(course.getTitle())
                    .coursePrice(course.getPrice())
                    .courseDiscount(course.getDiscount())
                    .createdBy(course.getCreatedBy())
                    .videosList(videoService.videosGlimpseForCourse(courseId).getBody().getData())
                    .build();
        }
        return CustomResponse.success(courseResponse, "Course details fetched successfully", HttpStatus.OK);
    }
}
