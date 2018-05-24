package com.alex.controller;

import com.alex.blog.dto.TkConfigQueryCond;
import com.alex.blog.entity.TkConfig;
import com.alex.blog.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private TestService testService;

    @RequestMapping("/testPagination")
    public String test(Map model)
    {
        return "test/testPagination";
    }

    @RequestMapping("/showConfigList")
    public String showConfigList(TkConfigQueryCond tkConfigQueryCond, Map model)
    {
        tkConfigQueryCond.setStatus(0);
        Integer totalCount = testService.selectConfigCount(tkConfigQueryCond);
        model.put("totalCount", totalCount);

        if (totalCount > 0)
        {
            List<TkConfig> configList = testService.selectAllConfig(tkConfigQueryCond);
            model.put("configList", configList);
        }

        return "test/testPagination";
    }

    @RequestMapping("/showConfigPagination")
    public String showConfigPagination(TkConfigQueryCond tkConfigQueryCond, Map model)
    {
        Integer totalCount = 1;
        if ("Y".equals(tkConfigQueryCond.getbInit()))
        {
            totalCount = testService.selectConfigCount(tkConfigQueryCond);
            model.put("totalCount", totalCount);
        }
        if (totalCount > 0)
        {
            List<TkConfig> configList = testService.selectAllConfig(tkConfigQueryCond);
            model.put("configList", configList);
        }

        return "test/page/testPaginationTable";
    }
}