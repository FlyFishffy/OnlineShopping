package com.onlineshopping.service.impl;

import com.onlineshopping.dto.Result;
import com.onlineshopping.dto.orderDTO;
import com.onlineshopping.dto.orderInfo;
import com.onlineshopping.dto.orderItemsDTO;
import com.onlineshopping.mapper.OrdersMapper;
import com.onlineshopping.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<orderDTO> getOrdersByUser(int userId) {
        return ordersMapper.getOrdersByUser(userId);
    }

    @Override
    public orderInfo getOrderById(int orderId) {

        return ordersMapper.getOrderById(orderId);
    }

    @Override
    public List<orderItemsDTO> getOrderItemsById(int orderId) {
        List<orderItemsDTO> orderItems = ordersMapper.getOrderItemsByOrderId(orderId);
        return orderItems;
    }
}
