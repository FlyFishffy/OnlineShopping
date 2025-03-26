package com.onlineshopping.service.impl;

import com.onlineshopping.dto.UserDTO;
import com.onlineshopping.entity.User;
import com.onlineshopping.mapper.UserMapper;
import com.onlineshopping.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean createAccount(User user) {
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public UserDTO login(User user) {
        User dbUser = userMapper.selectUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (dbUser!= null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(dbUser.getUserId());
            userDTO.setUsername(dbUser.getUsername());
            userDTO.setEmail(dbUser.getEmail());
            userDTO.setRole(dbUser.getRole());
            return userDTO;
        }
        return null;
    }

    @Override
    public boolean updateProfile(User user) {
        user.setUpdatedAt(new Date());
        // 这里假设更新时只更新username、password、email，不更新role等其他字段
        User updatedUser = new User();
        updatedUser.setUserId(user.getUserId());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setEmail(user.getEmail());
        return userMapper.updateUser(updatedUser) > 0;
    }
}