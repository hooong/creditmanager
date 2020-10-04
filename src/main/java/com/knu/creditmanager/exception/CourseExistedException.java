package com.knu.creditmanager.exception;

public class CourseExistedException extends RuntimeException {
    public CourseExistedException(Long courseCord) {
        super("해당 과목 [" + courseCord + "]는 존재하지 않습니다." );
    }
}
