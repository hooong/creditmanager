package com.knu.creditmanager.course;

import com.knu.creditmanager.exception.CourseExistedException;
import com.knu.creditmanager.exception.CourseNotExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CourseErrorAdvice extends ResponseEntityExceptionHandler{
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CourseExistedException.class)
    public String handleExisted() {
        return "{\"error\": \"이미 존재하는 과목입니다.\"}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CourseNotExistedException.class)
    public String handleNotExisted() {
        return "{\"error\": \"존재하지 않는 과목입니다.\"}";
    }
}