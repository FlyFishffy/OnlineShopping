package com.onlineshopping.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class orderDTO {
    private Integer orderId;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    private String productNames;







}
