package com.example.swagger.controller;


import com.example.swagger.dto.SignupRequest;
import com.example.swagger.dto.User;
import com.example.swagger.service.EmailService;
import com.example.swagger.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증")
public class LoginController {

    private final Service service;
    private final EmailService emailService;


    @PostMapping("/login")
    @Operation(summary = "업체 로그인", description = " 로그인 할 때 사용하는 API")
    public String login() {
        return login();
    }

    @PostMapping("/signup")
    @Operation(summary = "업체 회원가입", description = " 회원가입 할 때 사용하는 API")

    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {
        // 인증 코드 생성
        String verificationCode = generateVerificationCode();

        // 이메일로 인증 코드 전송
        service.sendVerificationCode(signupRequest.getEmail(), verificationCode);

        // 사용자가 올바른 인증 코드를 입력했는지 확인
        boolean isCodeValid = service.verifyCode(signupRequest.getEmail(), verificationCode, signupRequest.getCode());
        if (!isCodeValid) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 사용자 등록
        User newUser = new User();
        newUser.setName(signupRequest.getName());
        newUser.setId(signupRequest.getId());
        newUser.setPassword(signupRequest.getPassword());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setBirthday(signupRequest.getBirthday());

        service.addUser(newUser);



        // 회원가입 성공
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.DATE, date);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

     //인증 코드 생성 메서드
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

}




