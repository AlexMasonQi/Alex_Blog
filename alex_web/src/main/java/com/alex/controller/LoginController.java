package com.alex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
    @GetMapping("/login")
    public String showLoginPage()
    {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage()
    {
        return "register";
    }

    @GetMapping("/index")
    public String showMainPage()
    {
        return "index";
    }
}
