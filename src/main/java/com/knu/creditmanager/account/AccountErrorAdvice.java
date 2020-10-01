package com.knu.creditmanager.account;

import com.knu.creditmanager.exception.PasswordWrongException;
import com.knu.creditmanager.exception.StudentIdExistedException;
import com.knu.creditmanager.exception.StudentIdNotExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountErrorAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StudentIdExistedException.class)
    public String handleExisted() {
        return "{\"error\": \"이미 존재하는 학번입니다.\"}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StudentIdNotExistedException.class)
    public String handleNotExisted() {
        return "{\"error\": \"존재하지 않는 학번입니다.\"}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordWrongException.class)
    public String handleWrongPassword() {
        return "{\"error\": \"비밀번호가 틀립니다.\"}";
    }
}
