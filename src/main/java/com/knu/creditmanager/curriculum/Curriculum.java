package com.knu.creditmanager.curriculum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Curriculum {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String curriculumYear;

    private String curriculumUni;

    private String curriculumName;

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
}
