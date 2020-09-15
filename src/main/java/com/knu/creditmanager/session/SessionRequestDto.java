package com.knu.creditmanager.session;

import lombok.Data;

@Data
public class SessionRequestDto {
    private String studentId;
    private String password;
}
