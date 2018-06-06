package com.alex.blog.dto;

public class TkExamTypeQueryCond extends BaseQueryCond
{
    private Integer id;
    private Integer fid;
    private String pidId;
    private String name;
    private Integer isType;
    private Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getFid()
    {
        return fid;
    }

    public void setFid(Integer fid)
    {
        this.fid = fid;
    }

    public String getPidId()
    {
        return pidId;
    }

    public void setPidId(String pidId)
    {
        this.pidId = pidId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getIsType()
    {
        return isType;
    }

    public void setIsType(Integer isType)
    {
        this.isType = isType;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
}
