package com.onlineshopping.controller.buyer;

import com.onlineshopping.dto.UserDTO;
import com.onlineshopping.entity.User;
import com.onlineshopping.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("/createAccount")
    public ResponseEntity<Boolean> createAccount(@RequestBody User user) {
        boolean result = userService.createAccount(user);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        UserDTO userDTO = userService.login(user);
        if (userDTO!= null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<Boolean> updateProfile(@RequestBody User user) {
        // 可以在这里添加原密码验证逻辑，若验证失败，直接返回错误响应
        boolean result = userService.updateProfile(user);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}