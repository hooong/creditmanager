package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.domain.Course;
import com.knu.creditmanager.grade.Grade;
import com.knu.creditmanager.grade.Semester;
import lombok.NonNull;

public class MyCourse extends Course {

    public MyCourse(@NonNull Long courseCord, @NonNull String courseName, @NonNull String courseType, @NonNull int coursePoint, Grade grade, int uniYear, Semester
            semester) {
        super(courseCord, courseName, courseType, coursePoint);
        this.grade = grade;
        this.uniYear = uniYear;
        this.semester = semester;
    }

    private Grade grade;

    private int uniYear;

    private Semester semester;



}

