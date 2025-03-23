package com.onlineshopping.dto;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class orderItemsDTO {
    private String name;
    private int quantity;
    private BigDecimal price;

    private int sellerId;
    private String sellerName;
}
