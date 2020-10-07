package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.domain.Course;
import com.knu.creditmanager.domain.CourseSession;
import com.knu.creditmanager.grade.Grade;
import com.knu.creditmanager.grade.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyCourse extends Course {

    public MyCourse(Long courseCord,
                    String courseName,
                    String courseType,
                    int coursePoint,
                    Grade grade,
                    int uniYear,
                    Semester semester) {
        super(courseCord, courseName, courseType, coursePoint);
        this.grade = grade;
        this.semester = semester;
        this.uniYear = uniYear;
    }

    @Id @GeneratedValue
    private Long id;
    private Grade grade;
    private int uniYear;
    private Semester semester;

}

