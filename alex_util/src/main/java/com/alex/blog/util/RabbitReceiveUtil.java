package com.alex.blog.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Component
@Service
public class RabbitReceiveUtil
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @description receive message from queue
     * @author Alex
     * @date 2018.06.12 14:13
     */
    public String doReceiveString() throws UnsupportedEncodingException
    {
        Message message = rabbitTemplate.receive();
        return new String(message.getBody(), "utf-8");
    }
}
