package com.knu.creditmanager.domain;

import lombok.*;
import org.springframework.data.annotation.Id;


import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    @Setter
    private Long courseCord;

    @NotEmpty
    private String courseName;

    @NotEmpty
    private String courseType;

    @NotEmpty
    private int coursePoint;



}
