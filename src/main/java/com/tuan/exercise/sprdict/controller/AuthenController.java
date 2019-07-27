package com.tuan.exercise.sprdict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuan.exercise.sprdict.dao.AccountDao;

@Controller
public class AuthenController {

    private static final String REDIRECT_INDEX = "redirect:/";

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/login")
    public String getLoginForm(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object roleObj = session.getAttribute("role");
        
        if (roleObj != null) {
            return REDIRECT_INDEX;
        }
        
        return "authen";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam(name = "uname") String username, 
            @RequestParam(name = "pwd") String password,
            HttpServletRequest req) {

        HttpSession session = req.getSession();
        Object roleObj = session.getAttribute("role");

        if (roleObj == null) {
            String role = accountDao.isAccountValid(username, password);
            
            if (role == null) {
                return "authen";
            }
            session.setAttribute("role", role);
        }

        return REDIRECT_INDEX;
    }
    
    @GetMapping("/logout")
    public String doLogout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object roleObj = session.getAttribute("role");
        if (roleObj != null) {
            session.removeAttribute("role");
        }

        return "redirect:/login";
    }
}
