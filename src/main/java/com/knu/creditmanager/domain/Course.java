package com.knu.creditmanager.domain;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Course {

    public Course(Long courseCord,String courseName){
        this.courseCord = courseCord;
        this.courseName = courseName;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Long courseCord;

    @NonNull
    private String courseName;

    //기초,균형등등 전공인지 확인
    @NonNull
    private String courseType;

    //학점
    @NonNull
    private int coursePoint;

}
