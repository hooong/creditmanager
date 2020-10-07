package com.knu.creditmanager.account;

import com.knu.creditmanager.grade.Semester;
import lombok.Builder;
import lombok.Data;

@Data
public class RegisterAccountDto {
    private String studentId;
    private String password;
    private String name;
    private Integer uniYear;
    private Semester semester;
    private String major;
}
