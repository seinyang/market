package com.example.swagger.dto.password;

import lombok.Data;

@Data
public class PasswordUpdateRequest {

    private String id;

    private String email;

    private String code;

    private String newPassword;

    private String passwordCheck;
    
}
