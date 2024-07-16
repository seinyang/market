package com.example.swagger.dto.gada;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
@Data
public class HomeDto {

    private int homeId;

    private String worker;

    @JsonFormat(pattern = "MM-dd")
    private Date date;

    @JsonFormat(pattern = "HH:mm")
    private String time;

    private BigDecimal price;


}
