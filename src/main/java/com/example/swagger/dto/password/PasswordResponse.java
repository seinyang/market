package com.example.swagger.dto.password;

import lombok.Data;

@Data
public class PasswordResponse {

    public String password;
    public PasswordResponse(String password) {
        this.password = password;
    }
}
