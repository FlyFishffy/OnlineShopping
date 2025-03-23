package com.onlineshopping.service.impl;

import com.onlineshopping.dto.ProductDTO;
import com.onlineshopping.entity.Order;
import com.onlineshopping.entity.UserBehavior;
import com.onlineshopping.mapper.OrderMapper;
import com.onlineshopping.mapper.ProductMapper;
import com.onlineshopping.mapper.UserBehaviorMapper;
import com.onlineshopping.service.UserAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAnalysisServiceImpl implements UserAnalysisService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserBehaviorMapper userBehaviorMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Order> getUserOrders(Integer userId) {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    public List<UserBehavior> getUserBehaviors(Integer userId) {
        return userBehaviorMapper.getUserBehaviorsByUserId(userId);
    }

    @Override
    public List<ProductDTO> getPersonalizedProducts(Integer userId) {
        // 简单示例：根据用户的历史订单和行为，推荐相同分类的商品
        List<Order> orders = getUserOrders(userId);
        List<UserBehavior> behaviors = getUserBehaviors(userId);
        List<Integer> categoryIds = new ArrayList<>();

        // 从订单和行为中提取商品分类 ID
        for (Order order : orders) {
            ProductDTO product = productMapper.getProductById(order.getProductId());
            if (product != null && !categoryIds.contains(product.getCategoryId())) {
                categoryIds.add(product.getCategoryId());
            }
        }
        for (UserBehavior behavior : behaviors) {
            ProductDTO product = productMapper.getProductById(behavior.getProductId());
            if (product != null && !categoryIds.contains(product.getCategoryId())) {
                categoryIds.add(product.getCategoryId());
            }
        }

        // 根据分类 ID 查询商品
        List<ProductDTO> personalizedProducts = new ArrayList<>();
        for (Integer categoryId : categoryIds) {
            personalizedProducts.addAll(productMapper.getProductsByCategory(categoryId));
        }

        return personalizedProducts;
    }
}