package com.example.swagger.dto;

import lombok.Data;

@Data
public class SearchIdResponse {


    String id;

    public SearchIdResponse(String id){
        this.id=id;
    }
}
