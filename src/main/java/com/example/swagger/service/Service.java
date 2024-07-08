package com.example.swagger.service;

import com.example.swagger.dto.SignupRequest;
import com.example.swagger.dto.User;
import com.example.swagger.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    public final Mapper mapper;
    private final EmailService emailService;

    private Map<String, String> verificationCodes = new HashMap<>();
    public void addUser(User user){
        mapper.insertUser(user);
    }


    public boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }
//    public boolean verifyCode(String email, String code, String userInputCode) {
//        // 사용자의 입력 코드와 실제 코드 비교
//        return code.equals(userInputCode);
//    }
    public void sendVerificationCode(String email, String verificationCode) {
        emailService.sendVerificationCode(email, verificationCode);
    }

    public void storeVerificationCode(String email, String verificationCode) {
        verificationCodes.put(email, verificationCode);
    }
}
