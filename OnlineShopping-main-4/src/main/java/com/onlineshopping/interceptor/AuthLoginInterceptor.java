package com.onlineshopping.interceptor;

import com.onlineshopping.dto.UserDTO;
import com.onlineshopping.utils.UserHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: FlyF1sh
 * @CreateTime: 2025-03-25
 */
@Component
public class AuthLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取 Session 对象
        HttpSession session = request.getSession();

        // 校验用户是否登录
        if (session == null || session.getAttribute("user") == null) {
            // 用户未登录，返回 401 未授权状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("未登录，请先登录");
            return false;
        }

        // 校验用户权限
        Object role = session.getAttribute("role");
        if (role == null || (!role.equals("buyer") && !role.equals("seller") && !role.equals("admin"))) {
            // 用户权限不合法，返回 403 禁止访问状态码
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("权限不足，禁止访问");
            return false;
        }

        // 校验通过，继续执行后续的过滤链
        Object user = session.getAttribute("user");
        UserHolder.saveUser((UserDTO) user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }

}
