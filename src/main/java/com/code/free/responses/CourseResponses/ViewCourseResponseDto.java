package com.code.free.responses.CourseResponses;

import java.util.List;

import com.code.free.responses.VideoResponses.VideoResponseDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewCourseResponseDto {

    private String courseTitle;
    private Long createdBy;
    private Long coursePrice;
    private Integer courseDiscount;
    List<VideoResponseDto> videosList;
}
