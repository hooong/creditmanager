package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.course.Course;
import com.knu.creditmanager.grade.Grade;
import com.knu.creditmanager.grade.Semester;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyCourse {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String studentId;

    @NonNull
    private Long courseId;

    private Grade grade;

    private Integer credit;

    // 학년
    private int uniYear;

    private Semester semester;

    @Override
    public String toString() {
        return "MyCourse{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", courseId=" + courseId +
                ", grade=" + grade +
                ", credit=" + credit +
                ", uniYear=" + uniYear +
                ", semester=" + semester +
                '}';
    }
}

