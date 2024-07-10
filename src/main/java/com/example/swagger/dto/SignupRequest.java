package com.example.swagger.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest {

    private String name;
    private String id;
    private String password;
    private String email;
    private LocalDate birthday;
    private String code;  // 사용자가 입력한 인증 코드


}
