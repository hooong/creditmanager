package com.knu.creditmanager.account;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterAccountDto {
    private String studentId;
    private String password;
    private String name;
    private String semester;
    private String major;
}
