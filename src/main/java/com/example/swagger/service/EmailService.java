package com.example.swagger.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendVerificationCode(String email, String verificationCode) {
        String subject = "회원가입 인증 코드";
        String text = "회원가입을 완료하려면 아래 인증 코드를 입력해주세요:\n" + verificationCode;
        String from = "did756984@naver.com";

        try {
            sendEmail(email, subject, text, from);
            log.debug("이메일을 성공적으로 전송했습니다.");
        } catch (MailException e) {
            log.error("이메일 전송 중 오류가 발생했습니다.", e);
            // 예외 처리
        }
    }
    public void sendEmail(String to, String subject, String text, String from) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("did756984@naver.com");

        mailSender.send(message);

    }
}
