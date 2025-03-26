package com.onlineshopping.service.impl;

import com.onlineshopping.dto.Result;
import com.onlineshopping.entity.Seller;
import com.onlineshopping.mapper.SellerMapper;
import com.onlineshopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * 卖家业务逻辑实现类
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper; // 注入数据访问层

    /**
     * 卖家注册功能实现
     * @param seller 卖家实体
     * @return Result 操作结果
     */
    @Override
    public Result register(Seller seller) {
        // 检查邮箱是否已存在
        Seller existingSeller = sellerMapper.findByEmail(seller.getEmail());
        if (existingSeller != null) {
            return Result.fail("该邮箱已被注册！");
        }
        
        // 设置创建时间和更新时间
        seller.setCreatedAt(LocalDateTime.now());
        seller.setUpdatedAt(LocalDateTime.now());
        
        // 保存卖家信息到数据库
        sellerMapper.insertSeller(seller);
        return Result.ok("注册成功！");
    }

    /**
     * 卖家登录功能实现
     * 登录成功后，将用户的邮箱、密码和角色保存到 session 中
     * @param email 邮箱
     * @param password 密码
     * @param session HttpSession 对象
     * @return Result 操作结果
     */
    @Override
    public Result login(String email, String password, HttpSession session) {
        // 根据邮箱查询卖家信息
        Seller seller = sellerMapper.findByEmail(email);
        if (seller == null || !seller.getPassword().equals(password)) {
            return Result.fail("邮箱或密码错误！");
        }

        // 登录成功后，将用户信息存入 session 中
        session.setAttribute("user", seller.getEmail());       // 保存用户邮箱
        session.setAttribute("password", seller.getPassword());  // 保存用户密码
        session.setAttribute("role", "seller");                  // 保存用户角色（这里写死为 seller，可根据需要调整）

        return Result.ok("登录成功！");
    }
}
