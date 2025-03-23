package com.onlineshopping.dto;

import com.onlineshopping.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class orderInfo {
    private int orderId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<orderItemsDTO> products;

}
