package com.onlineshopping.controller.buyer;

import com.onlineshopping.dto.ProductDTO;
import com.onlineshopping.entity.Order;
import com.onlineshopping.entity.UserBehavior;
import com.onlineshopping.service.UserAnalysisService;
import com.onlineshopping.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userAnalysis")
@Api(tags = "用户消费行为分析和个性化推荐接口")
public class UserAnalysisController {
    @Autowired
    private UserAnalysisService userAnalysisService;

    @GetMapping("/getUserOrders/{userId}")
    @ApiOperation("获取用户的订单信息")
    public Result getUserOrders(@PathVariable Integer userId) {
        List<Order> orders = userAnalysisService.getUserOrders(userId);
        return Result.ok(orders);
    }

    @GetMapping("/getUserBehaviors/{userId}")
    @ApiOperation("获取用户的行为信息")
    public Result getUserBehaviors(@PathVariable Integer userId) {
        List<UserBehavior> behaviors = userAnalysisService.getUserBehaviors(userId);
        return Result.ok(behaviors);
    }

    @GetMapping("/getPersonalizedProducts/{userId}")
    @ApiOperation("获取个性化商品推荐")
    public Result getPersonalizedProducts(@PathVariable Integer userId) {
        List<ProductDTO> products = userAnalysisService.getPersonalizedProducts(userId);
        return Result.ok(products);
    }
}
