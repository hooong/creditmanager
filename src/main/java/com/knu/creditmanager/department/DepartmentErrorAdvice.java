package com.knu.creditmanager.department;

import com.knu.creditmanager.exception.DepartmentExistedException;
import com.knu.creditmanager.exception.DepartmentNotExistedException;
import com.knu.creditmanager.exception.StudentIdExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DepartmentErrorAdvice extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepartmentExistedException.class)
    public String handleExisted() {
        return "{\"error\": \"이미 존재하는 학과입니다.\"}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepartmentNotExistedException.class)
    public String handleNotExisted() {
        return "{\"error\": \"존재하지 않는 학과입니다.\"}";
    }
}
