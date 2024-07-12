package com.example.swagger.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
@Data
public class HomeDto {

    private String worker;

    @JsonFormat(pattern = "MM-dd")
    private Date date;

    @JsonFormat(pattern = "HH:mm")
    private String time;

    private BigDecimal price;


}
