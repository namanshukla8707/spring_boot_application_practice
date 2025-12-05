package com.code.free.services.CourseService;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.code.free.entities.course.CourseEntity;
import com.code.free.entities.video.VideoEntity;
import com.code.free.repositories.Course.CourseRepo;
import com.code.free.repositories.Course.VideoRepo;
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
    private final CloudfareService cloudfareService;
    private final VideoRepo videoRepository;

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
        request.getVideos().forEach(video -> {
            try {
                String key = cloudfareService.upload(video.getFile(), courseId, video.getPosition());
                VideoEntity videoEntity = VideoEntity.builder()
                        .title(video.getTitle())
                        .description(video.getDescription())
                        .position(video.getPosition())
                        .url(key)
                        .courseId(courseId)
                        .createdAt(LocalDateTime.now())
                        .build();
                videoRepository.save(videoEntity);

            } catch (IOException e) {

                throw new RuntimeException("Failed to upload video", e);
            }
        });

        return CustomResponse.success(course.getTitle(), "Course created successfully", HttpStatus.CREATED);
    }

    // public ApiResult<String> uploadCourseVideo(MultipartFile file) throws
    // IOException {
    // return CustomResponse.success(null, cloudfareService.upload(file),
    // HttpStatus.OK);
    // }
}
