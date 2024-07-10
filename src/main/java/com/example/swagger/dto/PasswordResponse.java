package com.example.swagger.dto;

import lombok.Data;

@Data
public class PasswordResponse {

    public String password;
    public PasswordResponse(String password) {
        this.password = password;
    }
}
