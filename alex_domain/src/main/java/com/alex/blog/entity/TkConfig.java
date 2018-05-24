package com.alex.blog.entity;

import java.io.Serializable;

public class TkConfig implements Serializable
{
    private Integer id;
    private Integer type;
    private String name;
    private Integer pid;
    private String subjectName;
    private String keys;
    private String values;
    private String image;
    private String listorder;
    private String addUser;
    private String addTime;
    private Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getPid()
    {
        return pid;
    }

    public void setPid(Integer pid)
    {
        this.pid = pid;
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    public String getKeys()
    {
        return keys;
    }

    public void setKeys(String keys)
    {
        this.keys = keys;
    }

    public String getValues()
    {
        return values;
    }

    public void setValues(String values)
    {
        this.values = values;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getListorder()
    {
        return listorder;
    }

    public void setListorder(String listorder)
    {
        this.listorder = listorder;
    }

    public String getAddUser()
    {
        return addUser;
    }

    public void setAddUser(String addUser)
    {
        this.addUser = addUser;
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

    @Override
    public String toString()
    {
        return "TkConfig{" + "id=" + id + ", type=" + type + ", name='" + name + '\'' + ", pid=" + pid + ", keys='" + keys + '\'' + ", values='" + values + '\'' + ", image='" + image + '\'' + ", listorder='" + listorder + '\'' + ", addUser='" + addUser + '\'' + ", addTime='" + addTime + '\'' + ", status=" + status + '}';
    }
}
