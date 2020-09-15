package com.knu.creditmanager.exception;

public class StudentIdNotExistedException extends RuntimeException {
    public StudentIdNotExistedException() {
        super("해당 학번은 존재하지 않습니다.");
    }
}
