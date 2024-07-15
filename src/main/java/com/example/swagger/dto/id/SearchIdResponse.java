package com.example.swagger.dto.id;

import lombok.Data;

@Data
public class SearchIdResponse {


    String id;

    public SearchIdResponse(String id){
        this.id=id;
    }
}
