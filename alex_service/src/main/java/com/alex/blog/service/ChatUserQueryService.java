package com.alex.blog.service;

import com.alex.blog.dao.ChatUserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatUserQueryService
{
    @Autowired
    private ChatUserDao chatUserDao;

    @Test
    public void test()
    {
        String[] uuidStr = UUID.randomUUID().toString().split("-");
        String uuid = "";
        for (String s : uuidStr)
        {
            uuid += s;
        }
        System.out.println(uuid);
    }
}
