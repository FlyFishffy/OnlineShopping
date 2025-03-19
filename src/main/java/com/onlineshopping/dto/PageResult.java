package com.onlineshopping.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: FlyF1sh
 * @CreateTime: 2025-03-19
 * 用于返回分页查询结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    /**
     * 总记录数
     */
    private int total;

    /**
     * 当前页数据集合
     */
    private List records;
}
