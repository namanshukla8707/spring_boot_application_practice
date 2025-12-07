package com.code.free.exceptions.CourseExceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseNotFoundException extends RuntimeException {

    private final String error="COURSE_NOT_FOUND";

    public CourseNotFoundException(String message) {
        super(message);
    }
    
}
