package com.onlineshopping.mapper;

import com.onlineshopping.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    int updateUser(User user);
    User selectUserByUsernameAndOriginalPassword(@Param("username") String username, @Param("originalPassword") String originalPassword);
}