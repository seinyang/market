package com.example.swagger.mapper;

import com.example.swagger.dto.LoginRequest;
import com.example.swagger.dto.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    void insertUser(User user);

    User findUserByIdAndPassword(@Param("id") String id,@Param("password") String password);

    User findUserById(@Param("email") String email);

}
