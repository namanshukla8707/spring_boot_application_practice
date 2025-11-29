package com.code.free.requests.CourseRequests;

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
}
