package com.example.swagger.service;

import com.example.swagger.dto.LoginRequest;
import com.example.swagger.dto.SignupRequest;
import com.example.swagger.dto.User;
import com.example.swagger.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    public final Mapper mapper;
    private final EmailService emailService;
    private final Map<String, String> verificationCodes = new HashMap<>();
    //로그인
    public User authenticationLogin(String id,String password){
        return mapper.findUserByIdAndPassword(id,password);
    }

    //회원가입
    public void addUser(User user){
        mapper.insertUser(user);
    }

    //회원가입 이메일 전송
    public void sendVerificationCode(String email, String verificationCode) {
        emailService.sendVerificationSignUpCode(email, verificationCode);
    }

    //회원가입 이메일 코드 검증
    public boolean verifySignupCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }



    //    public void sendSignUpCode(String email, String verificationCode) {
//        emailService.sendVerificationSignUpCode(email, verificationCode);
//    }



    //아이디 찾기
    public User getFindId(String email){
        return mapper.findUserById(email);
    }

    // 아이디 찾기 - 인증 코드 검증
    public boolean verifyIdCode(String email, String inputCode) {
        String sentCode = verificationCodes.get(email);
        return sentCode != null && sentCode.equals(inputCode);
    }

    // 아이디 찾기 인증 코드 전송
    public void sendIdSearchCode(String email, String verificationCode) {
        emailService.sendVerificationIdSearchCode(email, verificationCode);
    }


    // 회원가입,아디 찾기 인증 코드 저장
    public void storeVerificationCode(String email, String verificationCode) {
        verificationCodes.put(email, verificationCode);
    }


}
