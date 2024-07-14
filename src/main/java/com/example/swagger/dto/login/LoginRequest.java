package com.example.swagger.dto.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String id;
    private String password;  // 클라이언트로부터 입력받는 패스워드
}
