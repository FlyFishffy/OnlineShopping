package com.onlineshopping.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 卖家实体类，用于存储卖家基本信息
 */
@Data
public class Seller {
    private Integer sellerId; // 卖家唯一标识
    private String username;  // 卖家用户名
    private String password;  // 密码（需加密存储）
    private String email;     // 电子邮件
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
}
