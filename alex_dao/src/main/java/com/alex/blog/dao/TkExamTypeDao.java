package com.alex.blog.dao;

import com.alex.blog.entity.TkExamType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TkExamTypeDao
{
    List<TkExamType> selectAllExamType();
}
