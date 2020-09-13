package com.knu.creditmanager.domain;

public class StudentIdExistedException extends RuntimeException{
    public StudentIdExistedException(String studentId){
        super("학번 " + studentId + "는 이미 존재합니다.");
    }
}
