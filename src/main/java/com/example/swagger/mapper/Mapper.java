package com.example.swagger.mapper;

import com.example.swagger.dto.User;
import com.example.swagger.dto.HomeDto;
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

}
