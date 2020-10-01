package com.knu.creditmanager.exception;

public class DepartmentExistedException extends RuntimeException{
    public DepartmentExistedException(String name) {
        super("해당 학과 [" + name + "]는 존재하지 않습니다." );
    }
}
