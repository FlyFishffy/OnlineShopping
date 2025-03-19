package com.onlineshopping.controller.buyer;


import com.onlineshopping.dto.ProductDTO;
import com.onlineshopping.dto.Result;
import com.onlineshopping.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用于商品信息展示
 */
@RestController
@RequestMapping("/product")
@Api(tags = "商品信息相关接口")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * 用于关键字查询商品
     * @param keyword
     * @return
     */
    @GetMapping("/searchByKeyword/{keyword}")
    @ApiOperation("关键字查询商品")
    public Result searchProductsByKeyword(@PathVariable String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Result.fail("keyword is empty");
        }

        log.info("关键字查询商品: {}", keyword);

        List<ProductDTO> productDTOs = productService.getProductsByKeyword(keyword);
        return Result.ok(productDTOs);
    }


    /**
     * 根据分类查询商品
     * @param categoryId
     * @return
     */
    @GetMapping("/searchByCategory/{categoryId}")
    @ApiOperation("根据分类查询商品")
    public Result searchProductByCategory(@PathVariable Integer categoryId) {
        if (categoryId < 0) {
            return Result.fail("categoryId error");
        }

        log.info("根据类别查询商品: {}", categoryId);

        List<ProductDTO> productDTOs = productService.getProductsByCategory(categoryId);
        return Result.ok(productDTOs);
    }


    /**
     * 查询商品具体信息
     * @param productId
     * @return
     */
    @GetMapping("/get/{productId}")
    @ApiOperation("查询商品具体信息")
    public Result getProductById(@PathVariable Integer productId) {
        if (productId < 0) {
            Result.fail("productId error");
        }

        log.info("查询商品: {}", productId);
        ProductDTO productDTO = productService.getProductById(productId);
        return Result.ok(productDTO);
    }
}
