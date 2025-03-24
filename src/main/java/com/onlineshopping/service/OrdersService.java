package com.onlineshopping.service;

import com.onlineshopping.dto.orderDTO;
import com.onlineshopping.dto.orderInfo;
import com.onlineshopping.dto.orderItemsDTO;
import com.onlineshopping.entity.Product;

import java.util.List;

public interface OrdersService {
    List<orderDTO> getOrdersByUser(int userId);

    orderInfo getOrderById(int orderId);

    List<orderItemsDTO> getOrderItemsById(int orderId);

    orderInfo createOrder(orderInfo orderinforequest);
}
