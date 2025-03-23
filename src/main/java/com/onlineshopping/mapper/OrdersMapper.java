package com.onlineshopping.mapper;


import com.onlineshopping.dto.orderDTO;
import com.onlineshopping.dto.orderInfo;
import com.onlineshopping.dto.orderItemsDTO;
import org.apache.ibatis.annotations.*;

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

    /**
     * 根据订单ID查订单详情
     * @param orderId
     * @return
     */
    @Select("select o.order_id, o.total_amount, o.status, o.created_at, o.updated_at " +
            "from orders o " +
            "WHERE o.order_id = #{orderId}")
    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "products", column = "order_id",
                    many = @Many(select = "getOrderItemsByOrderId"))
    })
    orderInfo getOrderById(int orderId);


    /**
     * 根据订单ID查订单详情中的商品信息
     * @param orderId
     * @return
     */
    @Select("SELECT p.name AS name, oi.quantity, oi.price, p.seller_id, u.username AS sellerName " +
            "FROM order_items oi " +
            "JOIN products p ON oi.product_id = p.product_id " +
            "JOIN users u ON p.seller_id = u.user_id " +
            "WHERE oi.order_id = #{orderId}")
    List<orderItemsDTO> getOrderItemsByOrderId(int orderId);
}
