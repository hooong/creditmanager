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
    private Long courseCord;

    @NonNull
    private String courseName;

    @NonNull
    private String courseType;

    @NonNull
    private int coursePoint;

}
