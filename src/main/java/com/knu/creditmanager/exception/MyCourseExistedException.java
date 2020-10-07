package com.knu.creditmanager.exception;

public class MyCourseExistedException extends RuntimeException{
    public MyCourseExistedException(Long courseCord) {
        super("해당 과목 [" + courseCord + "]는 존재하지 않습니다." );
    }
}

