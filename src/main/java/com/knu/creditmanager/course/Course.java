package com.knu.creditmanager.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private Long courseCord;

    @NonNull
    private String courseName;

    @NonNull
    private String courseType;

    // 추후 Enum으로 변경 가능
    private String courseFTF;

    private Long courseDivision;

    // 학점-시수-이론-실습
    private String courseCredit;

    // 교양 분야
    private String courseTypeDetail;

    private String courseTarget;

    private Integer coursePerson;

    private String courseUni;

    private String courseMid;

    private String courseDep;

    private String corseTeach;

    private String courseTeachType;

    private String courseTime;

    private String courseELearning;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseCord=" + courseCord +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseFTF='" + courseFTF + '\'' +
                ", courseDivision=" + courseDivision +
                ", courseCredit='" + courseCredit + '\'' +
                ", courseTypeDetail='" + courseTypeDetail + '\'' +
                ", courseTarget='" + courseTarget + '\'' +
                ", coursePerson=" + coursePerson +
                ", courseUni='" + courseUni + '\'' +
                ", courseMid='" + courseMid + '\'' +
                ", courseDep='" + courseDep + '\'' +
                ", corseTeach='" + corseTeach + '\'' +
                ", courseTeachType='" + courseTeachType + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", courseELearning='" + courseELearning + '\'' +
                '}';
    }
}
