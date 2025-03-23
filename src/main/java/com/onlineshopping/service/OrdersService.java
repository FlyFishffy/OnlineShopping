package com.onlineshopping.service;

import com.onlineshopping.dto.orderDTO;

import java.util.List;

public interface OrdersService {
    List<orderDTO> getOrdersByUser(int userId);
}
