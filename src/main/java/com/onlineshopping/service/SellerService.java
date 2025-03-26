package com.onlineshopping.service;

import com.onlineshopping.dto.Result;
import com.onlineshopping.entity.Seller;

import javax.servlet.http.HttpSession;

/**
 * 卖家业务逻辑接口
 */
public interface SellerService {

    /**
     * 卖家注册方法
     * @param seller 卖家实体
     * @return Result 操作结果
     */
    Result register(Seller seller);

    /**
     * 卖家登录方法
     * 当登录成功后，将用户的邮箱、密码和角色保存到 session 中
     * @param email 用户邮箱
     * @param password 用户密码
     * @param session HttpSession 对象，用于保存用户登录信息
     * @return Result 登录结果
     */
    Result login(String email, String password, HttpSession session);
}
