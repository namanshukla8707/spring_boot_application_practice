package com.code.free.entities.userCourseMapping;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseMappingId implements Serializable {
    private Long userId;
    private Long courseId;
}
