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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentIdExistedException.class)
    public String handleExisted() {
        return "{}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentIdNotExistedException.class)
    public String handleNotExisted() {
        return "{}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordWrongException.class)
    public String handleWrongPassword() {
        return "{}";
    }
}
