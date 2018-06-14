package com.alex.blog.entity;

import java.io.Serializable;

public class ChatUser implements Serializable
{
    private Integer id;

    private String userName;

    private String realName;

    private String email;

    private String password;

    private String salt;

    private Integer status;

    private String addTime;

    private String updateTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getAddTime()
    {
        return addTime;
    }

    public void setAddTime(String addTime)
    {
        this.addTime = addTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    @Override
    public String toString()
    {
        return "ChatUser{" + "id=" + id + ", userName='" + userName + '\'' + ", realName='" + realName + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", salt='" + salt + '\'' + ", status=" + status + ", addTime='" + addTime + '\'' + ", updateTime='" + updateTime + '\'' + '}';
    }
}
