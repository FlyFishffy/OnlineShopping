package com.onlineshopping.mapper;


import com.onlineshopping.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 用于关键字查询商品
     * @param keyword
     * @return
     */
    @Select("select (product_id, name, price) from products where name like concat('%', #{keyword}, '%')")
    List<ProductDTO> getProductsByKeyword(String keyword);


    /**
     * 根据分类查询商品
     * @param categoryId
     * @return
     */
    @Select("select (product_id, name, price) from products where category_id = #{categoryId}")
    List<ProductDTO> getProductsByCategory(Integer categoryId);


    /**
     * 查询商品具体信息
     * @param productId
     * @return
     */
    @Select("select seller_id, name, description, price, category_id from products where product_id = #{productId}")
    ProductDTO getProductById(Integer productId);
}
