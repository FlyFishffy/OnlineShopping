package com.onlineshopping.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("Customer_Support_Table")
public class SupportRequest implements Serializable {
    @TableId(value = "support_id", type = IdType.AUTO)
    private Long supportId;

    private Long userId;
    private String subject;
    private String message;

    @EnumValue
    private SupportStatus status; // 枚举类

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;
}
