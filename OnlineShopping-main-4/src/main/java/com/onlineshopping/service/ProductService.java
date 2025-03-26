package com.onlineshopping.service;

import com.onlineshopping.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    /**
     * 用于关键字查询商品
     * @param keyword
     * @return
     */
    List<ProductDTO> getProductsByKeyword(String keyword);


    /**
     * 根据分类查询商品
     * @param categoryId
     * @return
     */
    List<ProductDTO> getProductsByCategory(Integer categoryId);


    /**
     * 查询商品具体信息
     * @param productId
     * @return
     */
    ProductDTO getProductById(Integer productId);
}
