package com.code.free.services.CourseService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.code.free.entities.cousre.CourseEntity;
import com.code.free.repositories.Course.CourseRepo;
import com.code.free.requests.CourseRequests.CourseRequestDto;
import com.code.free.responses.CustomResponse;
import com.code.free.utilities.ApiResult;
import com.code.free.utilities.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepository;
    private final Utils utils;

    public ApiResult<String> createCourse(CourseRequestDto request) {
        Long id = utils.getCurrentUser().getId();
        CourseEntity course = CourseEntity.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .createdBy(id)
                .discount(request.getDiscount())
                .build();

        courseRepository.save(course);
        return CustomResponse.success(course.getTitle(), "Course created successfully", HttpStatus.CREATED);
    }
}
