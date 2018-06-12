package com.alex.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Service
public class RabbitSendUtil implements RabbitTemplate.ConfirmCallback
{
    private static Logger logger = LoggerFactory.getLogger(RabbitSendUtil.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String err)
    {
        logger.info("confirm ID: " + correlationData.getId());
        if (ack)
        {
            logger.info("send message successfull!");
        }
        else
        {
            logger.error("error send message, error info: " + err);
        }
    }

    /**
     * @description send message
     * @author Alex
     * @date 2018.06.12 11:06
     */
    public void sendRabbitDirect(Object obj)
    {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(obj);
    }
}
