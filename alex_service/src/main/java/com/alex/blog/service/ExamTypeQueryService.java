package com.alex.blog.service;

import com.alex.blog.dao.TkExamTypeDao;
import com.alex.blog.entity.TkExamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamTypeQueryService
{
    @Autowired
    private TkExamTypeDao examTypeDao;

    /**
     * @return the list of exam type
     * @description get exam type list
     * @author Alex
     * @date 2018.06.06 09:48
     */
    @Cacheable(value = "selectAllExamType")
    public List<TkExamType> selectAllExamType()
    {
        System.out.println("not search database");
        return examTypeDao.selectAllExamType();
    }
}
