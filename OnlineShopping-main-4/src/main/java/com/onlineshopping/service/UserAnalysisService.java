package com.onlineshopping.service;

import com.onlineshopping.dto.ProductDTO;
import com.onlineshopping.entity.Order;
import com.onlineshopping.entity.UserBehavior;

import java.util.List;

public interface UserAnalysisService {
    List<Order> getUserOrders(Integer userId);
    List<UserBehavior> getUserBehaviors(Integer userId);
    List<ProductDTO> getPersonalizedProducts(Integer userId);
}
