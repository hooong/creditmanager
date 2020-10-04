package com.knu.creditmanager.exception;

public class CourseNotExistedException extends RuntimeException{
    public CourseNotExistedException(Long courseCord) {
        super("해당 과 [" + courseCord + "]는 존재하지 않습니다." );
    }
}
