package com.onlineshopping.mapper;


import com.onlineshopping.dto.orderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrdersMapper {

    /**
     * 根据用户ID查订单
     * @param userId
     * @return
     */
    @Select("select o.order_id, o.total_amount, o.created_at, GROUP_CONCAT(p.name SEPARATOR ', ') AS productNames " +
            "from orders o " +
            "JOIN order_items oi ON o.order_id = oi.order_id " +
            "JOIN products p ON oi.product_id = p.product_id " +
            "where o.buyer_id = #{userId} "+
            "GROUP BY o.order_id, o.total_amount, o.created_at")
    List<orderDTO> getOrdersByUser(int userId);
}
