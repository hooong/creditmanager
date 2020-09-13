package com.knu.creditmanager.controller;

import com.knu.creditmanager.domain.StudentIdExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountErrorAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentIdExistedException.class)
    public String handleExisted() {
        return "{}";
    }
}
