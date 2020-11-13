package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.course.Course;
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
public class MyCourse {

    @Id @GeneratedValue
    private Long id;

    private String studentId;

    private Grade grade;

    private Integer credit;

    private int uniYear;

    private Semester semester;

    private void setCredit(String courseCredit) {
        credit = Integer.parseInt(courseCredit.substring(0,0));
    }



}

