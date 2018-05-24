package com.alex.blog.service;

import com.alex.blog.dao.TkConfigDao;
import com.alex.blog.dto.TkConfigQueryCond;
import com.alex.blog.entity.TkConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService
{
    @Autowired
    private TkConfigDao tkConfigDao;

    /**
     * @return 系统配置集合
     * @description 查询所有启用的前台栏目
     * @author Xu Shiqi
     * @date 2018.05.08 17:44
     */
    @Cacheable(key = "'tkConfig.selectAllEnabledColumn'", value = "tkConfig")
    public List<TkConfig> selectAllEnabledColumn()
    {
        return tkConfigDao.selectAllEnabledColumn();
    }

    /**
     * @param tkConfigQueryCond 查询条件
     * @return 地区数量
     * @description 查询地区/年份数量
     * @author Xu Shiqi
     * @date 2018.05.10 17:35
     */
    @Cacheable(key = "'tkConfig.electConfigCount'+#tkConfigQueryCond", value = "tkConfig")
    public Integer selectConfigCount(TkConfigQueryCond tkConfigQueryCond)
    {
        return tkConfigDao.selectConfigCount(tkConfigQueryCond);
    }

    /**
     * @param tkConfigQueryCond 查询条件
     * @return 地区集合
     * @description 查询所有地区/年份
     * @author Xu Shiqi
     * @date 2018.05.10
     */
    @Cacheable(key = "'tkConfig.selectAllConfig'+#tkConfigQueryCond", value = "tkConfig")
    public List<TkConfig> selectAllConfig(TkConfigQueryCond tkConfigQueryCond)
    {
        return tkConfigDao.selectAllConfig(tkConfigQueryCond);
    }

    /**
     * @param id 要查询的配置信息ID
     * @return 要查询的配置信息
     * @description 根据ID查询相应配置信息
     * @author Xu Shiqi
     * @date 2018.05.11 15:41
     */
    @Cacheable(key = "'tkConfig.selectConfigInfoById'+#id", value = "tkConfig")
    public TkConfig selectConfigInfoById(Integer id)
    {
        return tkConfigDao.selectConfigInfoById(id);
    }


    /**
     * 查询所有分类
     *
     * @return : java.util.List<com.offcn.tiku.entity.TkConfig>
     * @author:hushihai
     * @date:14:42 2018/5/14
     * @params:[]
     */
    @Cacheable(value = "tkConfig", key = "'tkConfig.selectAllConfigs'")
    public List<TkConfig> selectAllConfigs()
    {
        return tkConfigDao.selectAllConfigs();
    }

    /**
     * @param
     * @return
     * @description 查询所有启用的地区
     * @author liyingnan
     * @date 2018/5/14 20:53
     */
    @Cacheable(key = "'tkConfig.selectAllArea'", value = "tkConfig")
    public List<TkConfig> selectAllArea()
    {
        return tkConfigDao.selectAllArea();
    }

    /**
     * @param
     * @return
     * @description 查询所有启用的地区
     * @author liyingnan
     * @date 2018/5/14 20:53
     */
    @Cacheable(key = "'tkConfig.selectAllYear'", value = "tkConfig")
    public List<TkConfig> selectAllYear()
    {
        return tkConfigDao.selectAllYear();
    }

    
}
