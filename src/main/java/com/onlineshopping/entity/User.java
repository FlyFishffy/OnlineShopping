package com.onlineshopping.entity;


import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private int id;


    /**
     * 用户名，默认是随机字符
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;


    /**
     * 密码，加密存储
     */
    private String password;




    /**
     * 用户角色private String role;
     */



    /**
     * 创建时间private LocalDateTime createTime;
     */
    private LocalDateTime createTime;


    /**
     * 更新时间private LocalDateTime updateTime;
     */
    private LocalDateTime updateTime;


}