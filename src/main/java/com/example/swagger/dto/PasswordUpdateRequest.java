package com.example.swagger.dto;

import lombok.Data;

@Data
public class PasswordUpdateRequest {

    private String id;

    private String email;

    private String code;

    private String newPassword;

    private String passwordConfirm;
    
}
