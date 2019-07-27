package com.tuan.exercise.sprdict.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        Object roleObj = session.getAttribute("role");

        boolean isLoggedIn = roleObj != null;
        if (isLoggedIn) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        return true;
    }
}
