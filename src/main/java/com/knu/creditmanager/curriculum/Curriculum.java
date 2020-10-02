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
    private String year;

    // 기초교양

    // 균형

    // 특화

    // 대학별

    // 전필

    // 전선

    // 전공 심화

    // 자선

    // 전체 총점 130
}
