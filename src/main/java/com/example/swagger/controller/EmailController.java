package com.example.swagger.controller;

import com.example.swagger.dto.SignupRequest;
import com.example.swagger.service.EmailService;
import com.example.swagger.service.Service;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "인증")
@Hidden
public class EmailController {

    private final Service service;

    @PostMapping("/send-code")
    @Operation(summary = "인증 코드 전송", description = "회원가입을 위해 이메일로 인증 코드를 전송합니다.")
    public ResponseEntity<Void> sendVerificationCode(@RequestBody SignupRequest signupRequest) {
        String email = signupRequest.getEmail();

        // 인증 코드 생성
        String verificationCode = generateVerificationCode();

        // 이메일로 인증 코드 전송
        service.sendVerificationCode(email, verificationCode);

        // 세션이나 다른 저장소에 인증 코드 저장 (여기서는 예제이므로 간단히 설명)
        service.storeVerificationCode(email, verificationCode);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 인증 코드 생성 메서드
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

}
