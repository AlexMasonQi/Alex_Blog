package com.alex.blog.service;

import com.alex.blog.dao.ChatUserDao;
import com.alex.blog.entity.ChatUser;
import com.alex.blog.util.DateTimeUtil;
import com.alex.blog.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ChatUserPersistenceService
{
    @Autowired
    private ChatUserDao userDao;

    /**
     * @description register a user
     * @author Alex
     * @date 2018.06.14 15:51
     */
    public Integer registerChatUser(ChatUser user)
    {
        String[] uuidStr = UUID.randomUUID().toString().split("-");
        String uuid = "";
        for (String s : uuidStr)
        {
            uuid += s;
        }
        String salt = uuid.substring(0, 5);
        user.setSalt(salt);
        user.setPassword(EncryptUtil.getMd5(user.getPassword() + salt));
        user.setAddTime(DateTimeUtil.getCurrentDateTime());
        user.setStatus(1);

        return userDao.registerUser(user);
    }
}
