package com.knu.creditmanager.exception;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException() {
        super("비밀번호가 틀렸습니다.");
    }
}
