package com.knu.creditmanager.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Department {

    public Department(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private String link;

}
