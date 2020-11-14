package com.knu.creditmanager.credit;

import com.knu.creditmanager.course.Course;
import com.knu.creditmanager.curriculum.Curriculum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Credit {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String studentId;

    private Long curriculumId;

    // 기초교양
    private Integer foundation;

    // 균형
    private Integer balance;

    // 특화
    private Integer specialization;

    // 대학별
    private Integer byUni;

    // 교양 총점 +10학점까지
    private Integer sumCulture;

    // 전필
    private Integer majorNecessary;

    // 전선
    private Integer majorSelection;

    // 전공 심화
    private Integer majorDeepening;

    // 전공 총점 = 넘어가면 총점
    private Integer majorSum;

    // 자선
    private Integer freeChoice;

    // 전체 총점 130
    private Integer allSum;

    public Credit(String studentId, Long curriculumId, Integer foundation, Integer balance,
                  Integer specialization, Integer byUni, Integer sumCulture,
                  Integer majorNecessary, Integer majorSelection, Integer majorDeepening,
                  Integer majorSum, Integer freeChoice, Integer allSum) {
        this.studentId = studentId;
        this.curriculumId = curriculumId;
        this.foundation = foundation;
        this.balance = balance;
        this.specialization = specialization;
        this.byUni = byUni;
        this.sumCulture = sumCulture;
        this.majorNecessary = majorNecessary;
        this.majorSelection = majorSelection;
        this.majorDeepening = majorDeepening;
        this.majorSum = majorSum;
        this.freeChoice = freeChoice;
        this.allSum = allSum;
    }

    public void cutCredit(Course course) {
        String type = course.getCourseType();

        switch (type) {
            case "기초교양":
            case "균형교양":
            case "특화교양":
            case "대학별교양":
            case "교직":
            case "자유선택":
            case "전공필수":
            case "전공선택":
        }
    }
}
