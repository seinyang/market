package com.example.swagger.dto.gada;

import lombok.Data;

import java.util.Date;

@Data
public class SupportRequsest {



    private String userName;
    private String phoneNumber;
    private Date birthday;
    private boolean certification;
    private int homeId; // `gada` 테이블의 home_id

}
