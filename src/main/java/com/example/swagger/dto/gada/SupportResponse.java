package com.example.swagger.dto.gada;

import lombok.Data;

import java.util.Date;

@Data
public class SupportResponse {

    private String userName;           // 사용자 이름
    private String phoneNumber;    // 사용자 전화번호
    private Date birthday;         // 사용자 생일
    private boolean certification; // 인증서 여부
    private String workName;       // 작업 이름
    private String place;          // 작업 장소
    private String map;            // 지도 정보
    private Double distance;       // 거리
    private int personnel;         // 인원
    private String workingIn;      // 작업 시작 시간
    private String workingOut;     // 작업 종료 시간



}
