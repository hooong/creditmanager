package com.knu.creditmanager.domain;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CourseSession extends Course {

    public CourseSession(Long courseCord, String courseName, String courseType, int coursePoint) {
        super(courseCord, courseName, courseType, coursePoint);
    }

    @Id @GeneratedValue
    private Long id;

}
