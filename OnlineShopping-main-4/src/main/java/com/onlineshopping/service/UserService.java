package com.onlineshopping.service;

import com.onlineshopping.dto.UserDTO;
import com.onlineshopping.entity.User;

public interface UserService {
    boolean createAccount(User user);
    UserDTO login(User user);
    boolean updateProfile(User user);
}