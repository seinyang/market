package com.example.swagger.config;


import com.example.swagger.jwt.JwtInterCepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterCepter jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/auth/**") // 인증이 필요한 경로 설정
                .excludePathPatterns("/api/auth/login","/api/auth/signup","/api/auth/password-search","/api/auth/id-search","/api/auth/signup/send-code","/api/auth/password-search/send-code","/api/auth/id-search/send-code"); // 로그인 경로는 제외
    }
}
