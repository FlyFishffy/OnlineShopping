package com.onlineshopping.service.impl;

import com.onlineshopping.dto.orderDTO;
import com.onlineshopping.mapper.OrdersMapper;
import com.onlineshopping.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<orderDTO> getOrdersByUser(int userId) {
        return ordersMapper.getOrdersByUser(userId);
    }
}
