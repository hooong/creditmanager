package com.knu.creditmanager.grade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GradeBySemester {

    @Id @GeneratedValue
    private Long id;

    // 1, 2, 3, 4 ...
    private Integer uniYear;

    // 봄, 여름, 가을, 겨울
    private Semester semester;

    private Integer credit;

    private Double score;

    private Double avgScore;

    public void addGrade(int credit, Grade grade) {
        
    }
}
