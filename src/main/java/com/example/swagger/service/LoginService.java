package com.example.swagger.service;

import com.example.swagger.dto.User;
import com.example.swagger.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

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



    // 비밀번호 찾기
    public User passwordSearch(String id, String email, String newPassword) {
        User user = mapper.findUserByIdAndEmail(id, email);
        if (user == null) {
            return null;
        }
        mapper.updatePassword(id, email, newPassword);
        return mapper.findUserByIdAndEmail(id, email);
    }


    //사용자 id,email 검증
    public boolean validateUser(String id, String email) {
        User user = mapper.findUserByIdAndEmail(id,email);
        return user != null;
    }

    // 비번 찾기 인증 코드 전송
    public void sendPasswordSearchCode(String email, String verificationCode) {
        emailService.sendPasswordSearchCode(email, verificationCode);
        storeVerificationCode(email, verificationCode); // 인증 코드 저장
    }

    // 비번 찾기 - 인증 코드 검증
    public boolean verifyPasswordCode(String email, String inputCode) {
        String sendCode = verificationCodes.get(email);
        return sendCode != null && sendCode.equals(inputCode);
    }



    // 회원가입,아디 찾기,비번찾기 인증 코드 저장
    public void storeVerificationCode(String email, String verificationCode) {
        verificationCodes.put(email, verificationCode);
    }
}
