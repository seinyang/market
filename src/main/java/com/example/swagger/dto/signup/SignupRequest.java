package com.example.swagger.dto.signup;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest {

    private String name;
    private String id;
    private String password;
    private String phoneNumber;
    private String email;
    private String code;  // 사용자가 입력한 인증 코드
    private LocalDate birthday;



}
