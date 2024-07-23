package com.example.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "더보기", description = "더보기 안에 여러가지 API")
public class WorkerMoreController {

    @Operation(summary = "공지사항", description = "공지사항 페이지")
    @GetMapping("/notice")
    public String getNotice() {
        return "공지사항 내용";
    }

    @Operation(summary = "개인정보 처리방침", description = "개인정보 처리방침 페이지")
    @GetMapping("/privacy")
    public String getPrivacy() {
        return "개인정보 처리방침 내용";
    }

    @Operation(summary = "위치 서비스 이용 약관", description = "위치 서비스 이용 약관 페이지")
    @GetMapping("/location-terms")
    public String getLocationTerms() {
        return "위치 서비스 이용 약관 내용";
    }

    @Operation(summary = "개인정보 3자 제공", description = "개인정보 3자 제공 페이지")
    @GetMapping("/third-party")
    public String getThirdParty() {
        return "개인정보 3자 제공 내용";
    }

    @Operation(summary = "회사소개", description = "회사소개 페이지")
    @GetMapping("/about")
    public String getAbout() {
        return "회사소개 내용";
    }

    @Operation(summary = "고객센터", description = "고객센터 페이지")
    @GetMapping("/contact")
    public String getContact() {
        return "고객센터 내용";
    }
}