package com.alex.controller;

import com.alex.blog.entity.ChatUser;
import com.alex.blog.service.ChatUserPersistenceService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ChatUserController
{
    @Autowired
    private ChatUserPersistenceService userPersistenceService;

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

    @PostMapping("/registerUser")
    @ResponseBody
    public Map<String, Object> registerUser(ChatUser chatUser)
    {
        Map<String, Object> resultMap = Maps.newHashMap();
        Integer result = userPersistenceService.registerChatUser(chatUser);
        resultMap.put("status", result);

        return resultMap;
    }

    @PostMapping("/userLogin")
    @ResponseBody
    public Map<String, Object> loginChat(ChatUser user)
    {
        Map<String, Object> resultMap = Maps.newHashMap();

        return resultMap;
    }
}
