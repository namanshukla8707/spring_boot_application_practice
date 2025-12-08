package com.code.free.responses.VideoResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponseDto {
    private String title;
    private String description;
    private Integer position;
    private String url;
}
