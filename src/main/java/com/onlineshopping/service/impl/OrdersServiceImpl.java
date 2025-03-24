package com.onlineshopping.service.impl;

import com.onlineshopping.dto.Result;
import com.onlineshopping.dto.orderDTO;
import com.onlineshopping.dto.orderInfo;
import com.onlineshopping.dto.orderItemsDTO;
import com.onlineshopping.entity.Product;
import com.onlineshopping.mapper.OrdersMapper;
import com.onlineshopping.mapper.ProductMapper;
import com.onlineshopping.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 获取订单历史列表接口
     * @param userId
     * @return
     */
    @Override
    public List<orderDTO> getOrdersByUser(int userId) {
        return ordersMapper.getOrdersByUser(userId);
    }

    /**
     * 获取订单详情接口
     * @param orderId
     * @return
     */
    @Override
    public orderInfo getOrderById(int orderId) {
        return ordersMapper.getOrderById(orderId);
    }

    /**
     * 获取订单项
     * @param orderId
     * @return
     */
    @Override
    public List<orderItemsDTO> getOrderItemsById(int orderId) {
        List<orderItemsDTO> orderItems = ordersMapper.getOrderItemsByOrderId(orderId);
        return orderItems;
    }

    /**
     * 下单：创建订单和订单项
     * @param orderinforequest
     * @return
     * 创建订单接口
     */
    @Override
    @Transactional
    public orderInfo createOrder(orderInfo orderinforequest) {
        //orders
        //1.获取products计算total_amount
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (orderItemsDTO product : orderinforequest.getProducts()) {
            BigDecimal productTotal = productMapper.getProductById(product.getProductId()).getPrice()
                    .multiply(BigDecimal.valueOf(product.getQuantity()));
            System.out.println("Product: " + product.getName() + ", Total Amount: " + productTotal);
            
            // 累加到总金额
            totalAmount = totalAmount.add(productTotal);
        }

        //2.设置订单创建时间，更新时间
        int buyerId= orderinforequest.getBuyerId();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        int addressId = orderinforequest.getAddressId();
        //3. 插入订单
        ordersMapper.createOrder(
                buyerId,
                totalAmount,
                "PENDING", // 默认状态
                createdAt,
                updatedAt,
                addressId

        );

        int orderId = ordersMapper.getLastOrderId(buyerId);
        log.info("Order Id: " + orderId);


        //order_items
        for (orderItemsDTO product : orderinforequest.getProducts()){
            //遍历product插入order_items
            BigDecimal productTotal = productMapper.getProductById(product.getProductId()).getPrice()
                    .multiply(BigDecimal.valueOf(product.getQuantity()));
            ordersMapper.createorderItems(
                    orderId,
                    product.getProductId(),
                    product.getQuantity(),
                    productTotal

            );
        }
        //返回创建的订单
        orderInfo neworder = getOrderById(orderId);
        return neworder;
    }
}
