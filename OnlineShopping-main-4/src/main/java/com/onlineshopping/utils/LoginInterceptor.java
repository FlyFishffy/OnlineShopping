package com.onlineshopping.utils;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// 实现HandlerInterceptor接口，用于拦截请求并进行登录验证
public class LoginInterceptor implements HandlerInterceptor {

    // 定义无需登录即可访问的URL列表
    private static final List<String> PERMITTED_URLS = Arrays.asList(
            "/user/createAccount",
            "/user/login"
    );

    // 在请求处理之前执行的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestUri = request.getRequestURI();
        // 检查当前请求的URI是否在无需登录的URL列表中
        if (PERMITTED_URLS.contains(requestUri)) {
            return true;
        }

        // 从UserHolder中获取当前用户信息
        Object user = UserHolder.getUser();
        if (user == null) {
            // 如果用户未登录，重定向到登录页面
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}