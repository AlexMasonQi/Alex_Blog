package com.alex.blog.dao;


import com.alex.blog.dto.TkConfigQueryCond;
import com.alex.blog.entity.TkConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TkConfigDao
{
    List<TkConfig> selectAllEnabledColumn();

    Integer selectConfigCount(TkConfigQueryCond tkConfigQueryCond);

    List<TkConfig> selectAllConfig(TkConfigQueryCond tkConfigQueryCond);

    Integer addConfigInfo(TkConfig tkConfig);

    Integer updateConfigInfoById(TkConfig tkConfig);

    Integer batchStartStatusByIds(@Param("list") List list);

    Integer batchForbidStatusByIds(@Param("list") List list);

    TkConfig selectConfigInfoById(@Param("id") Integer id);

    List<TkConfig> selectConfigsByIds(@Param("list") List<Integer> areaIdList);

    List<TkConfig> selectAllConfigs();

    List<TkConfig> selectAllArea();

    List<TkConfig> selectAllYear();
}
