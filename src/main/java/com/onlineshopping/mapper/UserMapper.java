package com.onlineshopping.mapper;


import com.onlineshopping.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据用户手机查询用户
     * @param phone
     * @return
     */
    @Select("select * from user where phone = #{phone}")
    User listByPhone(String phone);

    /**
     * 插入用户信息
     * @param user
     */
    @Insert("insert into user (username, password, phone, createTime, updateTime)" +
            "VALUES (#{username}, #{password}, #{phone}, #{createTime}, #{updateTime})")
    void insert(User user);
}
