package com.alex.controller;

import com.alex.blog.entity.TkExamType;
import com.alex.blog.service.ExamTypeQueryService;
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
    private ExamTypeQueryService examTypeQueryService;

    @RequestMapping("/showExamTypeList")
    public String showExamTypeList(Map model)
    {
        List<TkExamType> examTypeList = examTypeQueryService.selectAllExamType();
        model.put("list", examTypeList);

        return "test/testRedis";
    }
}
