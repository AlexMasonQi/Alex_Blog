package com.alex.controller;

import com.alex.blog.entity.TkExamType;
import com.alex.blog.service.ExamTypeQueryService;
import com.alex.blog.util.RabbitSendUtil;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private ExamTypeQueryService examTypeQueryService;

    @Autowired
    private RabbitSendUtil sendUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/showExamTypeList")
    public String showExamTypeList(Map model)
    {
        List<TkExamType> examTypeList = examTypeQueryService.selectAllExamType();
        model.put("list", examTypeList);

        return "test/testRedis";
    }

    @RequestMapping("/showCalendar")
    public String showTestCalendar()
    {
        return "test/myCalendar";
    }

    @RequestMapping("/showTestMQ")
    public String showMQPage()
    {
        return "test/testMQ";
    }

    @RequestMapping("/sendMQMessage")
    @ResponseBody
    public Map<String, Object> sendMessage(String msg)
    {
        Map<String, Object> resultMap = Maps.newHashMap();
        try
        {
            sendUtil.sendRabbitDirect(msg);
            resultMap.put("stat", "OK");
        }
        catch (Exception e)
        {
            logger.error("error send message \"" + msg + "\"!", e);
            resultMap.put("stat", "error");
        }

        return resultMap;
    }
}
