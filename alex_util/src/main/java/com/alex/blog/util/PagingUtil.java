package com.alex.blog.util;

import com.alex.blog.config.Global;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0.1
 * @description JDK 8+ Stream API集合分页工具类
 * @date 2018.06.04 18:03
 */
public class PagingUtil<T>
{
    /**
     * 总页数
     */
    private int totalPage = 0;

    /**
     * 当前是第几页
     */
    private int curPageNo = 0;

    /**
     * 每页的大小
     */
    private int pageSize = Global.pageSize;

    /**
     * 每页默认大小
     */
    private static final int DEFAULT_PAGE_SIZE = 500;


    private List<T> pageData = null;

    public PagingUtil(List<T> pageResult, int pageSize)
    {
        this.pageSize = pageSize;
        this.pageData = pageResult;
        init(pageResult, pageSize);
    }


    public PagingUtil(List<T> pageResult)
    {
        this(pageResult, DEFAULT_PAGE_SIZE);
    }


    private void init(List<T> pageResult, int pageSize)
    {
        if (pageSize <= 0)
        {
            throw new IllegalArgumentException("Paging size must be greater than zero.");
        }
        if (null == pageResult)
        {
            throw new NullPointerException("Paging resource list must be not null.");
        }
        if (pageResult.size() % pageSize > 0)
        {
            this.totalPage = (pageResult.size() / pageSize) + 1;
        }
        else
        {
            this.totalPage = pageResult.size() / pageSize;
        }
    }

    /**
     * 返回当前剩余页数
     *
     * @return
     */
    private int getSurplusPage()
    {
        if (pageData.size() % pageSize > 0)
        {
            return (pageData.size() / pageSize) + 1;
        }
        else
        {
            return pageData.size() / pageSize;
        }

    }

    /**
     * 返回是否还有下一页数据
     *
     * @return
     */
    public boolean hasNext()
    {
        return pageData.size() > 0;
    }

    /**
     * 获取分页后，总的页数
     *
     * @return
     */
    public int getTotalPage()
    {
        return totalPage;
    }

    public List<T> next()
    {
        List<T> pagingData = pageData.stream().limit(pageSize).collect(Collectors.toList());
        pageData = pageData.stream().skip(pageSize).collect(Collectors.toList());
        return pagingData;
    }

    /**
     * 返回当前页数
     *
     * @return
     */
    public int getCurPageNo()
    {
        return totalPage - getSurplusPage();
    }
}
