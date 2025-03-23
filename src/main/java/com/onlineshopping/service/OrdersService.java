package com.onlineshopping.service;

import com.onlineshopping.dto.orderDTO;
import com.onlineshopping.dto.orderInfo;
import com.onlineshopping.dto.orderItemsDTO;

import java.util.List;

public interface OrdersService {
    List<orderDTO> getOrdersByUser(int userId);

    orderInfo getOrderById(int orderId);

    List<orderItemsDTO> getOrderItemsById(int orderId);
}
