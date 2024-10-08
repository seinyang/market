package com.example.swagger.controller;

import com.example.swagger.dto.password.PasswordRequest;
import com.example.swagger.dto.signup.SendEmailRequest;
import com.example.swagger.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "인증 메일")

public class EmailController {

    private final LoginService service;

    //이메일형식 메서드
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    @PostMapping("/signup/send-code")
    @Operation(summary = "회원가입 - 인증 코드 전송", description = "회원가입을 위해 이메일로 인증 코드를 전송합니다.")
    public ResponseEntity<Void> sendSignupCode(@RequestBody SendEmailRequest sendEmailRequest) {
        String email = sendEmailRequest.getEmail();

        if (isValidEmail(email)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 인증 코드 생성
        String verificationCode = generateVerificationCode();

        // 이메일로 인증 코드 전송
        service.sendVerificationCode(email, verificationCode);

        // 세션이나 다른 저장소에 인증 코드 저장 (여기서는 예제이므로 간단히 설명)
        service.storeVerificationCode(email, verificationCode);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 아이디 찾기 - 이메일로 인증 코드 전송
    @PostMapping("/id-search/send-code")
    @Operation(summary = "아이디 찾기 - 인증 코드 전송", description = "아이디 찾기 시 이메일로 인증 코드를 전송하는 API")
    public ResponseEntity<Void> sendIdCode(@RequestBody SendEmailRequest sendEmailRequest) {

        String email = sendEmailRequest.getEmail();


        if (isValidEmail(email)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 인증 코드 생성
        String verificationCode = generateVerificationCode();

        //이메일로 인증 코드 전송
        service.sendIdSearchCode(email, verificationCode);


        //인증 코드 저장
        service.storeVerificationCode(email, verificationCode);



        return ResponseEntity.ok().build();
    }

    // 비번 찾기 - 이메일로 인증 코드 전송
    @PostMapping("/password-search/send-code")
    @Operation(summary = "비번 찾기  - 인증 코드 전송", description = "비번 찾기 시 이메일로 인증 코드를 전송하는 API")
    public ResponseEntity<Void> sendPasswordCode(@RequestBody PasswordRequest passwordRequest) {

        String id = passwordRequest.getId();
        String email = passwordRequest.getEmail();

        if (isValidEmail(email)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 사용자가 아이디와 이메일이 일치하는지 확인
        boolean isValidUser = service.validateUser(id, email);
        if (!isValidUser) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // 인증 코드 생성
        String verificationCode = generateVerificationCode();

        //이메일로 인증 코드 전송
        service.sendPasswordSearchCode(email, verificationCode);

        //인증 코드 저장
        service.storeVerificationCode(email, verificationCode);

        return ResponseEntity.ok().build();
    }

    // 이메일 형식 검증 메서드
    private boolean isValidEmail(String email) {
        return !EMAIL_PATTERN.matcher(email).matches();
    }

    // 인증 코드 생성 메서드
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
