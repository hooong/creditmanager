package com.knu.creditmanager.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
public class Department {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String link;

}
