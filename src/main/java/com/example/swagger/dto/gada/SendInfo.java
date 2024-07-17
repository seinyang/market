package com.example.swagger.dto.gada;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


@Data
public class SendInfo  {

    private String name;


    private String phoneNumber;


    private LocalDate birthday;

    private boolean certification;
}
