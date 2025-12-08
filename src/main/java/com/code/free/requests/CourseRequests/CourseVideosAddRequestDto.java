package com.code.free.requests.CourseRequests;

import java.util.List;

import com.code.free.requests.VideoRequests.VideoCreateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseVideosAddRequestDto {
    private Long courseId;
    List<VideoCreateRequest> videoRequests;
}
