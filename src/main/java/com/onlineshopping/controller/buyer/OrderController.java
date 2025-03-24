package com.onlineshopping.controller.buyer;


import com.onlineshopping.dto.*;
import com.onlineshopping.entity.Product;
import com.onlineshopping.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Api(tags = "用户订单管理相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 用于下单
     * @param orderinforequest
     *int buyerId
     *int addressId
     *products:[
     * {
     *     int productId,
     *     int quantity
     * }
     * ]
     * @return
     * 创建订单接口
     */
    @PostMapping("/ordering")
    @ApiOperation("创建订单")
    public Result createOrder(@RequestBody  orderInfo orderinforequest) {
        //Todo
        orderInfo ordercreate = ordersService.createOrder(orderinforequest);
        return Result.ok(ordercreate);
    }
    /**
     * 获取订单历史列表接口
     * @param userId
     * @return
     */
    @GetMapping("/{userid}")
    @ApiOperation("历史订单")
    public Result getOrders(@PathVariable("userid") int userId) {
        log.info("根据用户id查询订单: {}", userId);
        List<orderDTO> orderDTOs = ordersService.getOrdersByUser(userId);
        log.info("orderDTOs: {}", orderDTOs);
        return Result.ok(orderDTOs);
    }

    /**
     * 获取订单详情接口
     * @param orderId
     * @return
     */
    @GetMapping("/info/{orderid}")
    @ApiOperation("查看订单详情")
    public Result orderView(@PathVariable("orderid") int orderId) {
        //Todo
        log.info("查询订单: {}", orderId);
        orderInfo orderinfo = ordersService.getOrderById(orderId);
        log.info("orderinfo: {}", orderinfo);
        return Result.ok(orderinfo);

    }
}
