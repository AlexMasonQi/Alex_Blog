package com.alex.blog.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xu Shiqi
 * @description 考试类型
 * @date 2018.04.26 13:42
 */
public class TkExamType implements Serializable
{
    private Integer id;
    private Integer fid;
    private String pidId;
    private String name;
    private String remark;
    private Integer sort;
    private Integer isContent;
    private Integer isType;
    private String columnId;
    private Integer level;
    private Integer node;
    private Integer required;
    private Integer knowRequired;
    private Integer areaRequired;
    private String area;
    private String addUsername;
    private String addTime;
    private Integer status;
    private String uniqueCode;
    private String jianpin;
    private Integer isty;
    private Integer isApp;
    private String category;
    private Integer ctType;
    private String wxSid;

    private List<TkExamType> nodes;
    //zTree标志位
    private Boolean isParent;

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

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getIsContent()
    {
        return isContent;
    }

    public void setIsContent(Integer isContent)
    {
        this.isContent = isContent;
    }

    public Integer getIsType()
    {
        return isType;
    }

    public void setIsType(Integer isType)
    {
        this.isType = isType;
    }

    public String getColumnId()
    {
        return columnId;
    }

    public void setColumnId(String columnId)
    {
        this.columnId = columnId;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getNode()
    {
        return node;
    }

    public void setNode(Integer node)
    {
        this.node = node;
    }

    public Integer getRequired()
    {
        return required;
    }

    public void setRequired(Integer required)
    {
        this.required = required;
    }

    public Integer getKnowRequired()
    {
        return knowRequired;
    }

    public void setKnowRequired(Integer knowRequired)
    {
        this.knowRequired = knowRequired;
    }

    public Integer getAreaRequired()
    {
        return areaRequired;
    }

    public void setAreaRequired(Integer areaRequired)
    {
        this.areaRequired = areaRequired;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getAddUsername()
    {
        return addUsername;
    }

    public void setAddUsername(String addUsername)
    {
        this.addUsername = addUsername;
    }

    public String getAddTime()
    {
        return addTime;
    }

    public void setAddTime(String addTime)
    {
        this.addTime = addTime;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getUniqueCode()
    {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode)
    {
        this.uniqueCode = uniqueCode;
    }

    public String getJianpin()
    {
        return jianpin;
    }

    public void setJianpin(String jianpin)
    {
        this.jianpin = jianpin;
    }

    public Integer getIsty()
    {
        return isty;
    }

    public void setIsty(Integer isty)
    {
        this.isty = isty;
    }

    public Integer getIsApp()
    {
        return isApp;
    }

    public void setIsApp(Integer isApp)
    {
        this.isApp = isApp;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Integer getCtType()
    {
        return ctType;
    }

    public void setCtType(Integer ctType)
    {
        this.ctType = ctType;
    }

    public String getWxSid()
    {
        return wxSid;
    }

    public void setWxSid(String wxSid)
    {
        this.wxSid = wxSid;
    }

    public List<TkExamType> getNodes()
    {
        return nodes;
    }

    public void setNodes(List<TkExamType> nodes)
    {
        this.nodes = nodes;
    }

    public Boolean getIsParent()
    {
        return isParent;
    }

    public void setIsParent(Boolean isParent)
    {
        this.isParent = isParent;
    }

    @Override
    public String toString()
    {
        return "TkExamType{" + "id=" + id + ", fid=" + fid + ", pidId='" + pidId + '\'' + ", name='" + name + '\'' + ", remark='" + remark + '\'' + ", isType=" + isType + ", columnId='" + columnId + '\'' + ", level=" + level + ", node=" + node + ", required=" + required + ", knowRequired=" + knowRequired + ", areaRequired=" + areaRequired + ", addUsername='" + addUsername + '\'' + ", addTime='" + addTime + '\'' + ", status=" + status + ", uniqueCode='" + uniqueCode + '\'' + ", jianpin='" + jianpin + '\'' + ", isty=" + isty + ", isApp=" + isApp + ", category='" + category + '\'' + ", ctType=" + ctType + ", isParent=" + isParent + '}';
    }
}
