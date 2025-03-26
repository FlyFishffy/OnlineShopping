package com.onlineshopping.mapper;

import com.onlineshopping.entity.Seller;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 卖家数据访问层接口，用于操作数据库中卖家相关数据
 */
@Mapper
public interface SellerMapper {

    /**
     * 根据邮箱查询卖家信息
     * @param email 卖家邮箱
     * @return Seller 卖家对象
     */
    @Select("SELECT * FROM users WHERE email = #{email} AND role = 'seller'")
    Seller findByEmail(String email);

    /**
     * 插入新卖家信息
     * @param seller 卖家实体
     */
    @Insert("INSERT INTO users (username, password, email, role, created_at, updated_at) " +
            "VALUES (#{username}, #{password}, #{email}, 'seller', #{createdAt}, #{updatedAt})")
    void insertSeller(Seller seller);
}
