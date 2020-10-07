package com.knu.creditmanager.domain;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class CourseSession extends Course {

    public CourseSession(Long courseCord, String courseName, String courseType, int coursePoint) {
        super(courseCord, courseName, courseType, coursePoint);
    }

}
