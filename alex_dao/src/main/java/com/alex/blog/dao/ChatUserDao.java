package com.alex.blog.dao;

import com.alex.blog.entity.ChatUser;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserDao
{
    Integer registerUser(ChatUser user);
}
