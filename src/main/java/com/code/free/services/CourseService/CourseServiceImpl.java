package com.code.free.services.CourseService;

import java.io.IOException;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.code.free.entities.course.CourseEntity;
import com.code.free.repositories.Course.CourseRepo;
import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.responses.CustomResponse;
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
        videoService.uploadVideoFile(request.getVideos(),courseId);

        return CustomResponse.success(course.getTitle(), "Course created successfully", HttpStatus.CREATED);
    }

}
