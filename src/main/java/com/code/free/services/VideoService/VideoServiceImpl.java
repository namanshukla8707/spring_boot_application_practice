package com.code.free.services.VideoService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.code.free.entities.video.VideoEntity;
import com.code.free.repositories.video.VideoRepo;
import com.code.free.requests.VideoRequests.VideoCreateRequest;
import com.code.free.responses.CustomResponse;
import com.code.free.responses.VideoResponses.VideoResponseDto;
import com.code.free.responses.VideoResponses.VideoResponseInterfaceDto;
import com.code.free.services.CloudfareService.CloudfareService;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepo videoRepository;
    private final CloudfareService cloudfareService;

    public ApiResult<String> uploadVideoFile(List<VideoCreateRequest> videoRequest, Long courseId) throws IOException {
        List<VideoEntity> videos = new ArrayList<>();
        Integer lastVideoPosition = videoRepository.findLastVideoPositionByCourseId(courseId);
        videoRequest.forEach(video -> {
            try {
                String key = cloudfareService.upload(video.getVideo(), courseId, lastVideoPosition+video.getPosition());
                VideoEntity videoEntity = VideoEntity.builder()
                        .title(video.getTitle())
                        .description(video.getDescription())
                        .position(lastVideoPosition+video.getPosition())
                        .url(key)
                        .courseId(courseId)
                        .createdAt(LocalDateTime.now())
                        .build();
                videos.add(videoEntity);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload video", e);
            }
        });
        videoRepository.saveAll(videos);
        return CustomResponse.success("Videos uploaded successfully", "Videos uploaded successfully",
                HttpStatus.CREATED);
    }

    public ApiResult<List<VideoResponseDto>> videosGlimpseForCourse(Long courseId) {

        List<VideoResponseInterfaceDto> videosInterfaceResponse = videoRepository.findAllWithoutUrl(courseId);
        List<VideoResponseDto> videos = videosInterfaceResponse.stream().map(video -> {
            VideoResponseDto dto = new VideoResponseDto();
            dto.setTitle(video.getTitle());
            dto.setDescription(video.getDescription());
            dto.setPosition(video.getPosition());
            return dto;
        }).toList();
        return CustomResponse.success(videos, "Video details fetched successfully for given course",
                HttpStatus.OK);
    }

    public ApiResult<List<VideoResponseDto>> videosForCourseWithPresignedUrl(Long courseId) {
        List<VideoResponseInterfaceDto> videosInterfaceResponse = videoRepository.findAllWithoutUrl(courseId);
        List<VideoResponseDto> videos = videosInterfaceResponse.stream().map(video -> {
            VideoResponseDto dto = new VideoResponseDto();
            dto.setTitle(video.getTitle());
            dto.setDescription(video.getDescription());
            dto.setPosition(video.getPosition());
            String presignedUrl = cloudfareService.getPresignedUrl(video.getUrl());
            dto.setUrl(presignedUrl);
            return dto;
        }).toList();
        return CustomResponse.success(videos, "Video details with presigned URL fetched successfully for given course",
                HttpStatus.OK);
    }
}