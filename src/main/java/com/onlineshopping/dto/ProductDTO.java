package com.onlineshopping.dto;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: FlyF1sh
 * @CreateTime: 2025-03-19
 */
@Data
public class ProductDTO {
    private Integer sellerId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer categoryId;
}
