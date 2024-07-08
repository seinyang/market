package com.example.swagger.mapper;

import com.example.swagger.dto.User;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    void insertUser(User user);
}
