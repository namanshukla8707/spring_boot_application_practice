package com.code.free.requests.CourseRequests;

import java.util.List;


import com.code.free.requests.VideoRequests.VideoCreateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {
    private String title;
    private Long price;
    private Integer discount;

    private List<VideoCreateRequest> videos;

}
