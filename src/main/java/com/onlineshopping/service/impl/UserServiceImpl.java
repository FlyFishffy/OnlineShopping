package com.onlineshopping.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.onlineshopping.dto.LoginFormDTO;
import com.onlineshopping.dto.Result;
import com.onlineshopping.entity.User;
import com.onlineshopping.mapper.UserMapper;
import com.onlineshopping.service.UserService;
import com.onlineshopping.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        // 1.校验手机号
        String phone = loginForm.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误！");
        }

        // 3.一致，根据手机号查询用户 select * from tb_user where phone = ?
        User user = userMapper.listByPhone(phone);

        // 4.判断用户是否存在
        if (user == null) {
            // 5.不存在，创建新用户并保存
            user = createUserWithPhone(phone);
            return Result.ok(user.getId());
        }

        // 5.存在，校验密码
        String userpwd = user.getPassword();
        String pwd = loginForm.getPassword();
        if (userpwd == null || !userpwd.equals(pwd)) {
            // 不一致，报错
            return Result.fail("密码错误");
        }

        //6.保存用户到session
        session.setAttribute("user", user);
        // 8.返回id
        return Result.ok();
    }

    private User createUserWithPhone(String phone) {
        // 1.创建用户
        User user = new User();
        user.setPhone(phone);
        user.setName("user_" + RandomUtil.randomString(10));
        user.setPassword("123456");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        // 2.保存用户
        userMapper.insert(user);
        return user;
    }
}
