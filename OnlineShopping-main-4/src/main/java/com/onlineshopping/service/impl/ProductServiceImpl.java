package com.onlineshopping.service.impl;

import com.onlineshopping.dto.ProductDTO;
import com.onlineshopping.mapper.ProductMapper;
import com.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    /**
     * 用于关键字查询商品
     * @param keyword
     * @return
     */
    @Override
    public List<ProductDTO> getProductsByKeyword(String keyword) {
        return productMapper.getProductsByKeyword(keyword);
    }


    /**
     * 根据分类查询商品
     * @param categoryId
     * @return
     */
    @Override
    public List<ProductDTO> getProductsByCategory(Integer categoryId) {
        return productMapper.getProductsByCategory(categoryId);
    }


    /**
     * 查询商品具体信息
     * @param productId
     * @return
     */
    @Override
    public ProductDTO getProductById(Integer productId) {
        return productMapper.getProductById(productId);
    }
}
