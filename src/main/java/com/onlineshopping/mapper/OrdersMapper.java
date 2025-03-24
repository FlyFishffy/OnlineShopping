package com.onlineshopping.mapper;


import com.onlineshopping.dto.orderDTO;
import com.onlineshopping.dto.orderInfo;
import com.onlineshopping.dto.orderItemsDTO;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Select("select o.order_id, o.buyer_id, o.total_amount, o.status, o.created_at, o.updated_at, o.address_id " +
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
    @Select("SELECT p.name AS name, oi.product_id, oi.quantity, oi.price, p.seller_id, u.username AS sellerName " +
            "FROM order_items oi " +
            "JOIN products p ON oi.product_id = p.product_id " +
            "JOIN users u ON p.seller_id = u.user_id " +
            "WHERE oi.order_id = #{orderId}")
    List<orderItemsDTO> getOrderItemsByOrderId(int orderId);


    /**
     * 创建订单
     * @param buyerId
     * @param totalAmount
     * @param status
     * @param createdAt
     * @param updatedAt
     * @param addressId
     * @return
     */
    @Insert("INSERT INTO orders (buyer_id, total_amount, status, created_at, updated_at, address_id) " +
            "VALUES (#{buyerId}, #{totalAmount}, #{status}, #{createdAt}, #{updatedAt}, #{addressId})")
    void createOrder(
            @Param("buyerId")int buyerId,
            @Param("totalAmount")BigDecimal totalAmount,
            @Param("status")String status,
            @Param("createdAt")LocalDateTime createdAt,
            @Param("updatedAt")LocalDateTime updatedAt,
            @Param("addressId")int addressId);

    /**
     * 获取创建的订单id
     * @return
     */
    @Select("SELECT MAX(order_id) FROM orders " +
            "WHERE orders.buyer_id = #{buyerId}")
    int getLastOrderId(int buyerId);


    /**
     * 创建订单内的订单项
     * @param orderId
     * @param productId
     * @param quantity
     * @param productTotal
     */
    @Insert("INSERT INTO order_items (order_id, product_id, quantity, price) " +
            "VALUES (#{orderId}, #{productId}, #{quantity}, #{productTotal})")
    void createorderItems(
            @Param("orderId")int orderId,
            @Param("productId")int productId,
            @Param("quantity")int quantity,
            @Param("productTotal")BigDecimal productTotal
    );
}
