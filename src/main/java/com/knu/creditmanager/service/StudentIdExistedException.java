package com.knu.creditmanager.service;

public class StudentIdExistedException extends RuntimeException{
    StudentIdExistedException(String studentId){
        super("학번 " + studentId + "는 이미 존재합니다.");
    }
}
