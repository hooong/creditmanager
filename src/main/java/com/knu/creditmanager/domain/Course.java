package com.knu.creditmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Course {

    public Course(Long courseCord,
                  String courseName,
                  String courseType,
                  int coursePoint) {
        this.courseCord = courseCord;
        this.courseName = courseName;
        this.courseType = courseType;
        this.coursePoint = coursePoint;
    }

    @Id @GeneratedValue
    private Long id;

    private Long courseCord;

    private String courseName;

    //기초,균형등등 전공인지 확인
    private String courseType;

    //학점
    private int coursePoint;
}
