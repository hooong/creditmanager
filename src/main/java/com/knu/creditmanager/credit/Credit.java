package com.knu.creditmanager.credit;

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
public class Credit {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String year;

    // 기초교양

    // 균형

    // 특화

    // 대학별

    // 교양 총점 +10학점까지

    // 전필

    // 전선

    // 전공 심화

    // 전공 총점 = 넘어가면 총점

    // 자선

    // 전체 총점 130
}
