package com.onlineshopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.onlineshopping.dto.LoginFormDTO;
import com.onlineshopping.dto.Result;
import com.onlineshopping.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    /**
     * 用户登录
     * @param loginForm
     * @param session
     * @return
     */
    Result login(LoginFormDTO loginForm, HttpSession session);
}
