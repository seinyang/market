package com.example.swagger.dto.gada;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendInfo extends ClickWorkDto {

    private String name;


    private String phoneNumber;


    private LocalDate birthday;

    private boolean certification;
}
