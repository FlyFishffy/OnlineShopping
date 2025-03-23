package com.onlineshopping.mapper;

import com.onlineshopping.entity.UserBehavior;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserBehaviorMapper {
    @Select("SELECT * FROM user_behavior WHERE user_id = #{userId}")
    List<UserBehavior> getUserBehaviorsByUserId(Integer userId);
}