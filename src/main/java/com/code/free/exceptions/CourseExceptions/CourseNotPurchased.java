package com.code.free.exceptions.CourseExceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseNotPurchased extends RuntimeException {

    private final String error = "COURSE_NOT_PURCHASED";

    public CourseNotPurchased(String message) {
        super(message);
    }

}
