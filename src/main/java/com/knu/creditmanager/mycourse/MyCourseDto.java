package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.grade.Grade;
import com.knu.creditmanager.grade.Semester;
import lombok.Data;

@Data
public class MyCourseDto {
    private Long courseCord;
    private Grade grade;
    private Integer uniYear;
    private Semester semester;
}
