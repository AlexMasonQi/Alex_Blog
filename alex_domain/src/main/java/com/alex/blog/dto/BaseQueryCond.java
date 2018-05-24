package com.alex.blog.dto;


public class BaseQueryCond
{
    //第几页
    private Integer pageIndex = 1;
    //每页显示多少条记录
    private Integer pageSize = 20;

    public String getbInit()
    {
        return bInit;
    }

    public void setbInit(String bInit)
    {
        this.bInit = bInit;
    }

    //是否初始化，第一次查询
    private String bInit;
    //分页偏移量
    private Integer offset;

    public Integer getOffset()
    {
        return (pageIndex - 1) * pageSize;
    }


    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    public Integer getPageIndex()
    {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
}
