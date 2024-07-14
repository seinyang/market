package com.example.swagger.controller;


import com.example.swagger.dto.*;
import com.example.swagger.dto.login.JwtResponse;
import com.example.swagger.dto.login.LoginRequest;
import com.example.swagger.jwt.JwtUtil;
import com.example.swagger.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증 ")
public class LoginController {

    private final LoginService service;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = " 로그인 할 때 사용하는 API")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpResponse) {
        String id = loginRequest.getId();
        String password = loginRequest.getPassword();

        User authenticationUser = service.authenticationLogin(id, password);
        // 인증 성공 시 사용자 정보 반환, 실패 시 UNAUTHORIZED 반환
        if (authenticationUser != null) {
            String token = jwtUtil.generateToken(authenticationUser.getId());

            // JWT 토큰을 쿠키에 담아서 클라이언트에게 전달
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setMaxAge((int) jwtUtil.getTokenValidityInSeconds()); // 쿠키 유효 기간 설정
            cookie.setPath("/"); // 모든 경로에서 접근 가능하도록 설정
            cookie.setHttpOnly(true); // JavaScript로 접근할 수 없도록 설정
            httpResponse.addCookie(cookie);

            return ResponseEntity.ok(new JwtResponse(token, authenticationUser));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = " 회원가입 할 때 사용하는 API")

    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {
        String email = signupRequest.getEmail();
        String code = signupRequest.getCode();

        // 사용자가 입력한 인증 코드를 검증
        boolean isCodeValid = service.verifySignupCode(email, code);
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


    //아이디 찾기
    @PostMapping("/id-search")
    @Operation(summary = "아이디 찾기", description = " 아이디찾기 할 때 사용하는 API")

    public ResponseEntity<SearchIdResponse> idSearch(@RequestBody SearchIdRequest searchIdRequest) {
        String email = searchIdRequest.getEmail();
        String code = searchIdRequest.getCode();


        // 사용자가 올바른 인증 코드를 입력했는지 확인
        boolean isCodeValid = service.verifyIdCode(email, code);
        if (!isCodeValid) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = service.getFindId(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SearchIdResponse response = new SearchIdResponse(user.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/password-search")
    @Operation(summary = "비번 찾기", description = " 비번찾기 할 때 사용하는 API")

    public ResponseEntity<PasswordResponse> idSearch(@RequestBody PasswordUpdateRequest passwordUpdateRequest) {

        String id = passwordUpdateRequest.getId();
        String email = passwordUpdateRequest.getEmail();
        String code = passwordUpdateRequest.getCode();
        String newPassword = passwordUpdateRequest.getNewPassword();
        String confirm = passwordUpdateRequest.getPasswordCheck();

        //변경할 비번 같은지 확인
        if (!newPassword.equals(confirm)){
             return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 사용자가 올바른 인증 코드를 입력했는지 확인
        boolean isCodeValid = service.verifyPasswordCode(email, code);
        if (!isCodeValid) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = service.passwordSearch(id,email,newPassword);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PasswordResponse response = new PasswordResponse(user.getPassword());
        return ResponseEntity.ok(response);
    }


}







