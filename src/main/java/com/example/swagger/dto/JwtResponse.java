package com.example.swagger.dto;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;

    private String name;

    private String id;


    public JwtResponse(String token,User user) {

        this.token=token;
        this.name= user.getName();
        this.id = user.getId();
    }
}
