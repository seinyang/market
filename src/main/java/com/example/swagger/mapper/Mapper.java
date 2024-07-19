package com.example.swagger.mapper;

import com.example.swagger.dto.gada.*;
import com.example.swagger.dto.signup.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    void insertUser(User user);

    User findUserByIdAndPassword(@Param("id") String id,@Param("password") String password);

    User findUserById(@Param("email") String email);

    void updatePassword(@Param("id") String id, @Param("email") String email, @Param("password") String newPassword);

    User findUserByIdAndEmail(@Param("id") String id, @Param("email") String email);

    List<HomeDto> workerHome();
    //일자리 클릭
    ClickWorkDto workerApply(int homeId);
    //사용자 확인(인증서 유무)
    UserInfoDto getUserInfo(String userId);
    //사용자 확인후 지원하기
    void SendUserInfo(SupportRequsest supportRequsest);
    //지원내역
    List<SupportResponse> Support(String userName);

    //마이페이지
    MyPageDTO MyPage(String userName);

}
