package com.example.swagger.dto.gada;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;


@Data
public class UserInfoDto {

    private String name;

    private String id;

    private String phoneNumber;


    private Date birthday;

    private boolean certification;
}
