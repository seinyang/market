package com.example.swagger.dto.gada;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ClickWorkDto {
    private String workName;


    private String place;


    private String map;

    private Double distance;


    private Integer personnal;


    private LocalTime workingIn;


    private LocalTime workingOut;
}
