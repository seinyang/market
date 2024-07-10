package com.example.swagger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class User {

    @NotBlank(message = "이름을 입력하세요.")
    public String name;

    @NotBlank(message = "아이디를 입력하세요.")
    public String id;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 6, max = 12, message = "비밀번호는 6자 이상 12자 이하여야 합니다.")
    public String password;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    public String email;

    @NotNull(message = "생년월일을 입력하세요.")
    public LocalDate birthday;


}
