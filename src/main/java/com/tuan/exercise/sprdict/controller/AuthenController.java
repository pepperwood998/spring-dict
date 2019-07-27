package com.tuan.exercise.sprdict.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuan.exercise.sprdict.constant.Page;
import com.tuan.exercise.sprdict.dao.AccountDao;

@Controller
public class AuthenController {

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/login")
    public String getLoginForm() {
        return Page.AUTHEN;
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam(name = "uname") String username, 
            @RequestParam(name = "pwd") String password,
            HttpServletRequest req) {

        String role = accountDao.isAccountValid(username, password);
        req.getSession().setAttribute("role", role);

        return Page.Direct.getRedirect("/", null);
    }

    @GetMapping("/logout")
    public String doLogout(HttpServletRequest req) {
        req.getSession().removeAttribute("role");

        return Page.Direct.getRedirect("/login", null);
    }
}
