package com.onlineshopping.controller.buyer;

import com.onlineshopping.dto.LoginFormDTO;
import com.onlineshopping.dto.Result;
import com.onlineshopping.dto.UserDTO;
import com.onlineshopping.service.UserService;
import com.onlineshopping.utils.UserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "买家登录相关接口")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号、密码
     */
    @PostMapping("/login")
    @ApiOperation("买家登录接口")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session){
        // TODO 实现登录功能
        return userService.login(loginForm, session);
    }

    /**
     * 登出功能
     * @return 无
     */
    @PostMapping("/logout")
    public Result logout(){
        // TODO 实现登出功能
        return Result.fail("功能未完成");
    }

    @GetMapping("/me")
    public Result me(){
        // 获取当前登录的用户并返回
        UserDTO user = UserHolder.getUser();
        return Result.ok(user);
    }

}