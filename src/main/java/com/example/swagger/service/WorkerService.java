package com.example.swagger.service;

import com.example.swagger.dto.gada.*;
import com.example.swagger.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final Mapper mapper;


    public List<HomeDto> home (){
        return mapper.workerHome();
    }

    public ClickWorkDto getWorker(int homeId){
        return mapper.workerApply(homeId);
    }


//지원하기
    public void applySupport(String userId, int homeId) {
        // 사용자 정보 조회
        UserInfoDto userInfo = mapper.getUserInfo(userId);


        if (userInfo == null) {
            throw new RuntimeException("User not found");
        }

        if (!userInfo.isCertification()) {
            throw new RuntimeException("User is not certified");
        }

        // 지원 요청 DTO 생성
        SupportRequsest supportRequestDto = new SupportRequsest();
        supportRequestDto.setUserName(userInfo.getName());
        supportRequestDto.setPhoneNumber(userInfo.getPhoneNumber());
        supportRequestDto.setBirthday(userInfo.getBirthday());
        supportRequestDto.setCertification(userInfo.isCertification());
        supportRequestDto.setHomeId(homeId);

        // 지원 정보 삽입
        mapper.SendUserInfo(supportRequestDto);

    }

//지원내역
    public List<SupportResponse> support(String userName){
        return mapper.Support(userName);
    }

    //마이페이지
    public MyPageDTO getMyPage(String userName){

        MyPageDTO myPageInfo = mapper.MyPage(userName);
        if (myPageInfo == null) {
            throw new RuntimeException("User not found");
        }
        return myPageInfo;
    }
}
